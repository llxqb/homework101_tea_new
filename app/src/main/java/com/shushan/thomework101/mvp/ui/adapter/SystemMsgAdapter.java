package com.shushan.thomework101.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.SystemMsgResponse;
import com.shushan.thomework101.mvp.utils.DateUtil;

import java.util.List;

/**
 * 系统消息adapter
 */
public class SystemMsgAdapter extends BaseQuickAdapter<SystemMsgResponse.DataBean, BaseViewHolder> {

    public SystemMsgAdapter(@Nullable List<SystemMsgResponse.DataBean> data) {
        super(R.layout.item_system_msg, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemMsgResponse.DataBean item) {
        helper.setText(R.id.system_msg_time_tv, DateUtil.getStrTime(item.getSend_time(),DateUtil.TIME_YYMMDD));
        helper.setText(R.id.msg_content_tv,item.getContent());
    }
}
