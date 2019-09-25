package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.SystemMsgResponse;

import java.util.List;

/**
 * 系统消息adapter
 */
public class SystemMsgAdapter extends BaseQuickAdapter<SystemMsgResponse, BaseViewHolder> {

    public SystemMsgAdapter(@Nullable List<SystemMsgResponse> data) {
        super(R.layout.item_system_msg, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemMsgResponse item) {
    }
}
