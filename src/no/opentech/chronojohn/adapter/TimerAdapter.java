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

package no.opentech.chronojohn.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import no.opentech.chronojohn.R;
import no.opentech.chronojohn.entities.Timer;

import java.util.ArrayList;

/**
 * Created by: Christian Lønaas
 * Date: 29.12.11
 * Time: 17:35
 */
public class TimerAdapter extends ArrayAdapter<Timer> {
    ArrayList<Timer> timers;
    public TimerAdapter(Context context, int textViewResourceId, ArrayList<Timer> timers) {
        super(context, textViewResourceId, timers);
        this.timers = timers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row, null);
        }
        Timer timer = timers.get(position);
        if(null != timer) {
            TextView text = (TextView) v.findViewById(R.id.itemtext);
            TextView summary = (TextView) v.findViewById(R.id.summary);
            text.setTextColor(Color.LTGRAY);
            text.setText(timer.getName());
            summary.setTextColor(Color.CYAN);
            summary.setText((null != timer.getDescription() && timer.getDescription().length() > 0) ? timer.getDescription() : "Duration : " + timer.getSeconds());
        }
        return v;
    }
}
