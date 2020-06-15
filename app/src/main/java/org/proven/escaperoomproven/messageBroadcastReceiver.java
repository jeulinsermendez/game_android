package org.proven.escaperoomproven;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class messageBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("ReceptorSMS", "SMS recibido");

        Bundle b = intent.getExtras();

        String broadcastType = intent.getDataString();




        if (b != null) {
            Toast.makeText(context, "SMS.", Toast.LENGTH_SHORT).show();

            Object[] pdus = (Object[]) b.get("pdus");

            SmsMessage[] mensajes = new SmsMessage[pdus.length];

            for (int i = 0; i < mensajes.length; i++) {
                mensajes[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

                String idMensaje = mensajes[i].getOriginatingAddress();
                String textoMensaje = mensajes[i].getMessageBody();
                Toast.makeText(context, "REMITENTE: " + idMensaje, Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Mensaje: " + textoMensaje, Toast.LENGTH_SHORT).show();
               /* Log.i("ReceptorSMS", "Remitente: " + idMensaje);
                Log.i("ReceptorSMS", "Mensaje: " + textoMensaje);*/
            }

        }
    }
}
