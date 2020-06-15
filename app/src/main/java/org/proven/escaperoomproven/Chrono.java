package org.proven.escaperoomproven;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;


public class Chrono extends Service {

    private Timer temporizador = new Timer();
    private static final long INTERVALO_ACTUALIZACION = 10; // En ms
    public static GameActivity UPDATE_LISTENER;//
    private double cronometro = 100;
    private Handler handler;

    /***
     * Establece la activity que va a recibir los datos del servico (las actualizaciones del cronometro)
     *
     * @param M1
     */
    public static void setUpdateListener(GameActivity M1) {
        UPDATE_LISTENER = M1;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                UPDATE_LISTENER.actualizarCronometro(cronometro);//llamada desde el servicio a la activity
            }

        };
        chronoIncrement();
    }

    @Override
    public void onDestroy() {
        pararCronometro();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    private void chronoIncrement() {
        temporizador.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                cronometro = cronometro - 0.01;//suma
                handler.sendEmptyMessage(0);
                
            }
        }, 0, INTERVALO_ACTUALIZACION);
    }

    private void pararCronometro() {
        if (temporizador != null)
            temporizador.cancel();
    }

}
