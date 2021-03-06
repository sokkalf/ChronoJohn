/*
 * ChronoJohn, timer for Android
 * (C)2011, 2012 by Christian Lønaas
 *    <christian dot lonaas at discombobulator dot org>
 *
 * This file is part of ChronoJohn.
 *
 * ChronoJohn is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ChronoJohn is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ChronoJohn.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package no.opentech.chronojohn.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Toast;
import no.opentech.chronojohn.ChronoJohnApp;
import no.opentech.chronojohn.activities.AlarmActivity;

import java.util.Calendar;

/**
 * Created by: Christian Lønaas
 * Date: 6.1.12
 * Time: 23:58
 */
public class ChronoAlarm extends BroadcastReceiver {
    private static Logger log = Logger.getLogger(ChronoAlarm.class);
    public static final String REMINDER_BUNDLE = "AlarmBundle";
    public static PowerManager.WakeLock wl;
    private static Context context = ChronoJohnApp.getContext();
    public ChronoAlarm(){ }

    public ChronoAlarm(Context context, Bundle extras, int timeoutInSeconds){
        AlarmManager alarmMgr =
                (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ChronoAlarm.class);
        intent.putExtra(REMINDER_BUNDLE, extras);
        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context, 1+(int)(timeoutInSeconds*(Math.random()*100)), intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(System.currentTimeMillis());
        time.add(Calendar.SECOND, timeoutInSeconds);
        ChronoJohnApp.registerAlarm(extras.getString("alarmName"), timeoutInSeconds, context);
        alarmMgr.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(),
                pendingIntent);
        log.debug("Setting new alarm at " + timeoutInSeconds + " seconds from now");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // here you can get the extras you passed in when creating the alarm
        ChronoJohnApp.restoreState(context);
        Bundle alarmBundle = intent.getBundleExtra(REMINDER_BUNDLE);
        String alarmName = alarmBundle.getString("alarmName") != null ? alarmBundle.getString("alarmName") : "default";
        log.debug("Alarm is " + (ChronoJohnApp.isAlarmRunning(alarmName) ? "registered" : "not registered"));
        if (ChronoJohnApp.isAlarmRunning(alarmName)) {
            ChronoJohnApp.deRegisterAlarm(alarmName, context);
            Toast.makeText(context, "Alarm '" + alarmName + "' went off", Toast.LENGTH_SHORT).show();
            log.debug("Alarm '" + alarmName + "' went off");
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            if (null != wl && wl.isHeld()) wl.release();
            wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
            wl.acquire();
            Intent i = new Intent(context, AlarmActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}