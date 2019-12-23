package com.zcc.frame.image;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.Animatable2Compat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.webp.decoder.WebpDrawable;
import com.bumptech.glide.integration.webp.decoder.WebpDrawableTransformation;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;
import com.zcc.frame.R;
import com.zcc.frame.base.BaseActivity;

/**
 * Company:xxxxx
 * ClassName: ImageTestActivity
 * Author: zcc
 * Date: 2019-11-25 18:05
 * Description:各大图片加载库测评
 */
public class ImageTestActivity extends BaseActivity {
    private ImageView imageView;
    private RadioGroup radioGroup;
    private RadioButton radioButtonGlide;
    private RadioButton radioButtonFresco;
    private RadioButton radioButtonUIL;
    private RadioButton radioButtonPicasso;
    private TextView textView;
    private String url;
    private SimpleDraweeView simpleDraweeView;
    private ImageLoader loader;

    private void assignViews() {
        loader = ImageLoader.getInstance();
        url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575268866291&di=629025d26bd62d854802e26d84367680&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2018-01-17%2F5a5f18ee1db58.jpg";
        simpleDraweeView = findViewById(R.id.iv_simple);
        radioGroup = findViewById(R.id.radioGroup);
        imageView = findViewById(R.id.imageView);
        radioButtonGlide = findViewById(R.id.radioButtonGlide);
        radioButtonFresco = findViewById(R.id.radioButtonFresco);
        radioButtonUIL = findViewById(R.id.radioButtonUIL);
        radioButtonPicasso = findViewById(R.id.radioButtonPicasso);
        textView = findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void bindLayout() {
        setContentView(R.layout.activity_image_test);
    }

    @Override
    public void initView() {
        assignViews();
    }

    @Override
    public void setListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonFresco:
                        setImageFresco();
                        break;
                    case R.id.radioButtonUIL:
                        setImageUIL();
                        break;
                    case R.id.radioButtonPicasso:
                        setImagePicasso();
                        break;
                    case R.id.radioButtonGlide:
                        setImageGlide();
                        break;
                }
            }
        });
    }

    /**
     * compile 'com.github.bumptech.glide:glide:4.8.0'
     * annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'
     * 4.10.0有问题后期再处理
     */
    private void setImageGlide() {
        Glide.with(this).load(url).into(imageView);
        textView.setText("setImageGlide");
    }

    /**
     * compile 'com.squareup.picasso:picasso:2.71828'
     */
    private void setImagePicasso() {
        //network
        Picasso.get().load(url).into(imageView);
        textView.setText("setImagePicasso");
    }

    /**
     * compile 'com.nostra13.universalimageloader:universal-image-loader:1.9.5'
     */
    private void setImageUIL() {
        loader.displayImage(url, imageView);
        textView.setText("setImageUIL");
    }

    /**
     * compile 'com.facebook.fresco:fresco:2.0.0'
     */
    private void setImageFresco() {
        Uri uri = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.mipmap.gene_anim_end);

        final DraweeController kevController = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        simpleDraweeView.setController(kevController);
        Glide.with(this).load(R.mipmap.gene_anim_end).into(imageView);
//        simpleDraweeView.setImageResource(R.mipmap.gene_anim_end);
//        simpleDraweeView.setImageURI(url);
        textView.setText("setImageFresco");
    }

    private void setLoopCount(WebpDrawable resource, int count) {
        WebpDrawable webpDrawable = resource;
        webpDrawable.setLoopCount(count);
        webpDrawable.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationStart(Drawable drawable) {
                super.onAnimationStart(drawable);
            }

            @Override
            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);

            }
        });
    }

    @Override
    public void doBusiness() {
        setImageFresco();
    }
}
