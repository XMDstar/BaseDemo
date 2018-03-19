package com.zcc.frame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 使用point和canvas画一个笑脸
 * Created by zcc on 2018/3/16.
 */

public class SmileView extends View {
    private int x,y;
    private Paint mPaint,mPaint1,mPaint2;
    private RectF rectF;
    public SmileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        x=MeasureSpec.getSize(widthMeasureSpec);
        y=MeasureSpec.getSize(heightMeasureSpec);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint1 = new Paint();
        mPaint1.setAntiAlias(true);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint1.setColor(Color.BLACK);
        mPaint2 = new Paint();
        mPaint2.setStrokeWidth(8);
        mPaint2.setAntiAlias(true);
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setColor(Color.WHITE);
        rectF=new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawCircle(x/2, y/2, x/2, mPaint);
        canvas.drawCircle(x/4, y/4, x/12, mPaint1);
        canvas.drawCircle(3*x/4, y/4, x/12, mPaint1);
        rectF.set(x/4,y/4,3*x/4,3*y/4);
        canvas.drawArc(rectF,0,180,false,mPaint2);
    }
}
