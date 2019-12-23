package com.zcc.frame.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Copyright:更美互动信息科技有限公司
 * FileName: DefectImageView
 * Author: zcc
 * Date: 2019-12-04 11:45
 * Description: 绘制不完全imageview
 */
public class DefectImageView extends android.support.v7.widget.AppCompatImageView {
    private Paint paint;

    public DefectImageView(Context context) {
        super(context);
    }

    public DefectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void upload() {
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        paint.setStyle(Paint.Style.FILL);//画笔属性是空心圆
        paint.setStrokeWidth(8);//设置画笔粗细
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 200, paint);
    }
}
