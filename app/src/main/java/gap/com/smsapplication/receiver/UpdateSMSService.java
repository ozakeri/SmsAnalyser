package gap.com.smsapplication.receiver;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.Nullable;

public class UpdateSMSService extends IntentService {
    /**
     * @param name
     * @deprecated
     */
    public UpdateSMSService() {
        super("UpdateSMSReceiver");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            markSmsRead(intent.getLongExtra("id", -123));
        }
    }

    public void markSmsRead(Long id) {
        ContentValues values = new ContentValues();
        values.put("read", "1");
        ContentResolver contentResolver = getContentResolver();
        contentResolver.update(Uri.parse("content://sms/" + id), values, null, null);
    }
}
