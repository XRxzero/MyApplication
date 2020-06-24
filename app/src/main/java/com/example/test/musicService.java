package com.example.test;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class musicService extends Service {
    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    @Override
    public void onStart(Intent intent,int startId){
        super.onStart(intent,startId);
        if(mediaPlayer==null){
            mediaPlayer=MediaPlayer.create(this,R.raw.target);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }
    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
        mediaPlayer.stop();
    }
}

