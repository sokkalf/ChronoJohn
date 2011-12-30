package no.opentech.chronojohn.models;

import no.opentech.chronojohn.entities.Timer;

import java.util.ArrayList;

/**
 * Created by: Christian Lønaas
 * Date: 28.12.11
 * Time: 23:20
 */
public class ViewTimersModel {
    private ArrayList<Timer> timers;
    
    public ViewTimersModel() {
        timers = new ArrayList<Timer>();
        timers.add(Timer.getQuickTimer());
        timers.add(Timer.getDefaultTimer());
    }

    public ArrayList<Timer> getTimers() {
        return timers;
    }
    
    public Timer getTimer(int pos) {
        return timers.get(pos);
    }
}
