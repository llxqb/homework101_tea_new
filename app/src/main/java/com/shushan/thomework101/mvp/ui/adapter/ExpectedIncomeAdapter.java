package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.ExpectedIncomeResponse;

import java.util.List;

/**
 * 预计收益 adapter
 */
public class ExpectedIncomeAdapter extends BaseQuickAdapter<ExpectedIncomeResponse, BaseViewHolder> {

    public ExpectedIncomeAdapter(@Nullable List<ExpectedIncomeResponse> data) {
        super(R.layout.item_expected_income, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExpectedIncomeResponse item) {
//        helper.addOnClickListener(R.id.item_expected_income_layout);
        //如果收益为正 颜色是 add_income_bg  否则为#303030

    }
}
