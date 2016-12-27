package br.com.jmsstudio.agenda.receiver;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.jmsstudio.agenda.R;
import br.com.jmsstudio.agenda.dao.AlunoDAO;

/**
 * Created by jms on 27/12/16.
 */
public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
            byte[] pdu = (byte[]) pdus[0];

            SmsMessage smsMessage;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = (String) intent.getSerializableExtra("format");

                smsMessage = SmsMessage.createFromPdu(pdu, format);
            } else {
                smsMessage = SmsMessage.createFromPdu(pdu);
            }

            String telefoneDoSMS = smsMessage.getDisplayOriginatingAddress();

            AlunoDAO dao = new AlunoDAO(context);
            if (dao.existeAlunoComTelefone(telefoneDoSMS)) {
                Toast.makeText(context, "SMS recebido de Aluno!", Toast.LENGTH_SHORT).show();

                MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.msg);
                mediaPlayer.start();
            }
        }
    }
}
