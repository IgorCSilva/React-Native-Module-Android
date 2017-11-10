package com.and.toastmodule;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.and.R;

import java.io.File;

/**
 * Created by Administrador on 09/11/2017.
 */

public class ViewImage extends AppCompatActivity {

    ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_view);

        imagem = (ImageView) findViewById(R.id.imagemDownload);

        File f = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), getIntent().getStringExtra("arquivo"));
        if(f.exists()){
            imagem.setImageURI(Uri.parse("file://" + f.getAbsolutePath()));
        }else {
            Toast.makeText(getApplicationContext(), "Arquivo não existe!", Toast.LENGTH_SHORT).show();
        }

    }
}
