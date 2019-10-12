package com.shushan.thomework101.mvp.ui.activity.bank;

import android.content.Context;

import com.google.gson.Gson;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.response.MineBankCardResponse;
import com.shushan.thomework101.entity.response.WalletResponse;
import com.shushan.thomework101.entity.response.WithdrawResponse;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.MineModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2019/09/17.
 */

public class WithdrawPresenterImpl implements WithdrawControl.PresenterWithdraw {

    private WithdrawControl.WithdrawView mWithdrawView;
    private final MineModel mMineModel;
    private final Context mContext;

    @Inject
    public WithdrawPresenterImpl(Context context, MineModel model, WithdrawControl.WithdrawView WithdrawView) {
        mContext = context;
        mMineModel = model;
        mWithdrawView = WithdrawView;
    }


    /**
     * 我的钱包
     */
    @Override
    public void onRequestWallet(TokenRequest request) {
        mWithdrawView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.onRequestWallet(request).compose(mWithdrawView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestWalletSuccess, throwable -> mWithdrawView.showErrMessage(throwable),
                        () -> mWithdrawView.dismissLoading());
        mWithdrawView.addSubscription(disposable);
    }

    private void requestWalletSuccess(ResponseData responseData) {
        mWithdrawView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            responseData.parseData(WalletResponse.class);
            if (responseData.parsedData != null) {
                WalletResponse response = (WalletResponse) responseData.parsedData;
                mWithdrawView.getWalletSuccess(response);
            }
        } else {
            mWithdrawView.showToast(responseData.errorMsg);
        }
    }

    /**
     * 提现
     */
    @Override
    public void onRequestWithdraw(TokenRequest request) {
        mWithdrawView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.onRequestWithdraw(request).compose(mWithdrawView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestWithdrawSuccess, throwable -> mWithdrawView.showErrMessage(throwable),
                        () -> mWithdrawView.dismissLoading());
        mWithdrawView.addSubscription(disposable);
    }

    private void requestWithdrawSuccess(ResponseData responseData) {
        mWithdrawView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            responseData.parseData(WithdrawResponse.class);
            if (responseData.parsedData != null) {
                WithdrawResponse response = (WithdrawResponse) responseData.parsedData;
                mWithdrawView.getWithdrawSuccess(response);
            }
        } else {
            mWithdrawView.showToast(responseData.errorMsg);
        }
    }

    /**
     * 我的银行卡
     */
    @Override
    public void onRequestMineCardInfo(TokenRequest request) {
        mWithdrawView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.onRequestMineCardInfo(request).compose(mWithdrawView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestMineCardInfoSuccess, throwable -> mWithdrawView.showErrMessage(throwable),
                        () -> mWithdrawView.dismissLoading());
        mWithdrawView.addSubscription(disposable);
    }

    private void requestMineCardInfoSuccess(ResponseData responseData) {
        mWithdrawView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            MineBankCardResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), MineBankCardResponse.class);
            mWithdrawView.getMineBankCardSuccess(response);
        } else {
            mWithdrawView.showToast(responseData.errorMsg);
        }
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
