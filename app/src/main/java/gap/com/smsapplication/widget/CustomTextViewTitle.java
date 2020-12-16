package gap.com.smsapplication.widget;



import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import gap.com.smsapplication.utils.FontCache;

public class CustomTextViewTitle extends androidx.appcompat.widget.AppCompatTextView {
    private TextView textView;

    public CustomTextViewTitle(Context context) {
        super(context);
        applyCustomFont(context);
        singleLine(context);
    }

    public CustomTextViewTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
        singleLine(context);
    }

    public CustomTextViewTitle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
        singleLine(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("YekanBakhBold.ttf", context);
        setTypeface(customFont, Typeface.BOLD);

    }

    private void singleLine(Context context) {
        textView = new TextView(context);
        textView.setSingleLine(false);
    }
}
