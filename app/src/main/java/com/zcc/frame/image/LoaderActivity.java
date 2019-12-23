package com.zcc.frame.image;

import android.animation.Animator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.zcc.frame.R;

public class LoaderActivity extends AppCompatActivity {
    private BitmapShaderView iv_shader;
    private ImageView iv_circular_reveal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        iv_shader = findViewById(R.id.iv_shader);
        iv_circular_reveal = findViewById(R.id.iv_crcular_reveal);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_shader);
        iv_shader.setBitmap(bitmap);
        iv_shader.startAnim();
        final DisplayMetrics dm = this.getResources().getDisplayMetrics();
        final int centerX = dm.widthPixels / 2;
        final int centerY = dm.heightPixels / 2;
        iv_circular_reveal.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animator anim = ViewAnimationUtils.createCircularReveal(
                        iv_circular_reveal,
                        centerX,
                        centerY,
                        (float) Math.hypot(dm.widthPixels, dm.heightPixels), 0);
                anim.setDuration(2000);
                anim.setInterpolator(new AccelerateDecelerateInterpolator());
                anim.start();
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        iv_circular_reveal.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        }, 1000);
    }
}
