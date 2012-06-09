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

import android.util.Log;
import no.opentech.chronojohn.ChronoJohnApp;

/**
 * Created by: Christian Lønaas
 * Date: 18.12.11
 * Time: 16:08
 * modified for ChronoJohn 29.12.11
 */
public class Logger {
    public static boolean enabled = !ChronoJohnApp.isRelease();
    public static String TAG;

    public Logger(String name) {
        TAG = ChronoJohnApp.APP_NAME + "/" + name;
    }

    public static Logger getLogger(Class c) {
        Logger logger = new Logger(c.getName());
        return logger;
    }
    
    public void debug(String message) {
        if(enabled) Log.d(TAG, message);
    }
    
    public void info(String message) {
        if(enabled) Log.i(TAG, message);
    }
    
    public void verbose(String message) {
        if(enabled) Log.v(TAG, message);
    }
    
    public void error(String message) {
        if (enabled) Log.e(TAG, message);
    }
    
    public void warning(String message) {
        if (enabled) Log.w(TAG, message);
    }
}
