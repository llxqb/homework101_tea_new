package com.shushan.thomework101.mvp.ui.activity.main;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerMainComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.MainModule;
import com.shushan.thomework101.entity.request.DeviceInfoRequest;
import com.shushan.thomework101.entity.request.VersionUpdateRequest;
import com.shushan.thomework101.entity.response.VersionUpdateResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.activity.guide.SubjectSelectActivity;
import com.shushan.thomework101.mvp.ui.activity.guide.login.LoginActivity;
import com.shushan.thomework101.mvp.ui.adapter.MyFragmentAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.ui.fragment.home.HomeFragment;
import com.shushan.thomework101.mvp.ui.fragment.mine.MineFragment;
import com.shushan.thomework101.mvp.ui.fragment.student.StudentFragment;
import com.shushan.thomework101.mvp.utils.LogUtils;
import com.shushan.thomework101.mvp.utils.SystemUtils;
import com.shushan.thomework101.mvp.utils.UpdateManager;
import com.shushan.thomework101.mvp.views.MyNoScrollViewPager;
import com.umeng.analytics.AnalyticsConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, MainControl.MainView {

    @BindView(R.id.main_bottom_navigation)
    BottomNavigationView mMainBottomNavigation;
    @BindView(R.id.main_viewpager)
    MyNoScrollViewPager mMainViewpager;
    public static final int SWITCH_HOME_PAGE = 0;
    public static final int SWITCH_MESSAGE_PAGE = 1;
    public static final int SWITCH_MINE_PAGE = 2;
    private User mUser;
    @Inject
    MainControl.PresenterMain mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_main);
        initInjectData();
        mUser = mBuProcessor.getUser();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mMainBottomNavigation.setItemIconTintList(null);//Menu时不显示彩色图标的问题
        LogUtils.e("user:" + new Gson().toJson(mBuProcessor.getUser()));
        if (!mBuProcessor.isValidLogin()) {
            startActivitys(LoginActivity.class);
            finish();
        } else if (!mBuProcessor.isFinishFirstWrite()) {
            startActivitys(SubjectSelectActivity.class);
        }
        connectRongCloud();
        List<Fragment> fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        StudentFragment studentFragment = new StudentFragment();
        MineFragment mineFragment = new MineFragment();
        fragments.add(homeFragment);
        fragments.add(studentFragment);
        fragments.add(mineFragment);
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), fragments);
        mMainViewpager.setOffscreenPageLimit(fragments.size());
        mMainViewpager.setAdapter(adapter);
        mMainBottomNavigation.setOnNavigationItemSelectedListener(this);
        onRequestVersionUpdate();
        uploadDeviceInfo();
    }

    /**
     * 上传设备信息
     */
    private void uploadDeviceInfo() {
        DeviceInfoRequest deviceInfoRequest = new DeviceInfoRequest();
        deviceInfoRequest.version = SystemUtils.getVersionName(this);
        deviceInfoRequest.channel = AnalyticsConfig.getChannel(this);
        deviceInfoRequest.deviceType = SystemUtils.getSystemModel();
        deviceInfoRequest.deviceId = SystemUtils.getUUID(this, mSharePreferenceUtil);
        deviceInfoRequest.sysVer = SystemUtils.getSystemVersion();
        mPresenter.uploadDeviceInfo(deviceInfoRequest);
    }

    /**
     * 版本更新
     */
    private void onRequestVersionUpdate() {
        VersionUpdateRequest versionUpdateRequest = new VersionUpdateRequest();
        versionUpdateRequest.version = SystemUtils.getVersionName(this);
        mPresenter.onRequestVersionUpdate(versionUpdateRequest);
    }

    @Override
    public void getVersionUpdateSuccess(VersionUpdateResponse versionUpdateResponse) {
        if (versionUpdateResponse != null) {
            UpdateManager mUpdateManager = new UpdateManager(this, versionUpdateResponse);
            mUpdateManager.checkUpdateInfo();
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getBooleanExtra("exitLogin", false)) {
            //退出登录 558388
            User user = new User();
            user.isReadProtocol = true;
            mBuProcessor.setLoginUser(user);
            startActivitys(LoginActivity.class);
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        resetToDefaultIcon();//重置到默认不选中图片
        switch (menuItem.getItemId()) {
            case R.id.action_home:
                //在这里替换图标
                menuItem.setIcon(R.mipmap.home);
                mMainViewpager.setCurrentItem(SWITCH_HOME_PAGE, false);
                break;
            case R.id.action_student:
                menuItem.setIcon(R.mipmap.student);
                mMainViewpager.setCurrentItem(SWITCH_MESSAGE_PAGE, false);
                break;
            case R.id.action_mine:
                menuItem.setIcon(R.mipmap.my);
                mMainViewpager.setCurrentItem(SWITCH_MINE_PAGE, false);
                break;
        }
        return true;
    }

    private void resetToDefaultIcon() {
        MenuItem home = mMainBottomNavigation.getMenu().findItem(R.id.action_home);
        MenuItem mine = mMainBottomNavigation.getMenu().findItem(R.id.action_student);
        MenuItem more = mMainBottomNavigation.getMenu().findItem(R.id.action_mine);
        home.setIcon(R.mipmap.home_gray);
        mine.setIcon(R.mipmap.student_gray);
        more.setIcon(R.mipmap.my_gray);
    }


    /**
     * 捕捉返回事件按钮
     * <p>
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                this.exitApp();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    private long exitTime = 0;

    /**
     * 退出程序
     */
    private void exitApp() {
        // 判断2次点击事件时间
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            showToast(getResources().getString(R.string.main_exit_app));
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }


    /**
     * 连接融云
     * 舍弃 RongCloudHelper类
     */
    private void connectRongCloud() {
        String rToken = mUser.rToken;
        //连接融云
        if (!TextUtils.isEmpty(rToken)) {
            RongIM.connect(rToken, new RongIMClient.ConnectCallback() {
                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.e("ddd", "--onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.e("ddd", "--onSuccess" + userid);
//                    //保存融云userid
//                    mSharePreferenceUtil.setData("rUserId", userid);
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.e("ddd", "--onError" + errorCode);
                }
            });
        }
        //同步与服务器信息 new CustomerUserInfoProvider(rongId, mBuProcessor.getLoginUser())
//        RongIM.setUserInfoProvider(this, true);

    }

    private void initInjectData() {
        DaggerMainComponent.builder().appComponent(getAppComponent())
                .mainModule(new MainModule(MainActivity.this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
