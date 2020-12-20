package com.mrmnk.cooltimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener{

    private SeekBar seekBar;
    private TextView timerTextView;
    private Button timerBtn;
    private boolean runTimerFlag = false;
    private MediaPlayer mediaPlayer;
    private int defaultInterval;
    SharedPreferences sharedPreferences;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        timerTextView = findViewById(R.id.timerTextView);
        timerBtn = findViewById(R.id.timerBtn);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        seekBar.setMax(600);
        setDefaultOptions();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    if (mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    }
                    setTimeToTextView(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        timerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (runTimerFlag){
                    stopTimer();
                } else {
                    runTimerFlag = true;
                    startTimer();
                }
            }
        });

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    private void setTimeToTextView (int time){
        String minutes = String.valueOf(time / 60);
        String seconds = String.valueOf(time % 60);
        if (Integer.parseInt(minutes) < 10 ){
            minutes = "0" + minutes;
        }
        if (Integer.parseInt(seconds) < 10 ){
            seconds = "0" + seconds;
        }
        String string = minutes + ":" + seconds;
        timerTextView.setText(string);
    }

    private long getMilliseconds(){
        String strFromTimerTextView = String.valueOf(timerTextView.getText());
        String[] subStr = strFromTimerTextView.split(":");
        return (Long.parseLong(subStr[0]) * 60 + Long.parseLong(subStr[1])) * 1000;
    }

    private void stopTimer(){
        timer.cancel();
        timerBtn.setText("start");
        setDefaultOptions();
    }

    private void startTimer(){
        timerBtn.setText("stop");
        seekBar.setEnabled(false);
        timer = new Timer (getMilliseconds());
        timer.start();
    }

    protected void setDefaultOptions(){
        setIntervalFromSharedPreferences(sharedPreferences);
        setTimeToTextView(seekBar.getProgress());
        seekBar.setEnabled(true);
        runTimerFlag = false;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("default_interval")){
            setIntervalFromSharedPreferences(sharedPreferences);
        }
    }

    public class Timer extends CountDownTimer {

        public Timer(long millisInFuture) {
            super(millisInFuture, 1000);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            setTimeToTextView(millisUntilFinished);
        }

        @Override
        public void onFinish() {

            SharedPreferences sharedPreferences =
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            if (sharedPreferences.getBoolean("enable_sound", true)) {
                String melodyName = sharedPreferences.getString("timer_melody", "bell");
                if (melodyName.equals("bell")) {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bell_sound);
                    mediaPlayer.start();
                } else if (melodyName.equals("alarm")) {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                    mediaPlayer.start();
                } else if (melodyName.equals("bip")) {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bip_sound);
                    mediaPlayer.start();
                }
                timerBtn.setText("start");
                setDefaultOptions();
            }
        }

        private void setTimeToTextView (long time){
            time = time / 1000;
            String minutes = String.valueOf(time / 60);
            String seconds = String.valueOf(time % 60);
            if (Integer.parseInt(minutes) < 10 ){
                minutes = "0" + minutes;
            }
            if (Integer.parseInt(seconds) < 10 ){
                seconds = "0" + seconds;
            }
            String string = minutes + ":" + seconds;
            timerTextView.setText(string);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.timer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            Intent openSettings =
                    new Intent(this, SettingsActivity.class);
            startActivity(openSettings);
            return true;
        } else if (id == R.id.action_about){
            Intent openAbout =
                    new Intent(this, AboutActivity.class);
            startActivity(openAbout);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setIntervalFromSharedPreferences (SharedPreferences sharedPreferences){
        defaultInterval = Integer.valueOf(sharedPreferences.getString("default_interval", "30"));
        setTimeToTextView(defaultInterval);
        seekBar.setProgress(defaultInterval);
    }
}
