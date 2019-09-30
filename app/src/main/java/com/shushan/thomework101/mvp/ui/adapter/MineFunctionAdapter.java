package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.MineFunctionResponse;

import java.util.List;

/**
 * 我的Fragment 功能 adapter
 */
public class MineFunctionAdapter extends BaseQuickAdapter<MineFunctionResponse, BaseViewHolder> {

    public MineFunctionAdapter(@Nullable List<MineFunctionResponse> data) {
        super(R.layout.item_mine_function, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MineFunctionResponse item) {
        helper.addOnClickListener(R.id.item_mine_function_layout);
        helper.setText(R.id.text, item.name);
        helper.setImageResource(R.id.icon_iv, item.icon);
    }
}
