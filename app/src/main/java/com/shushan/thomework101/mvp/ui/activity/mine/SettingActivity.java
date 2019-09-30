package com.shushan.thomework101.mvp.ui.activity.mine;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.help.DialogFactory;
import com.shushan.thomework101.mvp.ui.activity.main.MainActivity;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.ui.dialog.CommonDialog;
import com.shushan.thomework101.mvp.utils.DataCleanManager;
import com.shushan.thomework101.mvp.utils.SystemUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

/**
 * 设置
 */
public class SettingActivity extends BaseActivity implements CommonDialog.CommonDialogListener {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.clear_cache_tv)
    TextView mClearCacheTv;
    @BindView(R.id.cache_tv)
    TextView mCacheTv;
    @BindView(R.id.about_us_tv)
    TextView mAboutUsTv;
    @BindView(R.id.exit_tv)
    TextView mExitTv;
    @BindView(R.id.message_notification_iv)
    ImageView mMessageNotificationIv;
    @BindView(R.id.update_tv)
    TextView mUpdateTv;
    @BindView(R.id.version_name_tv)
    TextView mVersionNameTv;
    private User mUser;
    private boolean mMessageNotification;
    /**
     * 清理缓存 1
     * 退出登录 2
     */
    private int commonDialogClickType;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_setting);
        ((HomeworkApplication) getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void initView() {
        mCommonTitleTv.setText("设置");
        mUser = mBuProcessor.getUser();
        mMessageNotification = mSharePreferenceUtil.getBooleanData("message_notification", true);
        if (mMessageNotification) {
            mMessageNotificationIv.setImageResource(R.mipmap.install_switch_red);
        } else {
            mMessageNotificationIv.setImageResource(R.mipmap.install_switch_graay);
        }
    }

    @Override
    public void initData() {
        mVersionNameTv.setText(SystemUtils.getVersionName(this));
        try {
            mCacheTv.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.common_left_iv, R.id.message_notification_iv, R.id.version_name_tv, R.id.clear_cache_tv, R.id.about_us_tv, R.id.exit_tv})
    public void onViewClicked(View view) {
        mMessageNotification = mSharePreferenceUtil.getBooleanData("message_notification", true);
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.message_notification_iv:
                if (mMessageNotification) {
                    //关闭
                    mSharePreferenceUtil.setData("message_notification", false);
                    mMessageNotificationIv.setImageResource(R.mipmap.install_switch_graay);
                    //设置会话通知免打扰时间。
                    RongIM.getInstance().setNotificationQuietHours("00:00:00", 1439, null);
                } else {
                    //打开
                    mSharePreferenceUtil.setData("message_notification", true);
                    mMessageNotificationIv.setImageResource(R.mipmap.install_switch_red);
                    //移除会话通知免打扰时间。
                    RongIM.getInstance().removeNotificationQuietHours(null);
                }
                break;
            case R.id.version_name_tv:
                break;
            case R.id.clear_cache_tv:
                commonDialogClickType = 1;
                showClearCacheDialog();
                break;
            case R.id.about_us_tv:
                break;
            case R.id.exit_tv:
                commonDialogClickType = 2;
                showExitLoginDialog();
                break;
        }
    }


    private void showClearCacheDialog() {
        String cacheValue = mCacheTv.getText().toString();
        DialogFactory.showCommonDialog(this, "你确定要清理缓存?", cacheValue, "取消", "确定");
    }

    private void showExitLoginDialog() {
        DialogFactory.showCommonDialog(this, "你确定要退出吗？", "", "取消", "确定");
    }

    @Override
    public void commonDialogBtnOkListener() {
        if (commonDialogClickType == 1) {
            showToast("清理成功");
            DataCleanManager.clearAllCache(this);
            try {
                mCacheTv.setText(DataCleanManager.getTotalCacheSize(this));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            showToast("退出成功");
            exitLogin();
        }
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
