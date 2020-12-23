package gap.com.smsapplication.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Communicator extends BroadcastReceiver {
    String TAG = "Yes! SMS Receved.";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"Yes! SMS Receved.");
    }
}
