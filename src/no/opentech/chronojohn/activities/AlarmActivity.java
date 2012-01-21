package no.opentech.chronojohn.activities;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import no.opentech.chronojohn.R;
import no.opentech.chronojohn.utils.Logger;

import java.io.IOException;

/**
 * Created by: Christian Lønaas
 * Date: 7.1.12
 * Time: 15:11
 */
public class AlarmActivity extends Activity {
    private static Logger log = Logger.getLogger(AlarmActivity.class);
    private MediaPlayer player;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD); // unlock screen

        Button stopButton = (Button) findViewById(R.id.stopalarm);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                player.stop();
                AlarmActivity.this.finish();
            }
        });

        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(alert == null){
            // alert is null, using backup
            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            if(alert == null){
                alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            }
        }
        
        player = new MediaPlayer();
        try {
        player.setDataSource(this, alert);
        final AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
            player.setAudioStreamType(AudioManager.STREAM_ALARM);
            player.setLooping(true);
            player.prepare();
            player.start();
        }
        } catch (IOException ioe) {
            log.debug("IOException loading alarm sound");
        }
    }

    @Override
    public void onBackPressed() {
        player.stop();
        this.finish();
        super.onBackPressed();
    }
}