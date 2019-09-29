package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.MineStudentResponse;

import java.util.List;

/**
 * 我的学生adapter
 */
public class MineStudentAdapter extends BaseQuickAdapter<MineStudentResponse, BaseViewHolder> {

    public MineStudentAdapter(@Nullable List<MineStudentResponse> data) {
        super(R.layout.item_mine_student, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MineStudentResponse item) {
        helper.addOnClickListener(R.id.student_avatar_iv).addOnClickListener(R.id.item_mine_student_layout);
//        helper.setText(R.id.date_name, item.name);
//        if (item.check) {
//            helper.setVisible(R.id.day_check_iv, true);
//        } else {
//            helper.setVisible(R.id.day_check_iv, false);
//        }
    }
}
