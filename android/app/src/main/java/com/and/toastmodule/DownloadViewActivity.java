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
 * Created by Administrador on 07/11/2017.
 */

public class DownloadViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_view);

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File imageFile = new File(root, Uri.parse(getIntent().getStringExtra("link")).getLastPathSegment());
            if (imageFile.exists()) {
                ImageView imageview = (ImageView) findViewById(R.id.imagemDownload);
                imageview.setImageURI(Uri.parse("file://" + imageFile.getAbsolutePath()));
            }
            else {
                Toast.makeText(this, "Arquivo nao existe", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Armazenamento externo nao esta montado...", Toast.LENGTH_SHORT).show();
        }
    }
}
