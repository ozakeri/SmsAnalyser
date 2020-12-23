package gap.com.smsapplication.receiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.Arrays;
import java.util.Objects;

import gap.com.smsapplication.MainActivity;
import gap.com.smsapplication.R;
import gap.com.smsapplication.constant.Constants;

import static gap.com.smsapplication.MainActivity.TAG;

public final class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), "android.provider.Telephony.SMS_RECEIVED")) {
            Log.e(TAG, "====smsReceiver====");
            Log.e(TAG, "====intent====" + intent.getExtras());

            Bundle bundle = intent.getExtras();
            Log.e(TAG, "====bundle====" + bundle);

            if (bundle != null) {
                Object[] pdu_Objects = (Object[]) bundle.get("pdus");
                Log.e(TAG, "====pdu_Objects====" + Arrays.toString(pdu_Objects));
                if (pdu_Objects != null) {
                    Object[] var7 = pdu_Objects;
                    int var8 = pdu_Objects.length;
                    for (int var6 = 0; var6 < var8; ++var6) {
                        Object aObject = var7[var6];
                        SmsMessage currentSMS = this.getIncomingMessage(aObject, bundle);
                        String senderNo = currentSMS.getDisplayOriginatingAddress();
                        String message = currentSMS.getDisplayMessageBody();

                        Intent intent1 = new Intent(context, MainActivity.class);
                        sendNotification(context, senderNo, message,intent1);


                       // issueNotification(context, senderNo, message);
                        saveSmsInInbox(context, currentSMS);
                    }

                    abortBroadcast();
                }
            }
        }
    }

    private void issueNotification(Context context, String senderNo, String message) {
        Log.e(TAG, "====issueNotification====" + senderNo + message);
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.ic_launcher);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setLargeIcon(icon)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(senderNo)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setAutoCancel(true)
                .setContentText(message);

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(Constants.CONTACT_NAME, senderNo);
        intent.putExtra(Constants.FROM_SMS_RECIEVER, true);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(101, mBuilder.build());
    }

    private void saveSmsInInbox(Context context, SmsMessage sms) {
        Intent serviceIntent = new Intent(context, SaveSmsService.class);
        serviceIntent.putExtra("sender_no", sms.getDisplayOriginatingAddress());
        serviceIntent.putExtra("message", sms.getDisplayMessageBody());
        serviceIntent.putExtra("date", sms.getTimestampMillis());
        context.startService(serviceIntent);
    }

    public SmsMessage getIncomingMessage(Object object, Bundle bundle) {
        SmsMessage smsMessage;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String format = bundle.getString("format");
            smsMessage = SmsMessage.createFromPdu((byte[]) object, format);
        } else {
            smsMessage = SmsMessage.createFromPdu((byte[]) object);
        }
        return smsMessage;
    }

    private void sendNotification(Context context,String title,String body,Intent intent) {

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = "default_notification_channel_id";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
