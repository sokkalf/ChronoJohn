package no.opentech.chronojohn.models;

import android.widget.EditText;
import no.opentech.chronojohn.ChronoJohnApp;
import no.opentech.chronojohn.entities.Timer;
import no.opentech.chronojohn.utils.Logger;
import no.opentech.chronojohn.utils.Utils;

import java.util.Date;

/**
 * Created by: Christian Lønaas
 * Date: 11.02.12
 * Time: 23:46
 */
public class TimerModel {
    private static Logger log = Logger.getLogger(TimerModel.class);
    private int hourFieldValue = 0;
    private int minuteFieldValue = 0;
    private int secondFieldValue = 0;
    private EditText hourField, minuteField, secondField;
    private Timer timer;
    private boolean editing = false;

    public TimerModel(EditText hour, EditText minute, EditText second) {
        timer = new Timer();
        this.hourField = hour;
        this.minuteField = minute;
        this.secondField = second;
        setHour(hourFieldValue);
        setMinute(minuteFieldValue);
        setSecond(secondFieldValue);
    }

    public String getAlarmName() {
        return timer.getName();
    }

    public void setAlarmName(String alarmName) {
        timer.setName(alarmName);
    }
    
    public String getAlarmDescription() {
        return timer.getDescription();
    }
    
    public void setAlarmDescription(String desc) {
        timer.setDescription(desc);
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public int getHour() {
        return hourFieldValue;
    }

    public void setHour(int hourField) {
        if(hourField > ChronoJohnApp.MAX_HOUR) hourField = 0;
        if(hourField < 0) hourField = ChronoJohnApp.MAX_HOUR;
        this.hourFieldValue = hourField;
        if(!editing) this.hourField.setText(String.format("%d", this.hourFieldValue));
        updateSeconds();
    }

    public int getMinute() {
        return minuteFieldValue;
    }

    public void setMinute(int minuteField) {
        if(minuteField > ChronoJohnApp.MAX_MINUTE) minuteField = 0;
        if(minuteField < 0) minuteField = ChronoJohnApp.MAX_MINUTE;
        this.minuteFieldValue = minuteField;
        if(!editing) this.minuteField.setText(String.format("%02d", this.minuteFieldValue));
        updateSeconds();
    }

    public int getSecond() {
        return secondFieldValue;
    }

    public void setSecond(int secondField) {
        if(secondField > ChronoJohnApp.MAX_SECOND) secondField = 0;
        if(secondField < 0) secondField = ChronoJohnApp.MAX_SECOND;
        this.secondFieldValue = secondField;
        if(!editing) this.secondField.setText(String.format("%02d", this.secondFieldValue));
        updateSeconds();
    }

    public int getSeconds() {
        return timer.getSeconds();
    }

    public void setSeconds(int seconds) {
        timer.setSeconds(seconds);
    }

    public void updateSeconds() {
        int secs = (hourFieldValue*3600) + (minuteFieldValue*60) + secondFieldValue;
        setSeconds(secs);
    }

    public void incrementHour() {
        setHour(getHour()+1);
    }

    public void incrementMinute() {
        setMinute(getMinute()+1);
    }

    public void incrementSecond() {
        setSecond(getSecond()+1);
    }

    public void decrementHour() {
        setHour(getHour()-1);
    }

    public void decrementMinute() {
        setMinute(getMinute()-1);
    }

    public void decrementSecond() {
        setSecond(getSecond()-1);
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void start() {

    }    
    
    public void save() {
        log.debug("Saving alarm " + timer.getName() + ", " + timer.getSeconds() + " seconds.");
        timer.setCreated(new Date());
        Utils.getTimerRepository().insert(timer);
    }
}
