package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.FeedBackResponse;

import java.util.List;

/**
 * 我的学生adapter
 */
public class TodayFeedBackAdapter extends BaseQuickAdapter<FeedBackResponse.DataBean, BaseViewHolder> {

    public TodayFeedBackAdapter(@Nullable List<FeedBackResponse.DataBean> data) {
        super(R.layout.item_feedback_today, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedBackResponse.DataBean item) {
        helper.addOnClickListener(R.id.student_avatar_iv).addOnClickListener(R.id.look_counselling_content_tv).addOnClickListener(R.id.edit_counselling_content_tv).addOnClickListener(R.id.item_mine_student_layout);
        //查看辅导内容    填写辅导反馈
        //查看辅导内容    已反馈（不能再次修改）
//        helper.setText(R.id.date_name, item.name);
//        if (item.check) {
//            helper.setVisible(R.id.day_check_iv, true);
//        } else {
//            helper.setVisible(R.id.day_check_iv, false);
//        }
    }
}
