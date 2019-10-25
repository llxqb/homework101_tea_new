package com.shushan.thomework101.mvp.ui.activity.logout;

import android.content.Intent;

import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.mvp.ui.activity.main.MainActivity;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import butterknife.OnClick;
import io.rong.imkit.RongIM;

/**
 * 注销成功页面
 */
public class LogoutSuccessActivity extends BaseActivity {

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_logout_success);
        ((HomeworkApplication) getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.sure_tv)
    public void onViewClicked() {
        exitLogin();
    }

    /**
     * 退出登录
     */
    public void exitLogin() {
        mSharePreferenceUtil.clearData();
        RongIM.getInstance().logout();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//表示 不创建新的实例activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//表示 移除该activity上面的activity
        intent.putExtra("exitLogin", true);
        startActivity(intent);
        finish();
    }

}
