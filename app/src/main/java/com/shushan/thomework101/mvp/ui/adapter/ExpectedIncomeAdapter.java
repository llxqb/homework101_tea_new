package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.constants.Constant;
import com.shushan.thomework101.entity.response.ExpectedIncomeResponse;
import com.shushan.thomework101.help.ImageLoaderHelper;
import com.shushan.thomework101.mvp.utils.DateUtil;
import com.shushan.thomework101.mvp.views.CircleImageView;

import java.util.List;

/**
 * 预计收益 adapter
 */
public class ExpectedIncomeAdapter extends BaseQuickAdapter<ExpectedIncomeResponse.ListBean, BaseViewHolder> {

    private ImageLoaderHelper mImageLoaderHelper;

    public ExpectedIncomeAdapter(@Nullable List<ExpectedIncomeResponse.ListBean> data, ImageLoaderHelper imageLoaderHelper) {
        super(R.layout.item_expected_income, data);
        mImageLoaderHelper = imageLoaderHelper;
    }

    @Override
    protected void convert(BaseViewHolder helper, ExpectedIncomeResponse.ListBean item) {
//        helper.addOnClickListener(R.id.item_expected_income_layout);
        //如果收益为正 颜色是 add_income_bg  否则为#303030
        //data.list.type  1辅导2提成3转班4请假
        CircleImageView circleImageView = helper.getView(R.id.avatar_iv);
        mImageLoaderHelper.displayImage(mContext, item.getCover(), circleImageView, Constant.LOADING_AVATOR);
        String totalMoney = "付费：" + item.getPay_money();
        String comeInMoney = null;
        if (item.getStatus() == 1) {//1收入2支出
            comeInMoney = "+" + item.getMoney();
            helper.setTextColor(R.id.commission_income_hint_tv, mContext.getResources().getColor(R.color.add_income_bg)).setTextColor(R.id.commission_income_tv, mContext.getResources().getColor(R.color.add_income_bg));
        } else if (item.getStatus() == 2) {
            comeInMoney = "-" + item.getMoney();
            helper.setTextColor(R.id.commission_income_hint_tv, mContext.getResources().getColor(R.color.color_30)).setTextColor(R.id.commission_income_tv, mContext.getResources().getColor(R.color.color_30));
        }
        String moneyType = "";
        if (item.getType() == 1) {
            moneyType = "辅导 ";
        } else if (item.getType() == 2) {
            moneyType = "提成 ";
        } else if (item.getType() == 3) {
            moneyType = "转班 ";
        } else if (item.getType() == 4) {
            moneyType = "请假 ";
        }
        helper.setText(R.id.username_tv, item.getName()).setText(R.id.date_tv, DateUtil.getStrTime(item.getCreate_time(), DateUtil.TIME_YYMMDD));
        helper.setText(R.id.money_tv, totalMoney);
        helper.setText(R.id.commission_income_hint_tv, moneyType).setText(R.id.commission_income_tv, comeInMoney);
    }
}
