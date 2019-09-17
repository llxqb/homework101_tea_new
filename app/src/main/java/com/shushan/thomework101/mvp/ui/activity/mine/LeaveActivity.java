package com.shushan.thomework101.mvp.ui.activity.mine;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerLeaveComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.LeaveModule;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

/**
 * 请假
 */
public class LeaveActivity extends BaseActivity implements LeaveControl.LeaveView{

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_leave);
        initInjectData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    private void initInjectData() {
        DaggerLeaveComponent.builder().appComponent(getAppComponent())
                .leaveModule(new LeaveModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
