package com.shushan.thomework101.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: LocalImagesPagerAdapter
 * @Desciption: //本地图片pager适配器
 * @author: zhangshihao
 * @date: 2018-12-17
 */
public class LocalImagesPagerAdapter extends PagerAdapter {

    private int[] resIdArr;
    private Context mContext;
    private List<ImageView> imageViewList;

    public LocalImagesPagerAdapter(Context context, int[] resIdArr){
        mContext = context;
        this.resIdArr = resIdArr;
        initImageViewList();
    }

    private void initImageViewList(){
        imageViewList = new ArrayList<>();
        if(resIdArr != null && resIdArr.length > 0){
            for(int i=0;i<resIdArr.length;i++){
                ImageView imageView = new ImageView(mContext);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setImageResource(resIdArr[i]);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageViewList.add(imageView);
            }
        }
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(imageViewList.get(position));
        return imageViewList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(imageViewList.get(position));
    }

    @Override
    public int getCount() {
        if(resIdArr == null || resIdArr.length == 0){
            return 0;
        }
        return resIdArr.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
