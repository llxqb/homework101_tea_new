package com.shushan.thomework101.mvp.ui.activity.student;

import android.content.Context;

import com.shushan.thomework101.mvp.model.MineModel;

import javax.inject.Inject;


/**
 * Created by li.liu on 2019/09/17.
 */

public class FeedbackPresenterImpl implements FeedbackControl.PresenterFeedback {

    private FeedbackControl.FeedbackView mFeedbackView;
    private final MineModel mMineModel;
    private final Context mContext;

    @Inject
    public FeedbackPresenterImpl(Context context, MineModel model, FeedbackControl.FeedbackView FeedbackView) {
        mContext = context;
        mMineModel = model;
        mFeedbackView = FeedbackView;
    }


//    /**
//     * 获取验证码
//     */
//    @Override
//    public void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest) {
//        mFeedbackView.showLoading(mContext.getResources().getString(R.string.loading));
//        Disposable disposable = mMineModel.onRequestVerifyCode(verifyCodeRequest).compose(mFeedbackView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
//                .subscribe(this::requestVerifyCodeSuccess, throwable -> mFeedbackView.showErrMessage(throwable),
//                        () -> mFeedbackView.dismissLoading());
//        mFeedbackView.addSubscription(disposable);
//    }
//
//
//    private void requestVerifyCodeSuccess(ResponseData responseData) {
//        if (responseData.resultCode == 0) {
//            mFeedbackView.getVerifyCodeSuccess(responseData.verifyCode);
////            responseData.parseData(ForgetPwdResponse.class);
////            if (responseData.parsedData != null) {
////                ForgetPwdResponse response = (ForgetPwdResponse) responseData.parsedData;
////                mFeedbackView.getForgetPwdSuccess(response);
////            }
//        } else {
//            mFeedbackView.showToast(responseData.errorMsg);
//        }
//    }
    
    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
