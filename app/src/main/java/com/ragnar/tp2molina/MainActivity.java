package com.ragnar.tp2molina;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Button btnIniciar;
    private Button btnFinalizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permisos();
        iniciarVista();
    }
    public void permisos(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_SMS},1000);
        }
    }
    private void iniciarVista() {
        btnIniciar = findViewById(R.id.btnIniciar);
        btnFinalizar = findViewById(R.id.btnFinalizar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciar();
                btnIniciar.setEnabled(false);
                btnFinalizar.setEnabled(true);
            }
        });
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizar();
                btnIniciar.setEnabled(true);
                btnFinalizar.setEnabled(false);
                Log.d("xd","olasdasdasda");
            }
        });
    }
    public void iniciar(){
        Intent intent = new Intent(this, SmsService.class);
        startService(intent);
    }
    public void finalizar(){
        Intent intent = new Intent(this, SmsService.class);
        stopService(intent);
    }


}