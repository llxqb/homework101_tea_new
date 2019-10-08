package com.shushan.thomework101.mvp.ui.activity.rongCloud;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shushan.thomework101.R;
import com.shushan.thomework101.mvp.ui.base.BaseFragment;

import java.util.Objects;

import io.rong.imlib.model.Conversation;

public class ConversationListFragment extends BaseFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.conversitionlist_fragment, container, false);
        io.rong.imkit.fragment.ConversationListFragment mConversationListFragment = (io.rong.imkit.fragment.ConversationListFragment) getChildFragmentManager().findFragmentById(R.id.conversationlist);
        Uri uri = Uri.parse("rong://" + Objects.requireNonNull(getActivity()).getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                .build();
        assert mConversationListFragment != null;
        mConversationListFragment.setUri(uri);
        mConversationListFragment.onRestoreUI();
        initView();
        initData();
        return view;
    }


    @Override
    public void initView() {
    }

    @Override
    public void initData() {
//        requestSystemMsgNew();
    }



}
