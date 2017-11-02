package com.and.toastmodule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

/**
 * Created by Administrador on 18/10/2017.
 */

public class ReactReceiver extends BroadcastReceiver {

    //private final String TAG = "StaticReceiver";
    private long[]  mVibratePattern = { 0, 200, 200, 300 };

    @Override
    public void onReceive(Context context, Intent intent) {

        String acao = intent.getAction();
        Toast.makeText(context, "Ação: " + acao, Toast.LENGTH_SHORT).show();

        if(Intent.ACTION_POWER_CONNECTED.equals(acao)){
            Toast.makeText(context, "Carregando...", Toast.LENGTH_LONG).show();

        }else {
            //Log.i(TAG, "INTENT Recebido");
            Toast.makeText(context, "INTENT Recebido do React Native pelo ReactReceiver", Toast.LENGTH_LONG).show();
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(mVibratePattern, -1);
        }
    }
}
