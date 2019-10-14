package com.shushan.thomework101.mvp.model;

import com.google.gson.Gson;
import com.shushan.thomework101.entity.request.BindCardRequest;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.request.WithDrawRequest;
import com.shushan.thomework101.network.networkapi.BankApi;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by li.liu on 2019/10/14.
 * LoginModel
 */

public class BankModel {
    private final BankApi mBankApi;
    private final Gson mGson;
    private final ModelTransform mTransform;

    @Inject
    public BankModel(BankApi api, Gson gson, ModelTransform transform) {
        mBankApi = api;
        mGson = gson;
        mTransform = transform;
    }

    /**
     * 我的钱包
     */
    public Observable<ResponseData> onRequestWallet(TokenRequest request) {
        return mBankApi.onRequestWallet(new Gson().toJson(request)).map(mTransform::transformCommon);
    }

    /**
     * 默认提现卡号
     */
    public Observable<ResponseData> onRequestDefaultCard(TokenRequest request) {
        return mBankApi.onRequestDefaultCard(new Gson().toJson(request)).map(mTransform::transformCommon);
    }

    /**
     * 我的银行卡
     */
    public Observable<ResponseData> onRequestMineCardInfo(TokenRequest request) {
        return mBankApi.onRequestMineCardInfo(new Gson().toJson(request)).map(mTransform::transformListType);
    }

    /**
     * 根据卡查找银行
     */
    public Observable<ResponseData> searchBankByCard(String cardNo) {
        return mBankApi.searchBankByCard(cardNo).map(mTransform::transformCommon);
    }

    /**
     * 银行信息
     */
    public Observable<ResponseData> onRequestBankInfo(TokenRequest tokenRequest) {
        return mBankApi.onRequestBankInfo(new Gson().toJson(tokenRequest)).map(mTransform::transformListType);
    }

    /**
     * 绑定银行卡
     */
    public Observable<ResponseData> bingBankCard(BindCardRequest request) {
        return mBankApi.bingBankCard(new Gson().toJson(request)).map(mTransform::transformListType);
    }
    /**
     * 去提现
     */
    public Observable<ResponseData> onRequestWithdraw(WithDrawRequest request) {
        return mBankApi.onRequestWithdraw(new Gson().toJson(request)).map(mTransform::transformCommon);
    }


}
