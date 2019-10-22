package com.shushan.thomework101.mvp.ui.activity.guide.login;

import android.content.Context;

import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.LoginRequest;
import com.shushan.thomework101.entity.request.VerifyCodeRequest;
import com.shushan.thomework101.entity.response.LoginResponse;
import com.shushan.thomework101.entity.response.VerifyCodeResponse;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.GuideModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2019/09/17.
 */

public class LoginPresenterImpl implements LoginControl.PresenterLogin {

    private LoginControl.LoginView mLoginView;
    private final GuideModel mGuideModel;
    private final Context mContext;

    @Inject
    public LoginPresenterImpl(Context context, GuideModel model, LoginControl.LoginView LoginView) {
        mContext = context;
        mGuideModel = model;
        mLoginView = LoginView;
    }


    /**
     * 获取验证码
     */
    @Override
    public void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest) {
        mLoginView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mGuideModel.onRequestVerifyCode(verifyCodeRequest).compose(mLoginView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestVerifyCodeSuccess, throwable -> mLoginView.showErrMessage(throwable),
                        () -> mLoginView.dismissLoading());
        mLoginView.addSubscription(disposable);
    }

    /**
     * 获取验证码成功
     */
    private void requestVerifyCodeSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            responseData.parseData(VerifyCodeResponse.class);
            if (responseData.parsedData != null) {
                VerifyCodeResponse response = (VerifyCodeResponse) responseData.parsedData;
                mLoginView.getVerifyCodeSuccess(response);
            }
        } else {
            mLoginView.showToast(responseData.errorMsg);
        }
    }


    /**
     * 注册 和 登录 , int type
     */
    @Override
    public void onRequestLogin(LoginRequest loginRequest) {
        mLoginView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mGuideModel.onRequestLogin(loginRequest).compose(mLoginView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(responseData -> requestLoginSuccess(responseData), throwable -> mLoginView.showErrMessage(throwable),
                        () -> mLoginView.dismissLoading());
        mLoginView.addSubscription(disposable);
    }

    /**
     * 登录成功
     * type : 1 注册 2 登录
     */
    private void requestLoginSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            responseData.parseData(LoginResponse.class);
            if (responseData.parsedData != null) {
                LoginResponse response = (LoginResponse) responseData.parsedData;
                mLoginView.getLoginSuccess(response);
            }
        } else {
            mLoginView.showToast(responseData.errorMsg);
        }
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
