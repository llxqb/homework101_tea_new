package com.shushan.thomework101.mvp.ui.activity.mine;

import android.content.Context;

import com.google.gson.Gson;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.ExpectedIncomeRequest;
import com.shushan.thomework101.entity.request.RevenueIncomeRequest;
import com.shushan.thomework101.entity.response.ExpectedIncomeResponse;
import com.shushan.thomework101.entity.response.RevenueIncomeResponse;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.MineModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


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


    /**
     * 预计收益
     */
    @Override
    public void onRequestExpectedIncome(ExpectedIncomeRequest request) {
        mExpectedIncomeView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.onRequestExpectedIncome(request).compose(mExpectedIncomeView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestExpectedIncomeSuccess, throwable -> mExpectedIncomeView.showErrMessage(throwable),
                        () -> mExpectedIncomeView.dismissLoading());
        mExpectedIncomeView.addSubscription(disposable);
    }

    private void requestExpectedIncomeSuccess(ResponseData responseData) {
        mExpectedIncomeView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            responseData.parseData(ExpectedIncomeResponse.class);
            if (responseData.parsedData != null) {
                ExpectedIncomeResponse response = (ExpectedIncomeResponse) responseData.parsedData;
                mExpectedIncomeView.getExpectedIncomeSuccess(response);
            }
        } else {
            mExpectedIncomeView.showToast(responseData.errorMsg);
        }
    }
    /**
     * 已到手金额明细
     */
    @Override
    public void onRequestRevenueIncome(RevenueIncomeRequest request) {
        mExpectedIncomeView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.onRequestRevenueIncome(request).compose(mExpectedIncomeView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestRevenueIncomeSuccess, throwable -> mExpectedIncomeView.showErrMessage(throwable),
                        () -> mExpectedIncomeView.dismissLoading());
        mExpectedIncomeView.addSubscription(disposable);
    }

    private void requestRevenueIncomeSuccess(ResponseData responseData) {
        mExpectedIncomeView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            RevenueIncomeResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), RevenueIncomeResponse.class);
            mExpectedIncomeView.getRevenueIncomeSuccess(response);
        } else {
            mExpectedIncomeView.showToast(responseData.errorMsg);
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
