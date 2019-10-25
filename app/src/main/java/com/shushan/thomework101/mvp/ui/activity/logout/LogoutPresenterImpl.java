package com.shushan.thomework101.mvp.ui.activity.logout;

import android.content.Context;

import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.LogoutRequest;
import com.shushan.thomework101.entity.request.VerifyCodeRequest;
import com.shushan.thomework101.entity.response.VerifyCodeResponse;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.GuideModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2019/09/17.
 */

public class LogoutPresenterImpl implements LogoutControl.PresenterLogout {

    private LogoutControl.LogoutView mLogoutView;
    private final GuideModel mGuideModel;
    private final Context mContext;

    @Inject
    public LogoutPresenterImpl(Context context, GuideModel model, LogoutControl.LogoutView logoutView) {
        mContext = context;
        mGuideModel = model;
        mLogoutView = logoutView;
    }


    /**
     * 获取验证码
     */
    @Override
    public void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest) {
        mLogoutView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mGuideModel.onRequestVerifyCode(verifyCodeRequest).compose(mLogoutView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestVerifyCodeSuccess, throwable -> mLogoutView.showErrMessage(throwable),
                        () -> mLogoutView.dismissLoading());
        mLogoutView.addSubscription(disposable);
    }

    /**
     * 获取验证码成功
     */
    private void requestVerifyCodeSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            responseData.parseData(VerifyCodeResponse.class);
            if (responseData.parsedData != null) {
                VerifyCodeResponse response = (VerifyCodeResponse) responseData.parsedData;
                mLogoutView.getVerifyCodeSuccess(response);
            }
        } else {
            mLogoutView.showToast(responseData.errorMsg);
        }
    }


    /**
     * 注销账号
     */
    @Override
    public void onRequestLogout(LogoutRequest logoutRequest) {
        mLogoutView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mGuideModel.onRequestLogout(logoutRequest).compose(mLogoutView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(responseData -> requestLogoutSuccess(responseData), throwable -> mLogoutView.showErrMessage(throwable),
                        () -> mLogoutView.dismissLoading());
        mLogoutView.addSubscription(disposable);
    }

    /**
     * 注销账号成功
     */
    private void requestLogoutSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            mLogoutView.getLogoutSuccess();
//            responseData.parseData(LoginResponse.class);
//            if (responseData.parsedData != null) {
//                LoginResponse response = (LoginResponse) responseData.parsedData;
//                mLogoutView.getLoginSuccess(response);
//            }
        } else {
            mLogoutView.showToast(responseData.errorMsg);
        }
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
