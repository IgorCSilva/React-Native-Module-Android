
package com.and.toastmodule;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.and.MainActivity;
import com.and.toastmodule.Services.BindService;
import com.and.toastmodule.Services.DownloadService;
import com.and.toastmodule.Services.NoBindingService;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;

import com.and.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class ToastModule extends ReactContextBaseJavaModule{


    public ToastModule(ReactApplicationContext reactApplicationContext){
        super(reactApplicationContext);
    }

    @Override
    public String getName(){
        return "ToastModule";
    }




    @ReactMethod
    public void callBack(Callback callback) {

        callback.invoke("Callback works!");
    }

    Context context = getReactApplicationContext();

    @ReactMethod
    public final void showToastSimples(final String msg){

        // Exibe toast com a mensagem enviada pelo código JS.
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    @ReactMethod
    public void showToastPersonalizado(String msg){

        // Instanciando elemento Toast.
        Toast toast = new Toast(getReactApplicationContext());

        // Setando a posição vertical do toast. Os dois números seguintes correspondem
        // respectivamente as posições x e y.
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

        // Definindo duração de exibição do toast.
        toast.setDuration(Toast.LENGTH_LONG);


        // Pegando layout e atribuindo a uma variável para que se possa manipular seus componentes.
        RelativeLayout rl = (RelativeLayout) getCurrentActivity().getLayoutInflater().inflate(R.layout.custom_toast, null);


        // Atribuindo o primeiro elemento (getChildAt(0)) a uma variável para setar seu texto. (É bom
        // se utilizar desta forma quando se quer trabalhar com vários filhos. Para se obter a quantidade
        // total de filhos se utiliza getChildCount())
        //TextView tv = (TextView) rl.getChildAt(0);
          // ou
        // Referenciando uma view pelo seu id. (melhor a se fazer em questão de manutenção de código)
        TextView tv = (TextView) rl.findViewById(R.id.toast_personalizado);

        // Setando texto com a mensagem enviada pelo código JS.
        tv.setText(msg);

        // Atribuindo ao toast a view manipulada acima.
        toast.setView(rl);

        // Exibindo o toast.
        toast.show();
    }

    @ReactMethod
    public void intentSite (String site){

        // Criando intent para avisar ao sistema do que fazer.
        Intent intent = new Intent();

        // A ação será exibir uma view.
        intent.setAction(Intent.ACTION_VIEW);

        // Como não se está em um contexto de uma activity é necessário
        // colocar esta flag.
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Passando o site a ser aberto para a intent.
        intent.setData(Uri.parse(site));

        // Inicializando a activity com a ação definica na intent.
        getReactApplicationContext().startActivity(intent);
    }

    @ReactMethod
    public void intentGeolocation (String local){

        // Criando intent para avisar ao sistema do que fazer.
        Intent intent = new Intent();

        // A ação será exibir uma view.
        intent.setAction(Intent.ACTION_VIEW);

        // Como não se está em um contexto de uma activity é necessário
        // colocar esta flag.
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Passando o local a ser aberto para a intent.
        intent.setData(Uri.parse(local));

        // Inicializando a activity com a ação definica na intent.
        getReactApplicationContext().startActivity(intent);
    }

    @ReactMethod
    public void intentCall (String number){

        // Criando intent para avisar ao sistema do que fazer.
        Intent intent = new Intent();

        // A ação será exibir uma view.
        intent.setAction(Intent.ACTION_DIAL);

        // Como não se está em um contexto de uma activity é necessário
        // colocar esta flag.
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Passando o número a ser chamado para a intent.
        intent.setData(Uri.parse(number));

        // Inicializando a activity com a ação definica na intent.
        getReactApplicationContext().startActivity(intent);
    }



    @ReactMethod
    public void intentPlaySongSdCard (String songPath){

        // Criando intent para avisar ao sistema do que fazer.
        Intent intent = new Intent();

        // A ação será exibir uma view.
        intent.setAction(Intent.ACTION_VIEW);

        // Como não se está em um contexto de uma activity é necessário
        // colocar esta flag.
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Passando o local do arquivo a ser aberto para a intent.
        intent.setDataAndType(Uri.parse(songPath), "audio/mp3");

        // Inicializando a activity com a ação definica na intent.
        getReactApplicationContext().startActivity(intent);
    }

    @ReactMethod
    public void sendReactBroadcast(String broadcast){

        getReactApplicationContext().sendBroadcast(new Intent(broadcast));
    }



    @ReactMethod
    public void simpleNotification( int id, String title, String text){

        // Definindo som ao ser executado e padrão de vibração.
        Uri soundURI = Uri.parse("android.resource://"+ MainActivity.PACKAGE_NAME+"/" + R.raw.alarm_rooster);
        long[] mVibratePattern = { 0, 200, 200, 300 };


        NotificationManager mNotifyManager;
        mNotifyManager = (NotificationManager) getReactApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getReactApplicationContext());

        mBuilder.setTicker("React Native Notification")
                .setSmallIcon(android.R.drawable.btn_star)
                .setAutoCancel(false)
                .setContentTitle(title)
                .setContentText(text)
                .setSound(soundURI)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setVibrate(mVibratePattern);

        mNotifyManager.notify(id, mBuilder.build());


    }

    @ReactMethod
    public void customNotification(int id, String title, String text){

        // Definindo som ao ser executado e padrão de vibração.
        Uri soundURI = Uri.parse("android.resource://"+ MainActivity.PACKAGE_NAME+"/" + R.raw.alarm_rooster);
        long[] mVibratePattern = { 0, 200, 200, 300 };

        RemoteViews mContentView = new RemoteViews(MainActivity.PACKAGE_NAME, R.layout.custom_notification);

        // Define a mensagem expandida da notificação personalizada.
        mContentView.setTextViewText(R.id.text, title);
        mContentView.setTextViewText(R.id.textDesc, text);

        // Costruir a notificação da mesma forma que a notificação simples.
        // Apenas prestar a atenção para agora atribuir a view personalizada à
        // notificação.
        NotificationManager mNotifyManager;
        mNotifyManager = (NotificationManager) getReactApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getReactApplicationContext());

        mBuilder.setTicker("React Native Notification")
                .setSmallIcon(android.R.drawable.btn_star)
                .setSound(soundURI)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(mVibratePattern)
                .setContent(mContentView);

        mNotifyManager.notify(id, mBuilder.build());
    }


    @ReactMethod
    public void actionNotification(int id, String title, String text, String action1, String action2){


        Intent int1 = new Intent(Intent.ACTION_USER_PRESENT);
        PendingIntent pendInt1 = PendingIntent.getActivity(getReactApplicationContext(), 0, int1, 0);

        Intent int2 = new Intent(Intent.ACTION_DIAL);
        PendingIntent pendInt2 = PendingIntent.getActivity(getReactApplicationContext(), 1, int2, 0);


        // Definindo som ao ser executado e padrão de vibração.
        Uri soundURI = Uri.parse("android.resource://"+ MainActivity.PACKAGE_NAME+"/" + R.raw.alarm_rooster);
        long[] mVibratePattern = { 0, 200, 200, 300 };


        NotificationManager mNotifyManager;
        mNotifyManager = (NotificationManager) getReactApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getReactApplicationContext());

        Bitmap largeIcon = BitmapFactory.decodeResource(getReactApplicationContext().getResources(), R.drawable.porquinlogo);

        mBuilder.setTicker("React Native Notification")
                .setSmallIcon(android.R.color.transparent)
                .setLargeIcon(largeIcon)
                .setContentTitle(title)
                .setContentText(text)
                .setSound(soundURI)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(mVibratePattern)
                .setColor(getReactApplicationContext().getResources().getColor(R.color.transparente));



        mBuilder.addAction(android.R.drawable.stat_sys_upload, action1, pendInt1)
                .addAction(android.R.drawable.stat_sys_download, action2, pendInt2);

        mNotifyManager.notify(id, mBuilder.build());

    }



    @ReactMethod
    public void inboxNotification(int id, String title, String summary){

        // Instancia de NotificationCompat.Builder.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getReactApplicationContext());

        // Aplicando estilo.
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle(builder);


        // Adicionando as linhas.
        int i;
        /*
        for(i = 0; i < texts.length; i++){
            style.addLine(texts[i]);
        }*/

        style.addLine("Linha 1");
        style.addLine("Linha 2");
        style.addLine("Linha 3");
        style.addLine("Linha 4");

        // Adicionando título.
        style.setBigContentTitle(title);

        // Acrescentando sumário.
        style.setSummaryText(summary);

        // Construindo a notificação.
        Notification notification = builder.setContentTitle("Title")
                .setContentText("This is a notification")
                .setSmallIcon(R.drawable.fire_eye_alien)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getReactApplicationContext());
        notificationManagerCompat.notify(id, notification);

    }

    @ReactMethod
    public void bigTextNotification(int id, String title, String bigText, String summary){

        // Instancia de NotificationCompat.Builder.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getReactApplicationContext());

        // Aplicando estilo.
        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle(builder);

        // Adicionando texto grande.
        style.bigText(bigText);

        // Adicionando título.
        style.setBigContentTitle(title);

        // Acrescentando sumário.
        style.setSummaryText(summary);

        // Construindo a notificação.
        Notification notification = builder.setContentTitle("Title")
                .setContentText("This is a notification")
                .setSmallIcon(android.R.drawable.ic_notification_overlay)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getReactApplicationContext());
        notificationManagerCompat.notify(id, notification);

    }

    @ReactMethod
    public void bigPictureNotification(int id, String title, String summary){

        // Instancia de NotificationCompat.Builder.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getReactApplicationContext());

        // Aplicando estilo.
        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle(builder);

        // Adicionando imagem.
        final Bitmap picture = BitmapFactory.decodeResource(getReactApplicationContext().getResources(), R.drawable.praia_xareu);
        style.bigPicture(picture);

        // Adicionando título.
        style.setBigContentTitle(title);

        // Acrescentando sumário.
        style.setSummaryText(summary);

        // Construindo a notificação.
        Notification notification = builder.setContentTitle("Title")
                .setContentText("This is a notification")
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getReactApplicationContext());
        notificationManagerCompat.notify(id, notification);

    }


    @ReactMethod
    public void settingsNotification(int id, String title){

        // Definindo som ao ser executado e padrão de vibração.
        Uri soundURI = Uri.parse("android.resource://"+ MainActivity.PACKAGE_NAME+"/" + R.raw.alarm_rooster);
        long[] mVibratePattern = { 0, 200, 200, 300 };

        Intent i=new Intent(Settings.ACTION_SECURITY_SETTINGS);
        PendingIntent pi = PendingIntent.getActivity(getReactApplicationContext(), 0, i, 0);


        NotificationManager mNotifyManager;
        mNotifyManager = (NotificationManager) getReactApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getReactApplicationContext());

        mBuilder.setTicker("React Native Notification")
                .setSmallIcon(android.R.drawable.btn_star)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentIntent(pi)
                .setContentTitle(title)
                .setSound(soundURI)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setVibrate(mVibratePattern)
                .addAction(android.R.drawable.bottom_bar, "alguma action", pi);

        mNotifyManager.notify(id, mBuilder.build());


    }

    @ReactMethod
    public void callback(Callback cb){

        try{
            cb.invoke("callback works!!");
        }catch (Exception e){
            Toast.makeText(getReactApplicationContext(), "Callback not works...", Toast.LENGTH_SHORT).show();
        }
    }

    @ReactMethod
    public void alertDialog(){

        // Instanciando um AlertDialog.Builder com seu construtor.
        AlertDialog.Builder builder = new AlertDialog.Builder(getCurrentActivity());

        // Escolhendo alguns sets para formar o alert dialog.
        //builder.setMessage("Construindo AlertDialog!!"); // Parece que não aparece se for utilizado também o .setItems.
        builder.setTitle("Alert Show");

        // Adicionando botões.
        builder.setPositiveButton("Show", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getReactApplicationContext(), "Clicou show!", Toast.LENGTH_SHORT).show();
            }
        });

        // Criando uma lista de escolha única.
        builder.setItems(R.array.colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getReactApplicationContext(), "Item " + which, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Fui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getReactApplicationContext(), "Clicou fui!", Toast.LENGTH_SHORT).show();
            }
        });

        // Obtendo o AlertDialog a partir do create().
        AlertDialog dialog = builder.create();

        // Desabilitando a opção de fechar o alert dialog ao clicar fora da caixa.
        dialog.setCancelable(false);

        // Mostrar o alert dialog.
        dialog.show();

    }

    // Player para o método seguinte.
    MediaPlayer mediaPlayer;

    @ReactMethod
    public void player(String command){


        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(getReactApplicationContext(), R.raw.moonlightsonata);
        }


        switch (command){

            case "play":    if(mediaPlayer != null){
                                mediaPlayer.start();
                            }
                            break;

            case "pause":   if(mediaPlayer != null){
                                mediaPlayer.pause();
                            }
                                break;

            case "stop": if(mediaPlayer != null){
                            mediaPlayer.release();
                            mediaPlayer = null;
                         }
                         break;

            default: mToast("Comando não conhecido: " + command);

        }

    }

    @ReactMethod
    public void serviceNoBind(String command){

        Intent serviceIntent = new Intent(getReactApplicationContext(), NoBindingService.class);

        if(command.equals("start")){
            getCurrentActivity().startService(serviceIntent);
        }else if(command.equals("stop")){
            getCurrentActivity().stopService(serviceIntent);
        }else {
            mToast("Comando desconhecido: " + command);
        }

    }


    BindService bindService;
    boolean isBound = false;

    ServiceConnection sConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mToast("Binding com service!");
            bindService = ((BindService.MusicBinder) service).getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mToast("Desconectou bound service!");
            bindService = null;
            isBound = false;
        }
    };

    @ReactMethod
    public void serviceWithBind(String command){


        if(command.equals("start")){

            if(isBound){
                bindService.playMusic();
            }else {
                mToast("Service ainda não fez binding...");
            }
        }else if(command.equals("pause")){
            if(isBound){
                bindService.pauseMusic();
            }else {
                mToast("Service ainda não fez binding...");
            }
        }else if(command.equals("bind")){


            Intent serviceIntent = new Intent(getReactApplicationContext(), BindService.class);
            getCurrentActivity().startService(serviceIntent);

            if(!isBound){
                mToast("Binding...");
                Intent bindIntent = new Intent(getReactApplicationContext(), BindService.class);
                isBound = getCurrentActivity().bindService(bindIntent, sConn, getReactApplicationContext().BIND_AUTO_CREATE);
            }
        }else if(command.equals("unbinding")){

            if(isBound) {
                mToast("Unbinding...");
                getCurrentActivity().unbindService(sConn);
                isBound = false;
            }
        }


    }

    @ReactMethod
    public void serviceDownload(String command, final String downloadLink){


        if(command.equals("download")){


            Intent downloadService = new Intent(getReactApplicationContext(), DownloadService.class);
            downloadService.setData(Uri.parse(downloadLink.toString()));
            getCurrentActivity().startService(downloadService);

        }else if(command.equals("visualizar")) {
            Intent viewFile = new Intent(getReactApplicationContext(), DownloadViewActivity.class).putExtra("link", downloadLink.toString());
            getCurrentActivity().startActivity(viewFile);

        }else {
            mToast("Comando não reconhecido");
        }
    }


    public boolean podeEscrever() {
        return ContextCompat.checkSelfPermission(getReactApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    /* Checa se memoria externa esta disponivel para leitura e escrita */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checa se memoria externa esta disponivel pelo menos para leitura */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    private void copiarImagemNaMemoria(File f) {
        try {
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(f));
            BufferedInputStream is = new BufferedInputStream(getCurrentActivity().getResources().openRawResource(R.raw.sport));
            copiar(is, os);
        } catch (FileNotFoundException e) {
            Log.e("COPIA", "FileNotFoundException " + e.getMessage());
        }
    }

    private void copiar(InputStream is, OutputStream os) {
        final byte[] buf = new byte[1024];
        int numBytes;
        try {
            while (-1 != (numBytes = is.read(buf))) {
                os.write(buf, 0, numBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                Log.e("COPIANDO", "IOException "+e.getMessage());

            }
        }
    }

    @ReactMethod
    public void memoriaExterna(String command){

        final String arquivo = "melhorDoNordeste.jpg";

        String[] STORAGE_PERMISSIONS = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        final int WRITE_EXTERNAL_STORAGE_REQUEST = 710;


        if(podeEscrever()){

            if(command.equals("copiar")){

                if(isExternalStorageWritable()){
                    File f = new File(getCurrentActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES), arquivo);
                    if (!f.exists()) {
                        copiarImagemNaMemoria(f);
                        Toast.makeText(getReactApplicationContext(), "Copiando...", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getReactApplicationContext(), "Arquivo já existe...", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getReactApplicationContext(),"Memória externa não disponivel!",Toast.LENGTH_SHORT).show();
                }

            }else if(command.equals("ler")){

                if (isExternalStorageReadable()) {
                    Intent intent = new Intent(getReactApplicationContext(), ViewImage.class).putExtra("arquivo", arquivo);
                    getCurrentActivity().startActivity(intent);
                }
                else {
                    Toast.makeText(getReactApplicationContext(),"Memória externa nao disponivel!",Toast.LENGTH_SHORT).show();
                }

            }else if(command.equals("apagar")){
                if (isExternalStorageWritable()) {
                    File f = new File(getCurrentActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES),arquivo);
                    if (f.exists()) {
                        f.delete();
                        Toast.makeText(getReactApplicationContext(),"Apagando...",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getReactApplicationContext(),"Arquivo inexistente!",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getReactApplicationContext(),"Memória externa nao disponivel!", Toast.LENGTH_SHORT).show();
                }
            }else {
                mToast("Comando não reconhecido: " + command);
            }

        }else {

            mToast("Permissão para se trabalhar com a memória externa negada");
        }

    }

    // Método utilizado para facilitar a exibição de toasts.
    public void mToast(String msg){

        Toast.makeText(getReactApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}