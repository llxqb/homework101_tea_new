package com.shushan.thomework101.help;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.shushan.thomework101.mvp.utils.TranTools;

import javax.inject.Inject;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by li.liu on 2017/7/10.
 * ImageLoaderHelper
 */

public class ImageLoaderHelper extends GlideLoader {

    @Inject
    public ImageLoaderHelper(final Context ctx) {
    }

    public void displayImage(Context context, Object path, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(true)
                .dontAnimate();
        Glide.with(context).load(path).apply(options).into(imageView);
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView, int loadPic) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(loadPic)
                .skipMemoryCache(true)
                .placeholder(loadPic)
                .dontAnimate();
        Glide.with(context).load(path).apply(options).into(imageView);
    }


    @Override
    public void displayImage(Context context, Object path, ImageView imageView, int res, int loadPic) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(loadPic)
                .skipMemoryCache(true)
                .error(res)
                .placeholder(loadPic)
                .dontAnimate();
        Glide.with(context).load(path).apply(options).into(imageView);
    }

    @Override
    public void displayCircularImage(Context context, Object path, ImageView imageView, int loadPic) {
        RequestOptions options = RequestOptions
                .bitmapTransform(new RoundedCorners(TranTools.dip2px(context, 8)))//设置圆角大小
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(loadPic)
                .skipMemoryCache(true)
                .placeholder(loadPic)
                .dontAnimate();
        Glide.with(context).load(path).apply(options).into(imageView);
    }

    @Override
    public void displayRoundedCornerImage(Context context, Object path, ImageView imageView, Integer size, int loadPic) {
        RequestOptions options = RequestOptions
                .bitmapTransform(new RoundedCorners(TranTools.dip2px(context, size)))//设置圆角大小
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(loadPic)
                .placeholder(loadPic)
                .dontAnimate();
        Glide.with(context).load(path).apply(options).into(imageView);
    }

    //设置圆角毛玻璃效果
    public void displayGlassImage(Context context, Object path, ImageView imageView, int loadPic) {
        RequestOptions options = RequestOptions
                .bitmapTransform(new MultiTransformation<>(new BlurTransformation(30, 3)
//                        new RoundedCorners(TranTools.dip2px(context, 8))
                        )
                )// radius 越大越模糊
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(loadPic)
                .placeholder(loadPic)
                .dontAnimate();
        Glide.with(context).load(path).apply(options).into(imageView);
    }

    /**
     * 设置自适应图片
     */
    @Override
    public void displayMatchImage(Context context, Object path, ImageView imageView, int loadPic) {
//        RequestOptions options = new RequestOptions()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .error(loadPic)
//                .skipMemoryCache(true)
//                .placeholder(loadPic)
//                .dontAnimate();
//
//        RequestListener requestListener = new RequestListener<Drawable>() {
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                if (imageView.getScaleType() != ImageView.ScaleType.CENTER_CROP) {
//                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                }
//                ViewGroup.LayoutParams params = imageView.getLayoutParams();
//                int vw = imageView.getWidth() ;//imageView.getWidth()- imageView.getPaddingLeft() - imageView.getPaddingRight()
//                float scale = (float) vw / (float) resource.getIntrinsicWidth();
//                int vh = Math.round(resource.getIntrinsicHeight() * scale);
//                params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
//                imageView.setLayoutParams(params);
//                return false;
//            }
//        };
//        Glide.with(context).load(path).listener(requestListener)
//                .apply(options).into(imageView);
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(loadPic)
                .skipMemoryCache(true)
                .placeholder(loadPic)
                .dontAnimate();
        Glide.with(context).load(path).apply(options).into(imageView);
    }


    /**
     * CustomizeMessageItemProvider使用到
     * 设置圆角毛玻璃效果
     * 静态方法
     */
    public static void displayGlassImage2(View context, Object path, ImageView imageView, int loadPic) {
        RequestOptions options = RequestOptions
                .bitmapTransform(new MultiTransformation<>(new BlurTransformation(30, 3)
//                        new RoundedCorners(TranTools.dip2px(context, 8))
                        )
                )// radius 越大越模糊
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(loadPic)
                .placeholder(loadPic)
                .dontAnimate();
        Glide.with(context).load(path).apply(options).into(imageView);
    }

    /**
     * CustomizeMessageItemProvider使用到
     */
    public static void displayImage2(View context, Object path, ImageView imageView, int loadPic) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(loadPic)
                .skipMemoryCache(true)
                .placeholder(loadPic)
                .dontAnimate();
        Glide.with(context).load(path).apply(options).into(imageView);
    }

}
