package com.and.toastmodule.Intents;

import android.content.Intent;
import android.net.Uri;

import com.facebook.react.bridge.ReactApplicationContext;

/**
 * Created by Administrador on 23/11/2017.
 */

public class IntentView {

    private String link;
    private Intent intent;

    public IntentView(String site){
        this.link = site;
    }

    public void createIntent(){

        // Criando intent para avisar ao sistema do que fazer.
        intent = new Intent();

        // A ação será exibir uma view.
        intent.setAction(Intent.ACTION_VIEW);

        // Como não se está em um contexto de uma activity é necessário
        // colocar esta flag.
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Passando o número a ser chamado para a intent.
        intent.setData(Uri.parse(this.link));
    }

    public void startIntent(ReactApplicationContext reactApplicationContext){

        // Inicializando a activity com a ação definica na intent.
        reactApplicationContext.startActivity(intent);
    }
}
