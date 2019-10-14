package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.RevenueIncomeResponse;

import java.util.List;

/**
 * 已到手金额明细 adapter
 */
public class RevenueIncomeAdapter extends BaseQuickAdapter<RevenueIncomeResponse.DataBean, BaseViewHolder> {

    public RevenueIncomeAdapter(@Nullable List<RevenueIncomeResponse.DataBean> data) {
        super(R.layout.item_expected_income, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RevenueIncomeResponse.DataBean item) {
//        helper.addOnClickListener(R.id.item_expected_income_layout);
        //如果收益为正 颜色是 add_income_bg  否则为#303030

    }
}
