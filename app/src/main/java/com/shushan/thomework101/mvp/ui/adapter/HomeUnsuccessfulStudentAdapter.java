package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.constants.Constant;
import com.shushan.thomework101.entity.response.HomeResponse;
import com.shushan.thomework101.help.ImageLoaderHelper;
import com.shushan.thomework101.mvp.utils.DateUtil;
import com.shushan.thomework101.mvp.views.CircleImageView;

import java.util.List;

/**
 * 首页 -未成单学生
 */
public class HomeUnsuccessfulStudentAdapter extends BaseQuickAdapter<HomeResponse.OrderBean, BaseViewHolder> {

    private ImageLoaderHelper mImageLoaderHelper;

    public HomeUnsuccessfulStudentAdapter(@Nullable List<HomeResponse.OrderBean> data, ImageLoaderHelper imageLoaderHelper) {
        super(R.layout.item_unsuccessful_student, data);
        mImageLoaderHelper = imageLoaderHelper;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeResponse.OrderBean item) {
        helper.addOnClickListener(R.id.item_unsuccessful_student_layout);
        CircleImageView circleImageView = helper.getView(R.id.student_avatar_iv);
        mImageLoaderHelper.displayImage(mContext, item.getCover(), circleImageView, Constant.LOADING_AVATOR);
        helper.setText(R.id.student_name_tv, item.getName()).setText(R.id.register_time_tv, DateUtil.getStrTime(item.getCreate_time(), DateUtil.TIME_YYMMDD));
        helper.setText(R.id.student_grade_tv, item.getGrade()).setText(R.id.state_tv, item.getStatus());
    }
}
