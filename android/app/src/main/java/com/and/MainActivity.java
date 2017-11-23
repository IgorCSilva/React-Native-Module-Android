package com.and;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;


import com.and.toastmodule.DownloadViewActivity;
import com.and.toastmodule.Services.DownloadService;
import com.facebook.react.ReactActivity;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */


    @Override
    protected String getMainComponentName() {
        return "and";
    }

    private static final String MINHA_ACAO = "module.android.MINHA_ACAO";
    public static final String PACKAGE_NAME = "com.and";
    InternoReceiver mReceiver;

    class InternoReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent){
            Toast.makeText(context, "Ação: " + intent.getAction(), Toast.LENGTH_SHORT).show();
        }
    }

    private BroadcastReceiver onDownloadCompleteEvent=new BroadcastReceiver() {
        public void onReceive(Context ctxt, Intent i) {
            //botaoDownload.setEnabled(true);
            Toast.makeText(ctxt, "Download finalizado!", Toast.LENGTH_LONG).show();

            String link = i.getStringExtra("link");
            startActivity(new Intent(ctxt,DownloadViewActivity.class).putExtra("link", link));
        }
    };

    @Override
    protected void onCreate(Bundle os) {
        super.onCreate(os);

        //Toast.makeText(this, "onCreate - MainActivity", Toast.LENGTH_SHORT).show();

        mReceiver = new InternoReceiver();

    }

    @Override
    public void onStart(){
        super.onStart();
        //Toast.makeText(this, "onStart - MainActivity", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onResume(){
        super.onResume();

        //Toast.makeText(this, "onResume - MainActivity", Toast.LENGTH_SHORT).show();

        IntentFilter filterLocal = new IntentFilter(MINHA_ACAO);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, filterLocal);

        IntentFilter f = new IntentFilter(DownloadService.DOWNLOAD_COMPLETE);
        LocalBroadcastManager.getInstance(this).registerReceiver(onDownloadCompleteEvent, f);


        IntentFilter filter = new IntentFilter(Intent.ACTION_USER_PRESENT);
        registerReceiver(mReceiver, filter);


    }

    @Override
    public void onPause(){
        super.onPause();

        //Toast.makeText(this, "onPause - MainActivity", Toast.LENGTH_SHORT).show();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(onDownloadCompleteEvent);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }

    @Override
    public void onStop(){
        super.onStop();

        //Toast.makeText(this, "onStop - MainActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        //Toast.makeText(this, "onDestroy - MainActivity", Toast.LENGTH_SHORT).show();
    }


}
