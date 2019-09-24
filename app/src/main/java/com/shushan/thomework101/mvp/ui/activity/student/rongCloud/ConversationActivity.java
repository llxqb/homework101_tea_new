package com.shushan.thomework101.mvp.ui.activity.student.rongCloud;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerConversationComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.ConversationModule;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

/**
 * 融云聊天activity
 */
public class ConversationActivity extends BaseActivity implements ConversationControl.ConversationView {

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_conversation);
        initInjectData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    private void initInjectData() {
        DaggerConversationComponent.builder().appComponent(getAppComponent())
                .conversationModule(new ConversationModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }

}
