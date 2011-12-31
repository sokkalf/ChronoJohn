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

package no.opentech.chronojohn.models;

import android.widget.EditText;
import no.opentech.chronojohn.entities.Timer;
import no.opentech.chronojohn.utils.Logger;

/**
 * Created by: Christian Lønaas
 * Date: 31.12.11
 * Time: 0:16
 */
public class QuickTimerModel {
    public static final int MAX_HOUR = 200;
    public static final int MAX_MINUTE = 59;
    public static final int MAX_SECOND = 59;
    private static Logger log = Logger.getLogger(QuickTimerModel.class);

    private int hourFieldValue = 0;
    private int minuteFieldValue = 0;
    private int secondFieldValue = 0;
    private int seconds;
    private EditText hourField, minuteField, secondField;
    private Timer timer;

    public QuickTimerModel(EditText hour, EditText minute, EditText second) {
        this.hourField = hour;
        this.minuteField = minute;
        this.secondField = second;
        setHour(hourFieldValue);
        setMinute(minuteFieldValue);
        setSecond(secondFieldValue);
    }

    public int getHour() {
        return hourFieldValue;
    }

    public void setHour(int hourField) {
        if(hourField > MAX_HOUR) hourField = 0;
        if(hourField < 0) hourField = MAX_HOUR;
        this.hourFieldValue = hourField;
        this.hourField.setText(String.format("%d", this.hourFieldValue));
        updateSeconds();
    }

    public int getMinute() {
        return minuteFieldValue;
    }

    public void setMinute(int minuteField) {
        if(minuteField > MAX_MINUTE) minuteField = 0;
        if(minuteField < 0) minuteField = MAX_MINUTE;
        this.minuteFieldValue = minuteField;
        this.minuteField.setText(String.format("%02d", this.minuteFieldValue));
        updateSeconds();
    }

    public int getSecond() {
        return secondFieldValue;
    }

    public void setSecond(int secondField) {
        if(secondField > MAX_SECOND) secondField = 0;
        if(secondField < 0) secondField = MAX_SECOND;
        this.secondFieldValue = secondField;
        this.secondField.setText(String.format("%02d", this.secondFieldValue));
        updateSeconds();
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
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
}
