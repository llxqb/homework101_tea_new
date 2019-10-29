package com.shushan.thomework101.mvp.ui.activity.rongCloud;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shushan.thomework101.mvp.utils.ToastUtils;

import io.rong.imkit.fragment.ConversationFragment;

/**
 *
 */
public class ConversationSaFragment extends ConversationFragment {
    private boolean mClickState;
    private Context mContext;

    public void setState(boolean clickState, Context context) {
        mClickState = clickState;
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override

    public void onPluginToggleClick(View v, ViewGroup extensionBoard) {
        if (mClickState) {
            super.onPluginToggleClick(v, extensionBoard);
        } else {
            ToastUtils.showShort(mContext, "请开始辅导");
        }
    }
}

