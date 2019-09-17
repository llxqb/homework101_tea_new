package com.shushan.thomework101.mvp.ui.activity.mine;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerMineFeedbackComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.MineFeedbackModule;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

/**
 * 我的辅导反馈
 */
public class MineFeedbackActivity extends BaseActivity implements MineFeedbackControl.MineFeedbackView {

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_mine_feedback);
        initInjectData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    private void initInjectData() {
        DaggerMineFeedbackComponent.builder().appComponent(getAppComponent())
                .mineFeedbackModule(new MineFeedbackModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
