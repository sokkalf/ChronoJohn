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
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import no.opentech.chronojohn.ChronoJohnApp;
import no.opentech.chronojohn.R;
import no.opentech.chronojohn.filters.InputFilterMinMax;
import no.opentech.chronojohn.models.QuickTimerModel;
import no.opentech.chronojohn.models.TimerModel;
import no.opentech.chronojohn.utils.Logger;

/**
 * Created by: Christian Lønaas
 * Date: 29.12.11
 * Time: 22:58
 */
public class NewTimerActivity extends Activity {
    private static Logger log = Logger.getLogger(NewTimerActivity.class);
    private TimerModel model;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Create a new timer");
        setContentView(R.layout.new_timer);
        TextView label = (TextView) findViewById(R.id.timer_name_label);
        EditText nameField = (EditText) findViewById(R.id.timer_name);
        nameField.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        nameField.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                model.setAlarmName(charSequence.toString());
            }

            public void afterTextChanged(Editable editable) {

            }
        });
        label.setVisibility(View.GONE);
        Button saveButton = (Button) findViewById(R.id.save_timer);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                NewTimerActivity.this.setResult(Activity.RESULT_OK, resultIntent);
                model.save();
                NewTimerActivity.this.finish();
            }
        });
        EditText hourField = (EditText) findViewById(R.id.edit_hours);
        EditText minuteField = (EditText) findViewById(R.id.edit_minutes);
        EditText secondField = (EditText) findViewById(R.id.edit_seconds);
        model = new TimerModel(hourField, minuteField, secondField);

        hourField.setFilters(new InputFilter[]{new InputFilterMinMax(0, ChronoJohnApp.MAX_HOUR)});
        minuteField.setFilters(new InputFilter[]{new InputFilterMinMax(0, ChronoJohnApp.MAX_MINUTE)});
        secondField.setFilters(new InputFilter[]{new InputFilterMinMax(0, ChronoJohnApp.MAX_SECOND)});
        FocusWatcher focusWatcher = new FocusWatcher();
        EditTextWatcher hourTextWatcher = new EditTextWatcher(hourField);
        EditTextWatcher minueTextWatcher = new EditTextWatcher(minuteField);
        EditTextWatcher secondTextWatcher = new EditTextWatcher(secondField);

        hourField.setOnFocusChangeListener(focusWatcher);
        minuteField.setOnFocusChangeListener(focusWatcher);
        secondField.setOnFocusChangeListener(focusWatcher);
        hourField.addTextChangedListener(hourTextWatcher);
        minuteField.addTextChangedListener(minueTextWatcher);
        secondField.addTextChangedListener(secondTextWatcher);

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
            if (view.equals(findViewById(R.id.hours_up))) {
                model.incrementHour();
            } else if (view.equals(findViewById(R.id.hours_down))) {
                model.decrementHour();
            } else if (view.equals(findViewById(R.id.minutes_up))) {
                model.incrementMinute();
            } else if (view.equals(findViewById(R.id.minutes_down))) {
                model.decrementMinute();
            } else if (view.equals(findViewById(R.id.seconds_up))) {
                model.incrementSecond();
            } else if (view.equals(findViewById(R.id.seconds_down))) {
                model.decrementSecond();
            }
        }
    }

    private class EditTextWatcher implements TextWatcher {
        private EditText textField;

        public EditTextWatcher(EditText text) {
            this.textField = text;
        }

        public void afterTextChanged(Editable s) {
            model.setEditing(false);
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            model.setEditing(true);
        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            try {
                if (textField.equals(findViewById(R.id.edit_hours)))
                    model.setHour(Integer.parseInt(textField.getText().toString()));
                else if (textField.equals(findViewById(R.id.edit_minutes)))
                    model.setMinute(Integer.parseInt(textField.getText().toString()));
                else if (textField.equals(findViewById(R.id.edit_seconds)))
                    model.setSecond(Integer.parseInt(textField.getText().toString()));
            } catch (NumberFormatException nfe) {
                log.debug("NumberFormatException");
            }

        }
    }

    private class FocusWatcher implements View.OnFocusChangeListener {
        public void onFocusChange(View view, boolean hasFocus) {
            if (hasFocus) {
                model.setEditing(true);
                ((EditText) view).setText("00");
                ((EditText) view).setSelection(0, ((EditText) view).getText().length());
            } else {
                model.setEditing(false);
            }
        }
    }
}