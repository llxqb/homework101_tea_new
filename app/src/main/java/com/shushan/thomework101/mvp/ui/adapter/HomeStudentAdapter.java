package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.HomeIncomeResponse;

import java.util.List;

/**
 * 首页 -我的学生
 */
public class HomeStudentAdapter extends BaseQuickAdapter<HomeIncomeResponse, BaseViewHolder> {
    public HomeStudentAdapter(@Nullable List<HomeIncomeResponse> data) {
        super(R.layout.item_home_student, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeIncomeResponse item) {
        helper.setText(R.id.student_title, item.title).setText(R.id.student_num_tv, String.valueOf(item.num));
        helper.setBackgroundRes(R.id.student_bg_icon_iv, item.bgIcon);
    }
}
