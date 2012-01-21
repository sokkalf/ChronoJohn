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

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import no.opentech.chronojohn.ChronoJohnApp;
import no.opentech.chronojohn.R;
import no.opentech.chronojohn.adapter.TimerAdapter;
import no.opentech.chronojohn.entities.Timer;
import no.opentech.chronojohn.models.ViewTimersModel;
import no.opentech.chronojohn.utils.Logger;

/**
 * Created by: Christian Lønaas
 * Date: 28.12.11
 * Time: 23:19
 */
public class ViewTimersActivity extends ListActivity {
    private ViewTimersModel model;
    private Context context = ChronoJohnApp.getContext();
    private static Logger log = Logger.getLogger(ViewTimersActivity.class);
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewtimers);
        setTitle("My Timers");
        model = new ViewTimersModel();
        setListAdapter(new TimerAdapter(this, R.layout.list_timers, model.getTimers()));
        ListView lv = getListView();
        lv.setCacheColorHint(Color.parseColor("#00000000"));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Timer selected = model.getTimer(position);
                if(selected.isDefaultTimer()) {
                    newTimer();
                } else if(selected.isQuickTimer()) {
                    quickTimer();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.viewtimersmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chronojohn_settings:
                // go to settings
                break;
            case R.id.chronojohn_help:
                startActivity(new Intent(this, HelpActivity.class));
                break;

        }
        return true;
    }

    public void newTimer() {
        Intent intent = new Intent(this, NewTimerActivity.class);
        startActivity(intent);
    }
    
    public void quickTimer() {
        Intent intent = new Intent(this, QuickTimerActivity.class);
        startActivity(intent);
    }
    
    public void goToTimer() {
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPause() {

        super.onPause();
    }
}