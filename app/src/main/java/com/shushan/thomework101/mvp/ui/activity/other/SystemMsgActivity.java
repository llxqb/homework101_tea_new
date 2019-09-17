package com.shushan.thomework101.mvp.ui.activity.other;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerSystemMsgComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.SystemMsgModule;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

/**
 * 系统消息
 */
public class SystemMsgActivity extends BaseActivity implements SystemMsgControl.SystemMsgView {

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_system_msg);
        initInjectData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    private void initInjectData() {
        DaggerSystemMsgComponent.builder().appComponent(getAppComponent())
                .systemMsgModule(new SystemMsgModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
