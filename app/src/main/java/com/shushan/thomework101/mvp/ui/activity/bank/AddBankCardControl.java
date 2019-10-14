package com.shushan.thomework101.mvp.ui.activity.bank;


import com.shushan.thomework101.entity.request.BindCardRequest;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.response.BankInfoResponse;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 * 添加银行卡
 */

public class AddBankCardControl {
    public interface AddBankCardView extends LoadDataView {
        void getBankByCardSuccess(String data);

        void getBankInfoSuccess(BankInfoResponse bankInfoResponse);

        void getBindCardSuccess();

    }

    public interface PresenterAddBankCard extends Presenter<AddBankCardView> {
        /**
         * 根据卡查找银行
         */
        void searchBankByCard(String cardNo);

        /**
         * 银行信息
         */
        void onRequestBankInfo(TokenRequest tokenRequest);

        /**
         * 绑定银行卡
         */
        void bingBankCard(BindCardRequest bindCardRequest);

    }

}
