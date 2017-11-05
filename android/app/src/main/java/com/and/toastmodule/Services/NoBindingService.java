package com.and.toastmodule.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.and.R;
import com.and.toastmodule.ToastModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by Administrador on 04/11/2017.
 */

public class NoBindingService extends Service {

    private final String TAG = "MusicPlayerNoBindingService";

    private MediaPlayer mPlayer;
    private int mStartID;


    @Override
    public void onCreate() {
        super.onCreate();


        // configurar media player
        //Nine Inch Nails Ghosts I-IV is licensed under a Creative Commons Attribution Non-Commercial Share Alike license.
        mPlayer = MediaPlayer.create(this, R.raw.ghosts);

        if (mPlayer != null) {

            //nao deixa a música entrar em loop
            mPlayer.setLooping(false);

            // encerrar o service quando terminar a musica
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // encerra se foi iniciado com o mesmo ID

                    stopSelf(mStartID);
                }
            });
        }

        Toast.makeText(getApplicationContext(),"onCreate() - Service", Toast.LENGTH_SHORT).show();
    }
    @ReactMethod
    public void show(){
        customNotification()
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(getApplicationContext(),"onStartCommand() - Service", Toast.LENGTH_SHORT).show();

        if (mPlayer != null) {
            // ID para o comando de start especifico
            mStartID = startId;

            /**/
            //se ja esta tocando...
            if (mPlayer.isPlaying()) {
                //volta pra o inicio
                mPlayer.seekTo(0);
            }
            else {
                // inicia musica
                mPlayer.start();
            }
        }
        // nao reinicia service automaticamente se for eliminado
        return START_NOT_STICKY;

    }

    // Este método é chamando quando se executa a chamada stopService().
    // Quando a activity é morta pelo usuário o onDestroy() não é chamado.
    // Não sei sobre outras possibilidades.
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();

            Toast.makeText(getApplicationContext(),"onDestroy() - Service", Toast.LENGTH_SHORT).show();
        }

    }

    // Não é possível fazer binding com este service. (prof)
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
        //return null;
    }
}
