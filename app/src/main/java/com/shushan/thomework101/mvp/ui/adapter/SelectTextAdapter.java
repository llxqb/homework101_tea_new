package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.SelectTextResponse;

import java.util.List;

/**
 * 选择年级/科目 adapter
 */
public class SelectTextAdapter extends BaseQuickAdapter<SelectTextResponse, BaseViewHolder> {

    public SelectTextAdapter(@Nullable List<SelectTextResponse> data) {
        super(R.layout.item_grade, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectTextResponse item) {
        helper.addOnClickListener(R.id.item_grade_layout);
        helper.setText(R.id.grade_tv, item.name);
        if (item.check) {
            helper.setBackgroundRes(R.id.grade_tv, R.drawable.bg_red_round_solid_30);
            helper.setTextColor(R.id.grade_tv, mContext.getResources().getColor(R.color.white));
        } else {
            helper.setBackgroundRes(R.id.grade_tv, R.drawable.bg_ripple_round_stroke_30);
            helper.setTextColor(R.id.grade_tv, mContext.getResources().getColor(R.color.color666));
        }
    }
}
