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

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import no.opentech.chronojohn.utils.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Christian Lønaas
 * Date: 29.12.11
 * Time: 15:55
 */
public class ChronoJohnApp extends Application {
    private static ChronoJohnApp instance;
    public static final String APP_NAME = "ChronoJohn";
    public static final boolean DEVELOPMENT_VERSION = true;
    public static final String PREFS_NAME = "ChronoJohnSettings";
    public static final String TIMERS_NAME = "ChronoJohnTimers";
    private static Logger log = Logger.getLogger(ChronoJohnApp.class);
    private static Map<String, Long> runningAlarms = new HashMap<String, Long>();

    public ChronoJohnApp() {
        instance = this;
    }

    @Override
    public void onCreate() {
        log.debug("ChronoJohn starting up");
        SharedPreferences timers = getSharedPreferences(TIMERS_NAME, 0);
        for(String key : timers.getAll().keySet()) {
            if(timers.getLong(key, 0) > System.currentTimeMillis()) // if alarm time is not here yet, restore
                runningAlarms.put(key, timers.getLong(key, System.currentTimeMillis()));
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static Context getContext() {
        return instance;
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
        for(String key : runningAlarms.keySet()) {
            editor.putLong(key, runningAlarms.get(key));
        }
        editor.commit();
    }

    public static boolean isAlarmRunning(String name) {
        return runningAlarms.containsKey(name);
    }

    public static void deRegisterAlarm(String name, Context context) {
        log.debug("Deregistering alarm " + name);
        SharedPreferences timers = context.getSharedPreferences(TIMERS_NAME, 0);
        timers.edit().remove(name);
        timers.edit().commit();
        runningAlarms.remove(name);
    }

    public static void restoreState(Context context) {
        log.debug("ChronoJohn restoring state");
        SharedPreferences timers = context.getSharedPreferences(TIMERS_NAME, 0);
        for(String key : timers.getAll().keySet()) {
            runningAlarms.put(key, timers.getLong(key, System.currentTimeMillis()));
        }
    }
}
