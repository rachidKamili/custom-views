package med.kamili.rachid.customviews.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class CustomLayout extends RelativeLayout implements View.OnTouchListener {

    private List<View> views = new ArrayList<>();

    public CustomLayout(Context context) {
        super(context);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setOnTouchListener(this);
            views.add(view);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_MOVE) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.leftMargin = (int)event.getRawX() - layoutParams.width / 2;
            layoutParams.topMargin = (int)event.getRawY() - layoutParams.height;
            view.setLayoutParams(layoutParams);
        }
        invalidate();
        return true;
    }
}
