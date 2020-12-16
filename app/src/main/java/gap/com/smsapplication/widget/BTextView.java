package gap.com.smsapplication.widget;

import android.content.Context;
import android.util.AttributeSet;

import gap.com.smsapplication.application.SmsApplication;
import gap.com.smsapplication.utils.Util;


/**
 * Created by mahdi on 7/18/2016 AD.
 */
public class BTextView extends androidx.appcompat.widget.AppCompatTextView {

    public BTextView(Context context) {
        super(context);
        init();
    }

    public BTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            setTypeface(SmsApplication.getInstance().getNormalTypeFace());
            // int size = SmsApplication.getInstance().getSharedPreferences().getInt(Constant.PREF_FONT_SIZE, -1);
        }
    }

    public void removeTypeface() {
        if (!isInEditMode())
            setTypeface(null);
    }

    public void setText(CharSequence text, BufferType type) {
        super.setText(Util.latinNumberToPersian(text.toString()), type);
    }
}
