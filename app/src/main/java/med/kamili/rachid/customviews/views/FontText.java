package med.kamili.rachid.customviews.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import med.kamili.rachid.customviews.R;

public class FontText extends android.support.v7.widget.AppCompatTextView {

    String customFont;

    public FontText(Context context) {
        super(context);
    }

    public FontText(Context context, AttributeSet attrs) {
        super(context, attrs);
        style(context, attrs);
    }

    public FontText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        style(context, attrs);

    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void style(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FontText);
        int cf = a.getInteger(R.styleable.FontText_styleFont, 0);
        int fontName = 0;
        switch (cf) {
            case 1:
                fontName = R.string.Roboto_Bold;
                break;
            case 2:
                fontName = R.string.Roboto_Italic;
                break;
            case 3:
                fontName = R.string.Roboto_Light;
                break;
            case 4:
                fontName = R.string.Roboto_Medium;
                break;
            case 6:
                fontName = R.string.Roboto_Thin;
                break;
            default:
                fontName = R.string.Roboto_Regular;
                break;
        }

        customFont = getResources().getString(fontName);
        Typeface tf = ResourcesCompat.getFont(context, getResId(customFont, R.font.class));
        setTypeface(tf);
        a.recycle();
    }

    public String getCustomFont() {
        return customFont;
    }

    public void setCustomFont(String customFont) {
        List<String> list = Arrays.asList("roboto_bold","roboto_medium","roboto_light","roboto_regular","roboto_thin","roboto_italic");
        if (list.contains(customFont)){
            this.customFont = customFont;
            Typeface tf = ResourcesCompat.getFont(getContext(), getResId(customFont, R.font.class));
            setTypeface(tf);
            invalidate();
        }
    }
}
