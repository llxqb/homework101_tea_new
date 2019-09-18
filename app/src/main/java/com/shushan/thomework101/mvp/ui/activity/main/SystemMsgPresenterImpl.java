package com.shushan.thomework101.mvp.ui.activity.main;

import android.content.Context;

import com.shushan.thomework101.mvp.model.MineModel;

import javax.inject.Inject;


/**
 * Created by li.liu on 2019/09/17.
 */

public class SystemMsgPresenterImpl implements SystemMsgControl.PresenterSystemMsg {

    private SystemMsgControl.SystemMsgView mSystemMsgView;
    private final MineModel mMineModel;
    private final Context mContext;

    @Inject
    public SystemMsgPresenterImpl(Context context, MineModel model, SystemMsgControl.SystemMsgView SystemMsgView) {
        mContext = context;
        mMineModel = model;
        mSystemMsgView = SystemMsgView;
    }


//    /**
//     * 获取验证码
//     */
//    @Override
//    public void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest) {
//        mSystemMsgView.showLoading(mContext.getResources().getString(R.string.loading));
//        Disposable disposable = mMineModel.onRequestVerifyCode(verifyCodeRequest).compose(mSystemMsgView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
//                .subscribe(this::requestVerifyCodeSuccess, throwable -> mSystemMsgView.showErrMessage(throwable),
//                        () -> mSystemMsgView.dismissLoading());
//        mSystemMsgView.addSubscription(disposable);
//    }
//
//
//    private void requestVerifyCodeSuccess(ResponseData responseData) {
//        if (responseData.resultCode == 0) {
//            mSystemMsgView.getVerifyCodeSuccess(responseData.verifyCode);
////            responseData.parseData(ForgetPwdResponse.class);
////            if (responseData.parsedData != null) {
////                ForgetPwdResponse response = (ForgetPwdResponse) responseData.parsedData;
////                mSystemMsgView.getForgetPwdSuccess(response);
////            }
//        } else {
//            mSystemMsgView.showToast(responseData.errorMsg);
//        }
//    }
    
    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
