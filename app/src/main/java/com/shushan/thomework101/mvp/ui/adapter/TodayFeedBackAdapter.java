package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.constants.Constant;
import com.shushan.thomework101.entity.response.FeedBackResponse;
import com.shushan.thomework101.help.ImageLoaderHelper;
import com.shushan.thomework101.mvp.views.CircleImageView;

import java.util.List;

/**
 * 我的学生adapter
 */
public class TodayFeedBackAdapter extends BaseQuickAdapter<FeedBackResponse.DataBean, BaseViewHolder> {

    private ImageLoaderHelper mImageLoaderHelper;

    public TodayFeedBackAdapter(@Nullable List<FeedBackResponse.DataBean> data, ImageLoaderHelper imageLoaderHelper) {
        super(R.layout.item_feedback_today, data);
        mImageLoaderHelper = imageLoaderHelper;
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedBackResponse.DataBean item) {
        helper.addOnClickListener(R.id.student_avatar_iv).addOnClickListener(R.id.look_counselling_content_tv).addOnClickListener(R.id.edit_counselling_content_tv);
        //查看辅导内容    填写辅导反馈
        //查看辅导内容    已反馈（不能再次修改）
        CircleImageView circleImageView = helper.getView(R.id.student_avatar_iv);
        mImageLoaderHelper.displayImage(mContext, item.getCover(), circleImageView, Constant.LOADING_AVATOR);
        String gradeVersionValue = item.getGrade() + " " + item.getVersion();
        helper.setText(R.id.student_name_tv, item.getName()).setText(R.id.textbook_version_tv, gradeVersionValue);
        //0未填写反馈1已填写
        if(item.getStatus()==1){
            helper.setText(R.id.edit_counselling_content_tv,"已填写");
        }else {
            helper.setText(R.id.edit_counselling_content_tv,"填写辅导反馈");
        }

    }
}
