package no.opentech.chronojohn;

import android.app.Application;
import android.content.Context;

/**
 * Created by: Christian Lønaas
 * Date: 29.12.11
 * Time: 15:55
 */
public class ChronoJohnApp extends Application {
    private static ChronoJohnApp instance;
    public static final String APP_NAME = "ChronoJohn";
    public static final boolean DEVELOPMENT_VERSION = true;

    public ChronoJohnApp() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

    public static boolean isRelease() {
        return !DEVELOPMENT_VERSION;
    }
}
