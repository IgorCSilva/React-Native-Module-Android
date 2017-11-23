package com.and.toastmodule.Services;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.and.R;

/**
 * Created by Administrador on 05/11/2017.
 */

public class BindService extends Service {

    private static final int ID = 2;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();

        // configurar media player
        mediaPlayer = MediaPlayer.create(this, R.raw.ghosts);

        if (mediaPlayer != null) {
            //fica em loop
            mediaPlayer.setLooping(true);
        }



        final Notification notification = new Notification.Builder(
                getApplicationContext())
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setOngoing(true).setContentTitle("Music Service rodando")
                .setContentText("Texto ...!")
                .build();

        // inicia em estado foreground, para ter prioridade na memoria
        // evita que seja facilmente eliminado pelo sistema
        startForeground(ID, notification);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    public void playMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void pauseMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private final IBinder mBinder = new MusicBinder();

    public class MusicBinder extends Binder {
        public BindService getService() {
            // retorna a instancia do Service, para que clientes chamem metodos publicos
            return BindService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }
}
