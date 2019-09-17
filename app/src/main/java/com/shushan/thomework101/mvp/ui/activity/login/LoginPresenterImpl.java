package com.shushan.thomework101.mvp.ui.activity.login;

import android.content.Context;


import com.shushan.thomework101.mvp.model.LoginModel;

import javax.inject.Inject;


/**
 * Created by li.liu on 2019/09/17.
 */

public class LoginPresenterImpl implements LoginControl.PresenterLogin {

    private LoginControl.LoginView mLoginView;
    private final LoginModel mLoginModel;
    private final Context mContext;

    @Inject
    public LoginPresenterImpl(Context context, LoginModel model, LoginControl.LoginView LoginView) {
        mContext = context;
        mLoginModel = model;
        mLoginView = LoginView;
    }


//    /**
//     * 获取验证码
//     */
//    @Override
//    public void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest) {
//        mLoginView.showLoading(mContext.getResources().getString(R.string.loading));
//        Disposable disposable = mLoginModel.onRequestVerifyCode(verifyCodeRequest).compose(mLoginView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
//                .subscribe(this::requestVerifyCodeSuccess, throwable -> mLoginView.showErrMessage(throwable),
//                        () -> mLoginView.dismissLoading());
//        mLoginView.addSubscription(disposable);
//    }
//
//
//    private void requestVerifyCodeSuccess(ResponseData responseData) {
//        if (responseData.resultCode == 0) {
//            mLoginView.getVerifyCodeSuccess(responseData.verifyCode);
////            responseData.parseData(ForgetPwdResponse.class);
////            if (responseData.parsedData != null) {
////                ForgetPwdResponse response = (ForgetPwdResponse) responseData.parsedData;
////                mLoginView.getForgetPwdSuccess(response);
////            }
//        } else {
//            mLoginView.showToast(responseData.errorMsg);
//        }
//    }
    
    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
