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
import no.opentech.chronojohn.R;

/**
 * Created by: Christian Lønaas
 * Date: 29.12.11
 * Time: 22:58
 */
public class NewTimerActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Create a new timer");
        setContentView(R.layout.new_timer);
    }
}