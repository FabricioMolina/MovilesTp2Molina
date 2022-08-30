package com.ragnar.tp2molina;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class SmsService extends Service {
    private Timer timer= new Timer();
    public SmsService(){}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                leerSms();
            }
        }, 9000,9000);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        Log.d("Resultado","No se mostraran mas mensajes.");
    }

    public void leerSms() {
        Uri sms = Uri.parse("content://sms/inbox");
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(sms, null, null, null, null);

        if (cursor.getCount() > 5) {
            int colBody = cursor.getColumnIndex("body");
            int colPersona = cursor.getColumnIndex("address");
            for(int i=0; i<5;i++){
                cursor.moveToNext();
                String body = cursor.getString(colBody);
                String persona = cursor.getString(colPersona);
                Log.d("resultado ", persona + " " + body);

            }
        }
    }
}