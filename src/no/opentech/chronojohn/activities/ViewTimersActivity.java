package no.opentech.chronojohn.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListView;
import no.opentech.chronojohn.ChronoJohnApp;
import no.opentech.chronojohn.R;
import no.opentech.chronojohn.adapter.TimerAdapter;
import no.opentech.chronojohn.entities.Timer;
import no.opentech.chronojohn.models.ViewTimersModel;

import java.util.ArrayList;

/**
 * Created by: Christian Lønaas
 * Date: 28.12.11
 * Time: 23:19
 */
public class ViewTimersActivity extends ListActivity {
    private ViewTimersModel model;
    private Context context = ChronoJohnApp.getContext();
    
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

    public void newTimer() {
        Intent intent = new Intent(this, NewTimerActivity.class);
        startActivity(intent);
    }
    
    public void quickTimer() {
        Intent intent = new Intent(this, QuickTimerActivity.class);
        startActivity(intent);
    }
}