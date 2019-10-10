package com.shushan.thomework101.mvp.ui.activity.personalInfo;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerPersonalInfoComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.PersonalInfoModule;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

/**
 * 设置辅导时间
 */
public class CoachingTimeActivity extends BaseActivity implements PersonalInfoControl.PersonalInfoView {

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_coaching_time);
        initInjectData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void getUploadPersonalInfoSuccess() {

    }

    private void initInjectData() {
        DaggerPersonalInfoComponent.builder().appComponent(getAppComponent())
                .personalInfoModule(new PersonalInfoModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
