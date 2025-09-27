package com.example.crochet_app;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {

    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.sample_song); // Replace with your song resource
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Start playback
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // Stop playback and release resources
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
