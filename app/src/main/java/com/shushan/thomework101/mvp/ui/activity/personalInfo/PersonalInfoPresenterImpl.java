package com.shushan.thomework101.mvp.ui.activity.personalInfo;

import android.content.Context;

import com.shushan.thomework101.mvp.model.MineModel;

import javax.inject.Inject;


/**
 * Created by li.liu on 2019/09/17.
 */

public class PersonalInfoPresenterImpl implements PersonalInfoControl.PresenterPersonalInfo {

    private PersonalInfoControl.PersonalInfoView mPersonalInfoView;
    private final MineModel mMineModel;
    private final Context mContext;

    @Inject
    public PersonalInfoPresenterImpl(Context context, MineModel model, PersonalInfoControl.PersonalInfoView PersonalInfoView) {
        mContext = context;
        mMineModel = model;
        mPersonalInfoView = PersonalInfoView;
    }


//    /**
//     * 获取验证码
//     */
//    @Override
//    public void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest) {
//        mPersonalInfoView.showLoading(mContext.getResources().getString(R.string.loading));
//        Disposable disposable = mMineModel.onRequestVerifyCode(verifyCodeRequest).compose(mPersonalInfoView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
//                .subscribe(this::requestVerifyCodeSuccess, throwable -> mPersonalInfoView.showErrMessage(throwable),
//                        () -> mPersonalInfoView.dismissLoading());
//        mPersonalInfoView.addSubscription(disposable);
//    }
//
//
//    private void requestVerifyCodeSuccess(ResponseData responseData) {
//        if (responseData.resultCode == 0) {
//            mPersonalInfoView.getVerifyCodeSuccess(responseData.verifyCode);
////            responseData.parseData(ForgetPwdResponse.class);
////            if (responseData.parsedData != null) {
////                ForgetPwdResponse response = (ForgetPwdResponse) responseData.parsedData;
////                mPersonalInfoView.getForgetPwdSuccess(response);
////            }
//        } else {
//            mPersonalInfoView.showToast(responseData.errorMsg);
//        }
//    }
    
    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}