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