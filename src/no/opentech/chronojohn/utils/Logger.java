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
    public static Logger logger;
    
    public Logger(String name) {
        TAG = ChronoJohnApp.APP_NAME + "/" + name;
    }

    public static Logger getLogger(Class c) {
        logger = new Logger(c.getName());
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
