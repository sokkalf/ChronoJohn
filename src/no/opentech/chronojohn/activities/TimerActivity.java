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
import android.os.CountDownTimer;
import android.widget.TextView;
import no.opentech.chronojohn.R;
import no.opentech.chronojohn.utils.Logger;

/**
 * Created by: Christian Lønaas
 * Date: 29.12.11
 * Time: 12:32
 */
public class TimerActivity extends Activity {
    private static Logger log = Logger.getLogger(TimerActivity.class);
    private int seconds;
    private TextView hourField, minuteField, secondField;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        seconds = getIntent().getIntExtra("timerSeconds", 0);
        hourField = (TextView) findViewById(R.id.hours);
        minuteField = (TextView) findViewById(R.id.minutes);
        secondField = (TextView) findViewById(R.id.seconds);
        updateView();
        new CountDownTimer(seconds * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                seconds = (int) (millisUntilFinished / 1000);
                updateView();
            }

            public void onFinish() {
                seconds = 0;
                updateView();
            }
        }.start();

    }

    public void updateView() {
        log.debug("seconds left : " + this.seconds);
        int hours = this.seconds / 3600;
        int minutes = (this.seconds % 3600) / 60;
        int secs = this.seconds % 60;

        hourField.setText(String.format("%02d", hours));
        minuteField.setText(String.format("%02d", minutes));
        secondField.setText(String.format("%02d", secs));
    }
}