package com.shushan.thomework101.mvp.ui.activity.student;

import android.content.Context;

import com.shushan.thomework101.mvp.model.StudentModel;

import javax.inject.Inject;


/**
 * Created by li.liu on 2019/09/17.
 */

public class StudentDetailPresenterImpl implements StudentDetailControl.PresenterStudentDetail {

    private StudentDetailControl.StudentDetailView mStudentDetailView;
    private final StudentModel mStudentModel;
    private final Context mContext;

    @Inject
    public StudentDetailPresenterImpl(Context context, StudentModel model, StudentDetailControl.StudentDetailView studentDetailView) {
        mContext = context;
        mStudentModel = model;
        mStudentDetailView = studentDetailView;
    }


//    /**
//     * 获取验证码
//     */
//    @Override
//    public void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest) {
//        mStudentDetailView.showLoading(mContext.getResources().getString(R.string.loading));
//        Disposable disposable = mStudentModel.onRequestVerifyCode(verifyCodeRequest).compose(mStudentDetailView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
//                .subscribe(this::requestVerifyCodeSuccess, throwable -> mStudentDetailView.showErrMessage(throwable),
//                        () -> mStudentDetailView.dismissLoading());
//        mStudentDetailView.addSubscription(disposable);
//    }
//
//
//    private void requestVerifyCodeSuccess(ResponseData responseData) {
//        if (responseData.resultCode == 0) {
//            mStudentDetailView.getVerifyCodeSuccess(responseData.verifyCode);
////            responseData.parseData(ForgetPwdResponse.class);
////            if (responseData.parsedData != null) {
////                ForgetPwdResponse response = (ForgetPwdResponse) responseData.parsedData;
////                mStudentDetailView.getForgetPwdSuccess(response);
////            }
//        } else {
//            mStudentDetailView.showToast(responseData.errorMsg);
//        }
//    }
    
    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
