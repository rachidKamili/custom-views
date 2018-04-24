package med.kamili.rachid.customviews.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import med.kamili.rachid.customviews.R;

public class Rectangle extends View{
    private float width;
    private float height;
    private int fillColor;
    private TypedArray typedArray;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Rectangle(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Rectangle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Rectangle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void init(Context context, @NonNull AttributeSet attrs, int defStyleRed, int defStyleRes){
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.Rectangle);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint myPaint = new Paint();
        myPaint.setColor(fillColor);
        //myPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0,0, height, width, myPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        fillColor = typedArray.getColor(R.styleable.Rectangle_fillColor, Color.GRAY);
        height = typedArray.getDimension(R.styleable.Rectangle_width, widthMeasureSpec);
        width = typedArray.getDimension(R.styleable.Rectangle_height,heightMeasureSpec );

        float desiredHeightLayout;
        float desiredWidthLayout;

        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == View.MeasureSpec.EXACTLY) {
            desiredWidthLayout = widthSize;
        } else if (widthMode == View.MeasureSpec.AT_MOST) {
            desiredWidthLayout = Math.min(width, widthSize);
        } else {
            desiredWidthLayout = width;
        }

        if (heightMode == View.MeasureSpec.EXACTLY) {
            desiredHeightLayout = heightSize;
        } else if (heightMode == View.MeasureSpec.AT_MOST) {
            desiredHeightLayout = Math.min(height, heightSize);
        } else {
            desiredHeightLayout = height;
        }

        setMeasuredDimension((int)desiredWidthLayout, (int) desiredHeightLayout);
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
        invalidate();
    }

    public float getAppWidth() {
        return width;
    }

    public void setAppWidth(float width) {
        this.width = width;
        invalidate();
    }

    public float getAppHeight() {
        return height;
    }

    public void setAppHeight(float height) {
        this.height = height;
        invalidate();
    }
}
