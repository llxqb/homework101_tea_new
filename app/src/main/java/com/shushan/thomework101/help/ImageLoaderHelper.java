package com.shushan.thomework101.help;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.shushan.thomework101.mvp.utils.TranTools;

import javax.inject.Inject;

/**
 * Created by li.liu on 2017/7/10.
 * ImageLoaderHelper
 */

public class ImageLoaderHelper extends GlideLoader {

    @Inject
    public ImageLoaderHelper(final Context ctx) {
    }

    @Override
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


    /**
     * 设置自适应图片
     */
    @Override
    public void displayMatchImage(Context context, Object path, ImageView imageView, int loadPic) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(loadPic)
                .skipMemoryCache(true)
                .placeholder(loadPic)
                .dontAnimate();
        Glide.with(context).load(path).apply(options).into(imageView);
    }


}
