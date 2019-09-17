package com.shushan.thomework101.mvp.ui.activity.mine;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerExpectedIncomeComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.ExpectedIncomeModule;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

/**
 * 预计收益
 */
public class ExpectedIncomeActivity extends BaseActivity implements ExpectedIncomeControl.ExpectedIncomeView {

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_expected_income);
        initInjectData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    private void initInjectData() {
        DaggerExpectedIncomeComponent.builder().appComponent(getAppComponent())
                .expectedIncomeModule(new ExpectedIncomeModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }

}
