package com.shushan.thomework101.mvp.ui.activity.rongCloud;

import android.content.Context;

import com.shushan.thomework101.mvp.model.StudentModel;

import javax.inject.Inject;


/**
 * Created by li.liu on 2017/4/27.
 * PresenterConversationImpl
 */

public class ConversationPresenterImpl implements ConversationControl.PresenterConversation {
    private ConversationControl.ConversationView mConversationView;
    private final StudentModel mStudentModel;
    private final Context mContext;

    @Inject
    public ConversationPresenterImpl(Context context, StudentModel model, ConversationControl.ConversationView ConversationView) {
        mContext = context;
        mStudentModel = model;
        mConversationView = ConversationView;
    }


//    /**
//     * 获取验证码
//     */
//    @Override
//    public void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest) {
//        mConversationView.showLoading(mContext.getResources().getString(R.string.loading));
//        Disposable disposable = mStudentModel.onRequestVerifyCode(verifyCodeRequest).compose(mConversationView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
//                .subscribe(this::requestVerifyCodeSuccess, throwable -> mConversationView.showErrMessage(throwable),
//                        () -> mConversationView.dismissLoading());
//        mConversationView.addSubscription(disposable);
//    }
//
//
//    private void requestVerifyCodeSuccess(ResponseData responseData) {
//        if (responseData.resultCode == 0) {
//            mConversationView.getVerifyCodeSuccess(responseData.verifyCode);
////            responseData.parseData(ForgetPwdResponse.class);
////            if (responseData.parsedData != null) {
////                ForgetPwdResponse response = (ForgetPwdResponse) responseData.parsedData;
////                mConversationView.getForgetPwdSuccess(response);
////            }
//        } else {
//            mConversationView.showToast(responseData.errorMsg);
//        }
//    }
    
    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
