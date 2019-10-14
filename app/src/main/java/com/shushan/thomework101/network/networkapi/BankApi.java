package com.shushan.thomework101.network.networkapi;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by helei on 2017/4/27.
 * MainApi
 */

public interface BankApi {

    /**
     * 我的钱包
     */
    @POST("teacher/wallet")
    Observable<String> onRequestWallet(@Body String request);
    /**
     * 默认提现卡号
     */
    @POST("teacher/wallet/extract")
    Observable<String> onRequestDefaultCard(@Body String request);

    /**
     * 我的银行卡
     */
    @POST("teacher/wallet/my_bank")
    Observable<String> onRequestMineCardInfo(@Body String request);

    /**
     * 根据卡查找银行
     */
    @POST("teacher/bank/search")
    Observable<String> searchBankByCard(@Query("card") String cardNo);
    /**
     * 银行信息
     */
    @POST("teacher/wallet/bank_info")
    Observable<String> onRequestBankInfo(@Body String request);
    /**
     * 绑定银行卡
     */
    @POST("teacher/wallet/bank_card")
    Observable<String> bingBankCard(@Body String request);
    /**
     * 去提现
     */
    @POST("teacher/wallet/doextract")
    Observable<String> onRequestWithdraw(@Body String request);

}
