package com.shushan.thomework101.mvp.ui.activity.mine;

import android.content.Context;

import com.shushan.thomework101.mvp.model.MineModel;

import javax.inject.Inject;


/**
 * Created by li.liu on 2019/09/17.
 */

public class LeavePresenterImpl implements LeaveControl.PresenterLeave {

    private LeaveControl.LeaveView mLeaveView;
    private final MineModel mMineModel;
    private final Context mContext;

    @Inject
    public LeavePresenterImpl(Context context, MineModel model, LeaveControl.LeaveView LeaveView) {
        mContext = context;
        mMineModel = model;
        mLeaveView = LeaveView;
    }


//    /**
//     * 获取验证码
//     */
//    @Override
//    public void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest) {
//        mLeaveView.showLoading(mContext.getResources().getString(R.string.loading));
//        Disposable disposable = mLeaveModel.onRequestVerifyCode(verifyCodeRequest).compose(mLeaveView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
//                .subscribe(this::requestVerifyCodeSuccess, throwable -> mLeaveView.showErrMessage(throwable),
//                        () -> mLeaveView.dismissLoading());
//        mLeaveView.addSubscription(disposable);
//    }
//
//
//    private void requestVerifyCodeSuccess(ResponseData responseData) {
//        if (responseData.resultCode == 0) {
//            mLeaveView.getVerifyCodeSuccess(responseData.verifyCode);
////            responseData.parseData(ForgetPwdResponse.class);
////            if (responseData.parsedData != null) {
////                ForgetPwdResponse response = (ForgetPwdResponse) responseData.parsedData;
////                mLeaveView.getForgetPwdSuccess(response);
////            }
//        } else {
//            mLeaveView.showToast(responseData.errorMsg);
//        }
//    }
    
    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
