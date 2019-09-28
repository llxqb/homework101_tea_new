package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.UnSuccessfulStudentResponse;

import java.util.List;

/**
 * 首页 -未成单学生
 */
public class HomeUnsuccessfulStudentAdapter extends BaseQuickAdapter<UnSuccessfulStudentResponse, BaseViewHolder> {

    public HomeUnsuccessfulStudentAdapter(@Nullable List<UnSuccessfulStudentResponse> data) {
        super(R.layout.item_unsuccessful_student, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UnSuccessfulStudentResponse item) {
        helper.addOnClickListener(R.id.item_unsuccessful_student_layout);
//        helper.setText(R.id.income_title,item.title).setText(R.id.money_tv,String.valueOf(item.money));
//        helper.setBackgroundRes(R.id.income_bg_icon_iv,item.bgIcon);
    }
}
