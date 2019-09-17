package com.shushan.thomework101.mvp.ui.activity.mine;

import android.content.Context;

import com.shushan.thomework101.mvp.model.MineModel;

import javax.inject.Inject;


/**
 * Created by li.liu on 2019/09/17.
 */

public class StudentReplaceDetailPresenterImpl implements StudentReplaceDetailControl.PresenterStudentReplaceDetail {

    private StudentReplaceDetailControl.StudentReplaceDetailView mStudentReplaceDetailView;
    private final MineModel mMineModel;
    private final Context mContext;

    @Inject
    public StudentReplaceDetailPresenterImpl(Context context, MineModel model, StudentReplaceDetailControl.StudentReplaceDetailView StudentReplaceDetailView) {
        mContext = context;
        mMineModel = model;
        mStudentReplaceDetailView = StudentReplaceDetailView;
    }


//    /**
//     * 获取验证码
//     */
//    @Override
//    public void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest) {
//        mStudentReplaceDetailView.showLoading(mContext.getResources().getString(R.string.loading));
//        Disposable disposable = mStudentReplaceDetailModel.onRequestVerifyCode(verifyCodeRequest).compose(mStudentReplaceDetailView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
//                .subscribe(this::requestVerifyCodeSuccess, throwable -> mStudentReplaceDetailView.showErrMessage(throwable),
//                        () -> mStudentReplaceDetailView.dismissLoading());
//        mStudentReplaceDetailView.addSubscription(disposable);
//    }
//
//
//    private void requestVerifyCodeSuccess(ResponseData responseData) {
//        if (responseData.resultCode == 0) {
//            mStudentReplaceDetailView.getVerifyCodeSuccess(responseData.verifyCode);
////            responseData.parseData(ForgetPwdResponse.class);
////            if (responseData.parsedData != null) {
////                ForgetPwdResponse response = (ForgetPwdResponse) responseData.parsedData;
////                mStudentReplaceDetailView.getForgetPwdSuccess(response);
////            }
//        } else {
//            mStudentReplaceDetailView.showToast(responseData.errorMsg);
//        }
//    }
    
    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
