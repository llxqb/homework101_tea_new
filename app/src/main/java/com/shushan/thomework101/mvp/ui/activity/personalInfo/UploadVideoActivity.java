package com.shushan.thomework101.mvp.ui.activity.personalInfo;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerPersonalInfoComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.PersonalInfoModule;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

/**
 * 上传试讲视频
 */
public class UploadVideoActivity extends BaseActivity implements PersonalInfoControl.PersonalInfoView {


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_upload_video);
        initInjectData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    private void initInjectData() {
        DaggerPersonalInfoComponent.builder().appComponent(getAppComponent())
                .personalInfoModule(new PersonalInfoModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
