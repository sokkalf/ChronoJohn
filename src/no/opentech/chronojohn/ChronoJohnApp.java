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

package no.opentech.chronojohn;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import no.opentech.chronojohn.utils.ChronoAlarm;
import no.opentech.chronojohn.utils.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Christian Lønaas
 * Date: 29.12.11
 * Time: 15:55
 */
public class ChronoJohnApp extends Application {
    private static Context context;
    public static final String APP_NAME = "ChronoJohn";
    public static final boolean DEVELOPMENT_VERSION = true;
    public static final String PREFS_NAME = "ChronoJohnSettings";
    public static final String TIMERS_NAME = "ChronoJohnTimers";
    public static final int MAX_HOUR = 200;
    public static final int MAX_MINUTE = 59;
    public static final int MAX_SECOND = 59;
    private static Logger log = Logger.getLogger(ChronoJohnApp.class);
    private static Map<String, Long> runningAlarms = new HashMap<String, Long>();

    @Override
    public void onCreate() {
        super.onCreate();
        ChronoJohnApp.context = getApplicationContext();
        log.debug("ChronoJohn starting up");
        SharedPreferences timers = getSharedPreferences(TIMERS_NAME, 0);
        for(String key : timers.getAll().keySet()) {
            if(timers.getLong(key, 0) > System.currentTimeMillis()) { // if alarm time is not here yet, restore
                log.debug("Restoring " + key);
                runningAlarms.put(key, timers.getLong(key, System.currentTimeMillis()));
            }
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public ChronoJohnApp() {

    }

    public static Context getContext() {
        return ChronoJohnApp.context;
    }

    public static boolean isRelease() {
        return !DEVELOPMENT_VERSION;
    }

    public static void registerAlarm(String name, int seconds, Context context) {
        long finishedMillis = System.currentTimeMillis() + (seconds * 1000);
        log.debug("Registering alarm " + name + " for a " + seconds + " timeout, finished at " + finishedMillis + "ms");
        runningAlarms.put(name, finishedMillis);
        SharedPreferences timers = context.getSharedPreferences(ChronoJohnApp.TIMERS_NAME, 0);
        SharedPreferences.Editor editor = timers.edit();
        editor.putLong(name, runningAlarms.get(name));
        editor.commit();
    }

    public static boolean isAlarmRunning(String name) {
        return runningAlarms.containsKey(name);
    }
    
    public static long getAlarmMillis(String name) {
        return runningAlarms.get(name);
    }

    public static void deRegisterAlarm(String name, Context context) {
        log.debug("Deregistering alarm " + name);
        SharedPreferences timers = context.getSharedPreferences(TIMERS_NAME, 0);
        timers.edit().remove(name).commit();
        runningAlarms.remove(name);
    }

    public static void cancelAlarm(String name) {
        deRegisterAlarm(name, getContext());
    }

    public static void restoreState(Context context) {
        log.debug("ChronoJohn restoring state");
        SharedPreferences timers = context.getSharedPreferences(TIMERS_NAME, 0);
        for(String key : timers.getAll().keySet()) {
            if(timers.getLong(key, 0) > System.currentTimeMillis()) { // if alarm time is not here yet, restore
                log.debug("Restoring " + key);
                runningAlarms.put(key, timers.getLong(key, System.currentTimeMillis()));
            }
        }
    }
}
