package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.CustomerResponse;

import java.util.List;

/**
 * 客服adapter
 */
public class CustomerAdapter extends BaseQuickAdapter<CustomerResponse, BaseViewHolder> {


    public CustomerAdapter(@Nullable List<CustomerResponse> data) {
        super(R.layout.item_customer, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerResponse item) {
        helper.addOnClickListener(R.id.item_customer_layout);
        helper.setImageResource(R.id.icon_iv, item.icon).setText(R.id.name_tv, item.name).setText(R.id.contact_number_tv, item.contactNumber);
    }
}
