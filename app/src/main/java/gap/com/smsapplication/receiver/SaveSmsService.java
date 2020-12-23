package gap.com.smsapplication.receiver;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;

import androidx.annotation.Nullable;

import gap.com.smsapplication.constant.Constants;

public class SaveSmsService extends IntentService {

    public SaveSmsService() {
        super("SaveService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String senderNo = intent.getStringExtra("sender_no");
        String message = intent.getStringExtra("message");
        long time = intent.getLongExtra("date", 0);
        ContentValues values = new ContentValues();
        values.put("address", senderNo);
        values.put("body", message);
        values.put("date_sent", time);
        ContentResolver contentResolver = getContentResolver();
        contentResolver.insert(Constants.ALL_SMS_URI, values);
        Intent i = new Intent("android.intent.action.MAIN").putExtra("new_sms", true);
        this.sendBroadcast(i);
    }
}
