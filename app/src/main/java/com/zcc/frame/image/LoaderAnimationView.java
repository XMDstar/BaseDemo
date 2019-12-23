package com.zcc.frame.image;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * Copyright:更美互动信息科技有限公司
 * FileName: LoaderAnimationView
 * Author: zcc
 * Date: 2019-12-04 15:13
 * Description: 动画
 */
public class LoaderAnimationView extends View {

    private Paint mPaint;

    private int mWidth;
    private int mHeight;

    private float mCenterRadius;
    private int mCenterX;
    private int mCenterY;

    private float mSmallCircleRadius;

    private int mCircleCount;
    //角度差
    private float mAngleDiff = 0F;
    //每一份圆的角度占比
    private double mPercentAngle;

    //扩散半径
    private float mSpreadRadius;
    //屏幕对角线
    private float mDiagonal;

    // 旋转动画
    private ValueAnimator mRotateAnimator;
    // 汇聚动画
    private ValueAnimator mConvergeAnimator;
    // 圆的扩散动画
    private ValueAnimator mSpreadAnimator;

    private boolean isSpread = false;

    private int mBackGround;

    //6种颜色，绘制6个不同颜色的小圆
    private int[] mColors = new int[]{
            getResources().getColor(android.R.color.holo_red_dark),
            getResources().getColor(android.R.color.holo_orange_dark),
            getResources().getColor(android.R.color.holo_blue_dark),
            getResources().getColor(android.R.color.holo_green_dark),
            getResources().getColor(android.R.color.holo_purple),
            getResources().getColor(android.R.color.darker_gray)
    };

    private void init() {
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);

        mBackGround = Color.parseColor("#ffffff");

        mCircleCount = mColors.length;
        //计算每一个小圆对应的角度
        mPercentAngle = 2 * Math.PI / mCircleCount;

        mWidth = getResources().getDisplayMetrics().widthPixels;
        mHeight = getResources().getDisplayMetrics().heightPixels - mWidth / 4;
        mCenterRadius = mWidth / 4;
        mSmallCircleRadius = mCenterRadius / 8;
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;

        mDiagonal = (float) Math.sqrt(Math.pow(mCenterX, 2) + Math.pow(mCenterY, 2));
    }

    public LoaderAnimationView(Context context) {
        this(context, null);
    }

    public LoaderAnimationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoaderAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //开始旋转动画
        startRotateAnimator();
        if (!isSpread) {
            canvas.drawColor(mBackGround);
            drawCircles(canvas);
        } else {
            drawSpreadCircle(canvas);
        }
    }

    private void startRotateAnimator() {
        //从 0 变到 2pi 就是每一个小圆所旋转的差量
        if (mRotateAnimator == null) {
            mRotateAnimator = ObjectAnimator.ofFloat(0, 2 * (float) Math.PI);
            mRotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mAngleDiff = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mRotateAnimator.setDuration(3000);
            mRotateAnimator.setInterpolator(new LinearInterpolator());
            mRotateAnimator.start();

            //监听动画结束事件
            mRotateAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRotateAnimator.cancel();
                    //旋转动画结束后，播放汇聚动画
                    startConvergeAnimator();
                }
            });
        }
    }

    private void startConvergeAnimator() {
        if (mConvergeAnimator == null) {
            mConvergeAnimator = ObjectAnimator.ofFloat(mCenterRadius, 0);
            mConvergeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCenterRadius = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mConvergeAnimator.setDuration(2000);
            mConvergeAnimator.setInterpolator(new AnticipateInterpolator(3f));
            mConvergeAnimator.start();

            mConvergeAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mConvergeAnimator.cancel();
                    //开始扩散动画
                    startSpreadAnimator();
                }
            });
        }
    }

    private void startSpreadAnimator() {
        isSpread = true;
        if (mSpreadAnimator == null) {
            mSpreadAnimator = ObjectAnimator.ofFloat(0, mDiagonal);
            mSpreadAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mSpreadRadius = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mSpreadAnimator.setDuration(5000);
            mSpreadAnimator.start();
        }
    }

    private void drawCircles(Canvas canvas) {
        for (int i = 0; i < mCircleCount; i++) {
            mPaint.setColor(mColors[i]);
            double diff = mAngleDiff + mPercentAngle * i;
            float x = mCenterX + (float) Math.sin(diff) * mCenterRadius;
            float y = mCenterY - (float) Math.cos(diff) * mCenterRadius;
            canvas.drawCircle(x, y, mSmallCircleRadius, mPaint);
        }
    }

    private void drawSpreadCircle(Canvas canvas) {
        mPaint.setColor(mBackGround);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mDiagonal - mSpreadRadius);
        canvas.drawCircle(mCenterX, mCenterY, mDiagonal, mPaint);
    }

}
