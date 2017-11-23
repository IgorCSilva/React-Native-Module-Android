package com.and.toastmodule.Toasts;

import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;

/**
 * Created by Administrador on 23/11/2017.
 */

public class ToastPersonalizado {

    private static String mensagem;
    private Toast toast;

    public ToastPersonalizado(String msg){
        mensagem = msg;
    }

    public String getMsg(){
        return mensagem;
    }


    public void createToast(ReactApplicationContext context){

        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);;
        toast.setDuration(Toast.LENGTH_SHORT);
    }


    public void setView(RelativeLayout view) {
        toast.setView(view);
    }

    public void show() {

        toast.show();
    }
}
