package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.MineBankCardResponse;
import com.shushan.thomework101.mvp.utils.BankUtil;

import java.util.List;

/**
 * 银行卡adapter
 */
public class BankCardAdapter extends BaseQuickAdapter<MineBankCardResponse.DataBean, BaseViewHolder> {

    public BankCardAdapter(@Nullable List<MineBankCardResponse.DataBean> data) {
        super(R.layout.item_bank_card, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MineBankCardResponse.DataBean item) {
        helper.addOnClickListener(R.id.item_bank_layout);
        ImageView imageView = helper.getView(R.id.bank_icon_iv);
        BankUtil.labelToBankIcon(item.getLabel(), imageView, helper.getView(R.id.bank_layout));
        helper.setText(R.id.bank_name_tv, item.getBank()).setText(R.id.card_num_tv, BankUtil.getBankHidedNum(item.getCard_no()));
    }
}
