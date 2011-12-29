package no.opentech.chronojohn.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import no.opentech.chronojohn.ChronoJohnApp;
import no.opentech.chronojohn.R;
import no.opentech.chronojohn.adapter.TimerAdapter;
import no.opentech.chronojohn.entities.Timer;

import java.util.ArrayList;

/**
 * Created by: Christian Lønaas
 * Date: 28.12.11
 * Time: 23:19
 */
public class ViewTimersActivity extends ListActivity {
    private ArrayList<Timer> timers;
    private Context context = ChronoJohnApp.getContext();
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewtimers);
        setTitle("My Timers");
        timers = new ArrayList<Timer>();
        timers.add(Timer.getDefaultTimer());
        setListAdapter(new TimerAdapter(this, R.layout.list_timers, timers));
        ListView lv = getListView();
        lv.setCacheColorHint(Color.parseColor("#00000000"));
    }
}