package no.opentech.chronojohn.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import no.opentech.chronojohn.R;

/**
 * Created by: Christian Lønaas
 * Date: 30.12.11
 * Time: 14:11
 */
public class QuickTimerActivity extends Activity {
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
    }
}