package com.shushan.thomework101.mvp.ui.activity.mine;

import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

/**
 * 我的钱包
 */
public class WalletActivity extends BaseActivity {

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_wallet);
        ((HomeworkApplication) getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
