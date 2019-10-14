package com.shushan.thomework101.mvp.ui.activity.bank;

import android.content.Context;

import com.google.gson.Gson;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.request.WithDrawRequest;
import com.shushan.thomework101.entity.response.MineBankCardResponse;
import com.shushan.thomework101.entity.response.WalletResponse;
import com.shushan.thomework101.entity.response.WithdrawResponse;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.BankModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2019/09/17.
 */

public class WithdrawPresenterImpl implements WithdrawControl.PresenterWithdraw {

    private WithdrawControl.WithdrawView mWithdrawView;
    private final BankModel mBankModel;
    private final Context mContext;

    @Inject
    public WithdrawPresenterImpl(Context context, BankModel model, WithdrawControl.WithdrawView WithdrawView) {
        mContext = context;
        mBankModel = model;
        mWithdrawView = WithdrawView;
    }


    /**
     * 我的钱包
     */
    @Override
    public void onRequestWallet(TokenRequest request) {
        mWithdrawView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mBankModel.onRequestWallet(request).compose(mWithdrawView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
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
     * 默认提现卡号
     */
    @Override
    public void onRequestDefaultCard(TokenRequest request) {
        mWithdrawView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mBankModel.onRequestDefaultCard(request).compose(mWithdrawView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestDefaultCardSuccess, throwable -> mWithdrawView.showErrMessage(throwable),
                        () -> mWithdrawView.dismissLoading());
        mWithdrawView.addSubscription(disposable);
    }

    private void requestDefaultCardSuccess(ResponseData responseData) {
        mWithdrawView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            responseData.parseData(WithdrawResponse.class);
            if (responseData.parsedData != null) {
                WithdrawResponse response = (WithdrawResponse) responseData.parsedData;
                mWithdrawView.getDefaultCardSuccess(response);
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
        Disposable disposable = mBankModel.onRequestMineCardInfo(request).compose(mWithdrawView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
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

    /**
     * 去提现
     */
    @Override
    public void onRequestWithdraw(WithDrawRequest request) {
        mWithdrawView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mBankModel.onRequestWithdraw(request).compose(mWithdrawView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestWithdrawSuccess, throwable -> mWithdrawView.showErrMessage(throwable),
                        () -> mWithdrawView.dismissLoading());
        mWithdrawView.addSubscription(disposable);
    }

    private void requestWithdrawSuccess(ResponseData responseData) {
        mWithdrawView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            mWithdrawView.getWithDrawSuccess();
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
