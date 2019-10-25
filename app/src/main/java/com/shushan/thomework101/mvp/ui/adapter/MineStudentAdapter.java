package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.constants.Constant;
import com.shushan.thomework101.entity.response.MineStudentResponse;
import com.shushan.thomework101.help.ImageLoaderHelper;
import com.shushan.thomework101.mvp.views.CircleImageView;

import java.util.List;

/**
 * 我的学生adapter
 */
public class MineStudentAdapter extends BaseQuickAdapter<MineStudentResponse.DataBean, BaseViewHolder> {
    private ImageLoaderHelper mImageLoaderHelper;

    public MineStudentAdapter(@Nullable List<MineStudentResponse.DataBean> data, ImageLoaderHelper imageLoaderHelper) {
        super(R.layout.item_mine_student, data);
        mImageLoaderHelper = imageLoaderHelper;
    }

    @Override
    protected void convert(BaseViewHolder helper, MineStudentResponse.DataBean item) {
        helper.addOnClickListener(R.id.student_avatar_iv).addOnClickListener(R.id.item_mine_student_layout);
        CircleImageView circleImageView = helper.getView(R.id.student_avatar_iv);
        mImageLoaderHelper.displayImage(mContext, item.getCover(), circleImageView, Constant.LOADING_AVATOR);
        String versionValue = item.getGrade() + " " + item.getVersion();
        helper.setText(R.id.student_name_tv, item.getName()).setText(R.id.textbook_version_tv, versionValue);
        helper.setText(R.id.counselling_type_tv, item.getStatus());
        helper.setText(R.id.studengt_remarks_tv, !TextUtils.isEmpty(item.getRemark()) ? "备注：" + item.getRemark() : "");
    }
}
