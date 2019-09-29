package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.DateResponse;

import java.util.List;

/**
 * 辅导adapter
 */
public class DateAdapter extends BaseQuickAdapter<DateResponse, BaseViewHolder> {

    public DateAdapter(@Nullable List<DateResponse> data) {
        super(R.layout.item_counselling_date, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DateResponse item) {
        helper.addOnClickListener(R.id.item_counselling_date);
        helper.setText(R.id.date_name, item.name);
        if (item.check) {
            helper.setVisible(R.id.day_check_iv, true);
        } else {
            helper.setVisible(R.id.day_check_iv, false);
        }
    }
}
