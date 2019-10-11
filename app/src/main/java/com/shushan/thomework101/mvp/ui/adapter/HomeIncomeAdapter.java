package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.HomeIncomeResponse;

import java.util.List;

/**
 * 首页 -我的收益
 */
public class HomeIncomeAdapter extends BaseQuickAdapter<HomeIncomeResponse, BaseViewHolder> {

    public HomeIncomeAdapter(@Nullable List<HomeIncomeResponse> data) {
        super(R.layout.item_home_income, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeIncomeResponse item) {
//        helper.addOnClickListener(R.id.item_home_income_layout);
        helper.setText(R.id.income_title,item.title).setText(R.id.money_tv,String.valueOf(item.money));
        helper.setBackgroundRes(R.id.income_bg_icon_iv,item.bgIcon);
    }
}
