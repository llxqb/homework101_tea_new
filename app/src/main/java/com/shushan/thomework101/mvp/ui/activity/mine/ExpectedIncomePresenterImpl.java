package com.shushan.thomework101.mvp.ui.activity.mine;

import android.content.Context;

import com.shushan.thomework101.mvp.model.MineModel;

import javax.inject.Inject;


/**
 * Created by li.liu on 2019/09/17.
 */

public class ExpectedIncomePresenterImpl implements ExpectedIncomeControl.PresenterExpectedIncome {

    private ExpectedIncomeControl.ExpectedIncomeView mExpectedIncomeView;
    private final MineModel mMineModel;
    private final Context mContext;

    @Inject
    public ExpectedIncomePresenterImpl(Context context, MineModel model, ExpectedIncomeControl.ExpectedIncomeView ExpectedIncomeView) {
        mContext = context;
        mMineModel = model;
        mExpectedIncomeView = ExpectedIncomeView;
    }


//    /**
//     * 获取验证码
//     */
//    @Override
//    public void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest) {
//        mExpectedIncomeView.showLoading(mContext.getResources().getString(R.string.loading));
//        Disposable disposable = mExpectedIncomeModel.onRequestVerifyCode(verifyCodeRequest).compose(mExpectedIncomeView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
//                .subscribe(this::requestVerifyCodeSuccess, throwable -> mExpectedIncomeView.showErrMessage(throwable),
//                        () -> mExpectedIncomeView.dismissLoading());
//        mExpectedIncomeView.addSubscription(disposable);
//    }
//
//
//    private void requestVerifyCodeSuccess(ResponseData responseData) {
//        if (responseData.resultCode == 0) {
//            mExpectedIncomeView.getVerifyCodeSuccess(responseData.verifyCode);
////            responseData.parseData(ForgetPwdResponse.class);
////            if (responseData.parsedData != null) {
////                ForgetPwdResponse response = (ForgetPwdResponse) responseData.parsedData;
////                mExpectedIncomeView.getForgetPwdSuccess(response);
////            }
//        } else {
//            mExpectedIncomeView.showToast(responseData.errorMsg);
//        }
//    }
    
    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
