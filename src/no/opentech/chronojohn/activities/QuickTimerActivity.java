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

package no.opentech.chronojohn.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import no.opentech.chronojohn.R;
import no.opentech.chronojohn.filters.InputFilterMinMax;
import no.opentech.chronojohn.models.QuickTimerModel;
import no.opentech.chronojohn.utils.Logger;

/**
 * Created by: Christian Lønaas
 * Date: 30.12.11
 * Time: 14:11
 */
public class QuickTimerActivity extends Activity {
    private static Logger log = Logger.getLogger(QuickTimerActivity.class);
    private QuickTimerModel model;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Quick timer");
        setContentView(R.layout.new_timer); // we reuse the "new_timer" layout,
                                            // and just renames and hides stuff to make it look like we want
        TextView label = (TextView) findViewById(R.id.timer_name_label);
        Button startButton = (Button) findViewById(R.id.save_timer);
        EditText editText = (EditText) findViewById(R.id.timer_name);
        editText.setVisibility(View.GONE);
        label.setText("Quick timer");
        startButton.setText("Start");
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                log.debug("Starting timer : " + model.getSeconds() + " seconds");
            }
        });

        // hours/minutes/seconds fields
        EditText hourField = (EditText) findViewById(R.id.edit_hours);
        EditText minuteField = (EditText) findViewById(R.id.edit_minutes);
        EditText secondField = (EditText) findViewById(R.id.edit_seconds);
        model = new QuickTimerModel(hourField, minuteField, secondField);
        hourField.setFilters(new InputFilter[] {new InputFilterMinMax(0, QuickTimerModel.MAX_HOUR)});
        minuteField.setFilters(new InputFilter[] {new InputFilterMinMax(0, QuickTimerModel.MAX_MINUTE)});
        secondField.setFilters(new InputFilter[] {new InputFilterMinMax(0, QuickTimerModel.MAX_SECOND)});
        
        // up/down buttons
        Button hoursUpButton = (Button) findViewById(R.id.hours_up);
        Button hoursDownButton = (Button) findViewById(R.id.hours_down);
        Button minutesUpButton = (Button) findViewById(R.id.minutes_up);
        Button minutesDownButton = (Button) findViewById(R.id.minutes_down);
        Button secondsUpButton = (Button) findViewById(R.id.seconds_up);
        Button secondsDownButton = (Button) findViewById(R.id.seconds_down);
        ButtonListener listener = new ButtonListener();
        hoursUpButton.setOnClickListener(listener);
        hoursDownButton.setOnClickListener(listener);
        minutesUpButton.setOnClickListener(listener);
        minutesDownButton.setOnClickListener(listener);
        secondsUpButton.setOnClickListener(listener);
        secondsDownButton.setOnClickListener(listener);

    }
    
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View view) {
            if(view.equals(findViewById(R.id.hours_up))) {
                model.incrementHour();
            } else if(view.equals(findViewById(R.id.hours_down))) {
                model.decrementHour();
            } else if(view.equals(findViewById(R.id.minutes_up))) {
                model.incrementMinute();
            } else if(view.equals(findViewById(R.id.minutes_down))) {
                model.decrementMinute();
            } else if(view.equals(findViewById(R.id.seconds_up))) {
                model.incrementSecond();
            } else if(view.equals(findViewById(R.id.seconds_down))) {
                model.decrementSecond();
            }
        }
    }
}