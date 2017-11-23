package com.and.toastmodule.Notifications;

import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.and.MainActivity;
import com.and.R;
import com.facebook.react.bridge.ReactApplicationContext;

/**
 * Created by Administrador on 23/11/2017.
 */

public class SimpleNotification {

    private Uri sound;
    private long[] vibratePattern;
    private int id;
    private String title;
    private String text;

    NotificationCompat.Builder mBuilder;
    NotificationManager mNotifyManager;


    public SimpleNotification(long[] vibratePattern, int id, String title, String text){

        this.sound = Uri.parse("android.resource://"+ MainActivity.PACKAGE_NAME+"/" + R.raw.alarm_rooster);
        this.title = title;
        this.text = text;
        this.vibratePattern = vibratePattern;
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public long[] getVibrate(){
        return this.vibratePattern;
    }

    public void createNotification(ReactApplicationContext context){
        this.mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        this.mBuilder = new NotificationCompat.Builder(context);

        this.mBuilder.setTicker("React Native Notification")
                .setSmallIcon(android.R.drawable.btn_star)
                .setAutoCancel(false)
                .setContentTitle(this.title)
                .setContentText(this.text)
                .setSound(this.sound)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setVibrate(this.vibratePattern);
    }

    public void showNotification(){

        this.mNotifyManager.notify(this.id, this.mBuilder.build());
    }
}
