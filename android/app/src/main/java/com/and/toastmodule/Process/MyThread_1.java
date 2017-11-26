package com.and.toastmodule.Process;

import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;

/**
 * Created by Administrador on 24/11/2017.
 */

class MyThread_1 extends Thread {

    private ReactApplicationContext context;

    public MyThread_1(ReactApplicationContext context){

        this.context = context;
    }

    public void run(){

        Toast.makeText(this.context, "Iniciou thread.", Toast.LENGTH_SHORT).show();

        goToSleep();

        Toast.makeText(this.context, "Terminou thread.", Toast.LENGTH_SHORT).show();
    }



    private void goToSleep(){
        try {

            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

