package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.CoachingResponse;

import java.util.List;

/**
 * 辅导adapter
 */
public class CoachingAdapter extends BaseQuickAdapter<CoachingResponse, BaseViewHolder> {

    public CoachingAdapter(@Nullable List<CoachingResponse> data) {
        super(R.layout.item_coaching, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoachingResponse item) {
        helper.addOnClickListener(R.id.item_coaching);
    }
}
