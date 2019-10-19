package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.constants.Constant;
import com.shushan.thomework101.entity.response.RevenueIncomeResponse;
import com.shushan.thomework101.help.ImageLoaderHelper;
import com.shushan.thomework101.mvp.utils.DateUtil;
import com.shushan.thomework101.mvp.views.CircleImageView;

import java.util.List;

/**
 * 已到手金额明细 adapter
 */
public class RevenueIncomeAdapter extends BaseQuickAdapter<RevenueIncomeResponse.DataBean, BaseViewHolder> {

    private ImageLoaderHelper mImageLoaderHelper;

    public RevenueIncomeAdapter(@Nullable List<RevenueIncomeResponse.DataBean> data, ImageLoaderHelper imageLoaderHelper) {
        super(R.layout.item_expected_income, data);
        mImageLoaderHelper = imageLoaderHelper;
    }

    @Override
    protected void convert(BaseViewHolder helper, RevenueIncomeResponse.DataBean item) {
//        helper.addOnClickListener(R.id.item_expected_income_layout);
        //如果收益为正 颜色是 add_income_bg  否则为#303030
        CircleImageView circleImageView = helper.getView(R.id.avatar_iv);
        mImageLoaderHelper.displayImage(mContext, item.getCover(), circleImageView, Constant.LOADING_AVATOR);
        String totalMoney = "付费：" + item.getPay_money();
        String montyType = "";
        if(item.getType()==1){//1日辅导2日提成
            montyType = "日提成 ";
        }else if(item.getType()==2){
            montyType = "日辅导 ";
        }
        helper.setText(R.id.username_tv, item.getName()).setText(R.id.date_tv, DateUtil.getStrTime(item.getCreate_time(), DateUtil.TIME_YYMMDD));
        helper.setText(R.id.money_tv, totalMoney);
        helper.setText(R.id.commission_income_hint_tv, montyType).setText(R.id.commission_income_tv, "+"+item.getMoney());
    }
}
