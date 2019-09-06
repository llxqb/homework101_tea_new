package com.shushan.thomework101.mvp.ui.activity.main;


import com.shushan.thomework101.R;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        showLoading();
    }

    @Override
    public void initData() {

    }
}
