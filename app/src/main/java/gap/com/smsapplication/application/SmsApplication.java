package gap.com.smsapplication.application;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

public class SmsApplication extends Application {
    private static SmsApplication instance;
    private Typeface typeFaceNormal = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized SmsApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    public Typeface getNormalTypeFace() {
        if (typeFaceNormal == null) {
            typeFaceNormal = Typeface.createFromAsset(getContext().getAssets(), "YekanBakhBold.ttf");
        }

        return typeFaceNormal;
    }
}
