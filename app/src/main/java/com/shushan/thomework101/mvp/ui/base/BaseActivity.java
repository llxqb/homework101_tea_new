package com.shushan.thomework101.mvp.ui.base;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.HttpException;
import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.AppComponent;
import com.shushan.thomework101.entity.user.BuProcessor;
import com.shushan.thomework101.help.DialogFactory;
import com.shushan.thomework101.help.ImageLoaderHelper;
import com.shushan.thomework101.mvp.ui.activity.main.MainActivity;
import com.shushan.thomework101.mvp.utils.SharePreferenceUtil;
import com.shushan.thomework101.mvp.utils.StatusBarUtil;
import com.shushan.thomework101.mvp.utils.SystemUtils;
import com.shushan.thomework101.mvp.utils.ToastUtils;
import com.umeng.message.PushAgent;

import java.net.ConnectException;

import javax.inject.Inject;
import javax.net.ssl.SSLHandshakeException;

import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.rong.imkit.RongIM;

/**
 * Created by li.liu on 17/12/20.
 * 基类Activity
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Inject
    protected ImageLoaderHelper mImageLoaderHelper;
    @Inject
    protected SharePreferenceUtil mSharePreferenceUtil;
    @Inject
    protected BuProcessor mBuProcessor;

    private CompositeDisposable mDisposable;
    private Dialog mProgressDialog;
    protected final IntentFilter mFilter = new IntentFilter();

    public AppComponent getAppComponent() {
        return ((HomeworkApplication) getApplication()).getAppComponent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFilter();
        //Push后台进行日活统计及多维度推送的必调用方法，请务必调用！
        PushAgent.getInstance(this).onAppStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, mFilter);
        initContentView();
        ButterKnife.bind(this);
        setStatusBar();
        initView();
        initData();
    }

    /**
     * 设置白底黑字状态栏
     */
    public void setStatusBar() {
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.white));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View content = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
            if (content != null && !SystemUtils.isUseFullScreenMode(this)) {
                content.setFitsSystemWindows(true);
            }
        }
    }

    protected abstract void initContentView();

    public abstract void initView();

    public abstract void initData();


    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onReceivePro(context, intent);
        }
    };

    public void onReceivePro(Context context, Intent intent) {
    }

    public void addFilter() {
    }

    /**
     * 启动activity
     */
    public void startActivitys(Class<?> tClass) {
        Intent intent = new Intent(this, tClass);
        startActivity(intent);
    }


    public void showErrMessage(Throwable e) {
        dismissLoading();
        String mErrMessage;
        Log.d("LogInterceptor", "e:" + e.toString());
        if (e instanceof HttpException || e instanceof ConnectException || e instanceof SSLHandshakeException) {
            mErrMessage = getString(R.string.text_check_internet);
        } else {
            mErrMessage = getString(R.string.text_wait_try);
        }
        showToast(mErrMessage);
    }

    public void judgeToken(Integer code) {
        if (code == 2) {
            showToast("登入过期,请重新登入");
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

    public void showToast(String message) {
        ToastUtils.showShort(getContext(), message);
    }

    public void showLoading() {
        dismissLoading();
        mProgressDialog = DialogFactory.showLoadingDialog(this, "");
        mProgressDialog.show();
    }


    public void showLoading(String msg) {
        dismissLoading();
        mProgressDialog = DialogFactory.showLoadingDialog(this, msg);
        mProgressDialog.show();
    }

    public void dismissLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mProgressDialog = null;
    }

    public Context getContext() {
        return this;
    }

    /**
     * @param subscription RxJava取消订阅
     */
    public void addSubscription(Disposable subscription) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(subscription);
    }

    public <T> ObservableTransformer<T, T> applySchedulers() {
        //noinspection unchecked
        return (ObservableTransformer<T, T>) schedulersTransformer;
    }

    private final ObservableTransformer schedulersTransformer = observable -> (
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()));

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.clear();
        }
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }
}
