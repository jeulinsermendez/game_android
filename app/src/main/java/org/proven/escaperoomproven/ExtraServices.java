package org.proven.escaperoomproven;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Timer;

public class ExtraServices extends Service {

    private Handler handler;
    private static final long TIME_INTERVAL = 2000;
    public static GameActivity UPDATE_LISTENER;
    private double crono = 10;
    private Timer timer = new Timer();


    public static void setUpdateListener(GameActivity gameActivity){
        UPDATE_LISTENER = gameActivity;
    }




    @Override
    public void onCreate(){
        super.onCreate();
        handler = new Handler(){
        };

    }

    @Override
    public void onDestroy(){

    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
