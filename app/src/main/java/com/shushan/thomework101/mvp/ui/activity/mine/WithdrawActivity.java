package com.shushan.thomework101.mvp.ui.activity.mine;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerWithdrawComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.WithdrawModule;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

/**
 * 提现
 */
public class WithdrawActivity extends BaseActivity implements WithdrawControl.WithdrawView {

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_withdraw);
        initInjectData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    private void initInjectData() {
        DaggerWithdrawComponent.builder().appComponent(getAppComponent())
                .withdrawModule(new WithdrawModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }

}
