package com.shushan.thomework101.mvp.ui.activity.bank;

import android.content.Context;

import com.google.gson.Gson;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.BindCardRequest;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.response.BankInfoResponse;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.BankModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2019/09/17.
 */

public class AddBankPresenterImpl implements AddBankCardControl.PresenterAddBankCard {

    private AddBankCardControl.AddBankCardView mAddBankCardView;
    private final BankModel mBankModel;
    private final Context mContext;

    @Inject
    public AddBankPresenterImpl(Context context, BankModel model, AddBankCardControl.AddBankCardView AddBankCardView) {
        mContext = context;
        mBankModel = model;
        mAddBankCardView = AddBankCardView;
    }


    /**
     * 根据卡查找银行
     */
    @Override
    public void searchBankByCard(String cardNo) {
        mAddBankCardView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mBankModel.searchBankByCard(cardNo).compose(mAddBankCardView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::searchBankByCardSuccess, throwable -> mAddBankCardView.showErrMessage(throwable),
                        () -> mAddBankCardView.dismissLoading());
        mAddBankCardView.addSubscription(disposable);
    }

    private void searchBankByCardSuccess(ResponseData responseData) {
        mAddBankCardView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            mAddBankCardView.getBankByCardSuccess(responseData.result);
//            responseData.parseData(WalletResponse.class);
//            if (responseData.parsedData != null) {
//                WalletResponse response = (WalletResponse) responseData.parsedData;
//
//            }
        } else {
            mAddBankCardView.showToast(responseData.errorMsg);
        }
    }
    /**
     * 银行信息
     */
    @Override
    public void onRequestBankInfo(TokenRequest tokenRequest) {
        mAddBankCardView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mBankModel.onRequestBankInfo(tokenRequest).compose(mAddBankCardView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestBankInfoSuccess, throwable -> mAddBankCardView.showErrMessage(throwable),
                        () -> mAddBankCardView.dismissLoading());
        mAddBankCardView.addSubscription(disposable);
    }

    private void requestBankInfoSuccess(ResponseData responseData) {
        mAddBankCardView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            BankInfoResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), BankInfoResponse.class);
            mAddBankCardView.getBankInfoSuccess(response);
        } else {
            mAddBankCardView.showToast(responseData.errorMsg);
        }
    }

    /**
     * 绑定银行卡
     */
    @Override
    public void bingBankCard(BindCardRequest bindCardRequest) {
        mAddBankCardView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mBankModel.bingBankCard(bindCardRequest).compose(mAddBankCardView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::bingBankCardSuccess, throwable -> mAddBankCardView.showErrMessage(throwable),
                        () -> mAddBankCardView.dismissLoading());
        mAddBankCardView.addSubscription(disposable);
    }

    private void bingBankCardSuccess(ResponseData responseData) {
        mAddBankCardView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            mAddBankCardView.getBindCardSuccess();
//            responseData.parseData(WalletResponse.class);
//            if (responseData.parsedData != null) {
//                WalletResponse response = (WalletResponse) responseData.parsedData;
//
//            }
        } else {
            mAddBankCardView.showToast(responseData.errorMsg);
        }
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
