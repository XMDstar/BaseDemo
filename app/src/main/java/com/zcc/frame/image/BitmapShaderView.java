package com.zcc.frame.image;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;


/**
 * Copyright:更美互动信息科技有限公司
 * FileName: BitmapShaderView
 * Author: zcc
 * Date: 2019-12-11 16:23
 * Description: AI加载数据动画
 */
public class BitmapShaderView extends View {
    private Paint paint;
    private Bitmap bgBitmap, bitmap;
    private int mX, mY, mRadius;
    private Path mPath;
    private PathMeasure mPathMeasure;
    private float mAnimatorValue;
    private Path mDst;
    private float mLength;
    private int mStrokeWidth;
    private ValueAnimator valueAnimator;
    private AnimCallback callback;
    private boolean isDraw = false;

    public BitmapShaderView(Context context) {
        super(context);
    }

    public BitmapShaderView setBitmap(Bitmap bitmap) {
        this.bgBitmap = null;
        this.cancel = false;
        this.bitmap = bitmap;
        if (bitmap != null) {
            isDraw = true;
            init();
        }
        return this;
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        this.mX = dm.widthPixels / 2;
        this.mY = dm.heightPixels / 2;
        this.mStrokeWidth = dm.widthPixels / 2;
        this.mRadius = dm.widthPixels / 5;
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        this.paint = new Paint();
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(mStrokeWidth);
        this.paint.setStrokeCap(Paint.Cap.ROUND);

        this.mPathMeasure = new PathMeasure();
        this.mPath = new Path();
        this.mPath.addCircle(mX, mY, mRadius, Path.Direction.CW);
        this.mPathMeasure.setPath(mPath, true);
        this.mLength = mPathMeasure.getLength();
        this.mDst = new Path();
    }

    public void setCancelGeneAnimListener(AnimCallback animListener) {
        this.callback = animListener;
    }

    public void startAnim() {
        valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                if (cancel) {
                    valueAnimator.cancel();
                    if (callback != null) {
                        callback.cancelGeneAnim(mX, mY);
                    }
                }
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isDraw) return;
        if (this.bgBitmap == null) {
            this.bgBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);//创建一个新空白位图
            Canvas canvasBg = new Canvas(this.bgBitmap);
            canvasBg.drawBitmap(this.bitmap, null, new Rect(0, 0, getWidth(), getHeight()), this.paint);
        }
        this.paint.setShader(new BitmapShader(this.bgBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));

        mDst.reset();
        float stop = mLength * mAnimatorValue;
        float start = stop - 1;
        mPathMeasure.getSegment(start, stop, mDst, true);
        canvas.drawPath(mDst, paint);
    }

    private boolean cancel = false;

    public void endAnim() {
        cancel = true;
    }

    public void breakAnim() {
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    public interface AnimCallback {
        void cancelGeneAnim(int x, int y);
    }
}