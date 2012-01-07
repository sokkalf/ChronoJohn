package no.opentech.chronojohn.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import no.opentech.chronojohn.ChronoJohnApp;

import java.util.Calendar;

/**
 * Created by: Christian Lønaas
 * Date: 6.1.12
 * Time: 23:58
 */
public class ChronoAlarm extends BroadcastReceiver {
    private static Logger log = Logger.getLogger(ChronoAlarm.class);
    private final String REMINDER_BUNDLE = "MyReminderBundle";

    // this constructor is called by the alarm manager.
    public ChronoAlarm(){ }

    // you can use this constructor to create the alarm.
    //  Just pass in the main activity as the context,
    //  any extras you'd like to get later when triggered
    //  and the timeout
    public ChronoAlarm(Context context, Bundle extras, int timeoutInSeconds){
        AlarmManager alarmMgr =
                (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ChronoAlarm.class);
        intent.putExtra(REMINDER_BUNDLE, extras);
        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(System.currentTimeMillis());
        time.add(Calendar.SECOND, timeoutInSeconds);
        ChronoJohnApp.setTimerOn(true);
        alarmMgr.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(),
                pendingIntent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // here you can get the extras you passed in when creating the alarm
        //intent.getBundleExtra(REMINDER_BUNDLE));

        Toast.makeText(context, "Alarm went off", Toast.LENGTH_SHORT).show();
        log.debug("Alarm went off");
        ChronoJohnApp.setTimerOn(false);
    }
}