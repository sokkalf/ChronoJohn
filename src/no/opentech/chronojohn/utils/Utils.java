package no.opentech.chronojohn.utils;

import no.opentech.chronojohn.ChronoJohnApp;
import no.opentech.chronojohn.crud.TimerRepository;

/**
 * Created by: Christian Lønaas
 * Date: 29.12.11
 * Time: 16:37
 */
public class Utils {
    private static TimerRepository timerRepository = getTimerRepository();
    private static DBHelper dBHelper = getDBHelper();

    public static DBHelper getDBHelper() {
        if(null != dBHelper) return dBHelper;
        else return new DBHelper(ChronoJohnApp.getContext());
    }

    public static TimerRepository getTimerRepository() {
        if(null != timerRepository) return timerRepository;
        else return new TimerRepository(getDBHelper());
    }
}
