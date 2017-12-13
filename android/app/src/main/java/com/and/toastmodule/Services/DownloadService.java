package com.and.toastmodule.Services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrador on 07/11/2017.
 */

public class DownloadService extends IntentService {

    public static final String DOWNLOAD_COMPLETE = "igor.android.services.action.DOWNLOAD_COMPLETE";

    public DownloadService() {super("DownloadService");}


    @Override
    public void onHandleIntent(Intent intent) {


        try{

            // Checar se tem permissão... Android 6.0+
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            root.mkdirs();

            File output = new File(root, intent.getData().getLastPathSegment());

            if(output.exists()){
                output.delete();
            }else {

                URL url = new URL(intent.getData().toString());
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                FileOutputStream fos = new FileOutputStream(output.getPath());
                BufferedOutputStream out = new BufferedOutputStream(fos);

                try {
                    //Toast.makeText(getApplicationContext(), "Baixando...", Toast.LENGTH_SHORT).show();

                    InputStream in = c.getInputStream();
                    byte[] buffer = new byte[8192];
                    int len = 0;
                    while ((len = in.read(buffer)) >= 0) {
                        out.write(buffer, 0, len);
                    }
                    out.flush();
                }
                finally {
                    fos.getFD().sync();
                    out.close();
                    c.disconnect();
                }
            }


            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(DOWNLOAD_COMPLETE).putExtra("link", intent.getData().toString()));

        }catch (Exception e){
            Log.e(getClass().getName(), "Exception durante download", e);
        }
    }
}
