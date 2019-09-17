package com.shushan.thomework101.mvp.ui.activity.mine;

import android.content.Context;

import com.shushan.thomework101.mvp.model.UserModel;

import javax.inject.Inject;


/**
 * Created by li.liu on 2019/09/17.
 */

public class WithdrawPresenterImpl implements WithdrawControl.PresenterWithdraw {

    private WithdrawControl.WithdrawView mWithdrawView;
    private final UserModel mUserModel;
    private final Context mContext;

    @Inject
    public WithdrawPresenterImpl(Context context, UserModel model, WithdrawControl.WithdrawView WithdrawView) {
        mContext = context;
        mUserModel = model;
        mWithdrawView = WithdrawView;
    }


//    /**
//     * 获取验证码
//     */
//    @Override
//    public void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest) {
//        mWithdrawView.showLoading(mContext.getResources().getString(R.string.loading));
//        Disposable disposable = mWithdrawModel.onRequestVerifyCode(verifyCodeRequest).compose(mWithdrawView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
//                .subscribe(this::requestVerifyCodeSuccess, throwable -> mWithdrawView.showErrMessage(throwable),
//                        () -> mWithdrawView.dismissLoading());
//        mWithdrawView.addSubscription(disposable);
//    }
//
//
//    private void requestVerifyCodeSuccess(ResponseData responseData) {
//        if (responseData.resultCode == 0) {
//            mWithdrawView.getVerifyCodeSuccess(responseData.verifyCode);
////            responseData.parseData(ForgetPwdResponse.class);
////            if (responseData.parsedData != null) {
////                ForgetPwdResponse response = (ForgetPwdResponse) responseData.parsedData;
////                mWithdrawView.getForgetPwdSuccess(response);
////            }
//        } else {
//            mWithdrawView.showToast(responseData.errorMsg);
//        }
//    }
    
    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
