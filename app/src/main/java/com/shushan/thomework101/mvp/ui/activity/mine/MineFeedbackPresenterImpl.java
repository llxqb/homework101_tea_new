package com.shushan.thomework101.mvp.ui.activity.mine;

import android.content.Context;

import com.shushan.thomework101.mvp.model.MineModel;

import javax.inject.Inject;


/**
 * Created by li.liu on 2019/09/17.
 */

public class MineFeedbackPresenterImpl implements MineFeedbackControl.PresenterMineFeedback {

    private MineFeedbackControl.MineFeedbackView mMineFeedbackView;
    private final MineModel mMineModel;
    private final Context mContext;

    @Inject
    public MineFeedbackPresenterImpl(Context context, MineModel model, MineFeedbackControl.MineFeedbackView MineFeedbackView) {
        mContext = context;
        mMineModel = model;
        mMineFeedbackView = MineFeedbackView;
    }


//    /**
//     * 获取验证码
//     */
//    @Override
//    public void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest) {
//        mMineFeedbackView.showLoading(mContext.getResources().getString(R.string.loading));
//        Disposable disposable = mMineFeedbackModel.onRequestVerifyCode(verifyCodeRequest).compose(mMineFeedbackView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
//                .subscribe(this::requestVerifyCodeSuccess, throwable -> mMineFeedbackView.showErrMessage(throwable),
//                        () -> mMineFeedbackView.dismissLoading());
//        mMineFeedbackView.addSubscription(disposable);
//    }
//
//
//    private void requestVerifyCodeSuccess(ResponseData responseData) {
//        if (responseData.resultCode == 0) {
//            mMineFeedbackView.getVerifyCodeSuccess(responseData.verifyCode);
////            responseData.parseData(ForgetPwdResponse.class);
////            if (responseData.parsedData != null) {
////                ForgetPwdResponse response = (ForgetPwdResponse) responseData.parsedData;
////                mMineFeedbackView.getForgetPwdSuccess(response);
////            }
//        } else {
//            mMineFeedbackView.showToast(responseData.errorMsg);
//        }
//    }
    
    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
