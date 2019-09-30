package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.StudentChangeRecordResponse;

import java.util.List;

/**
 * 学生变动记录 adapter
 */
public class StudentChangeRecordAdapter extends BaseQuickAdapter<StudentChangeRecordResponse, BaseViewHolder> {

    public StudentChangeRecordAdapter(@Nullable List<StudentChangeRecordResponse> data) {
        super(R.layout.item_student_change_record, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudentChangeRecordResponse item) {
//        helper.addOnClickListener(R.id.item_expected_income_layout);
        //如果学生状态转入 背景用 student_change_in_bg   转出 用 student_change_out_bg

    }
}
