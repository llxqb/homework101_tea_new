package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;

import java.util.List;

/**
 * 选择学生类型 adapter
 */
public class PopupWindowText2Adapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PopupWindowText2Adapter(@Nullable List<String> data) {
        super(R.layout.item_popup_window_text2, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.popup_window_text_layout);
        helper.setText(R.id.text,item);
//        helper.addOnClickListener(R.id.item_grade_layout);
//        helper.setText(R.id.grade_tv, item.name);
//        if (item.check) {
//            helper.setBackgroundRes(R.id.grade_tv, R.drawable.gradient_red_bg_30);
//            helper.setTextColor(R.id.grade_tv, mContext.getResources().getColor(R.color.white));
//        } else {
//            helper.setBackgroundRes(R.id.grade_tv, R.drawable.bg_ripple_round_solid_30);
//            helper.setTextColor(R.id.grade_tv, Color.parseColor("#9C9DA8"));
//        }
    }
}
