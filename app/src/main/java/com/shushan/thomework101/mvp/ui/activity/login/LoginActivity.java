package com.shushan.thomework101.mvp.ui.activity.login;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerLoginComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.LoginModule;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity implements LoginControl.LoginView {

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_login);
        initInjectData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    private void initInjectData() {
        DaggerLoginComponent.builder().appComponent(getAppComponent())
                .loginModule(new LoginModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
