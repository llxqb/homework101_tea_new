package com.shushan.thomework101.mvp.ui.activity.bank;


import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.request.WithDrawRequest;
import com.shushan.thomework101.entity.response.MineBankCardResponse;
import com.shushan.thomework101.entity.response.WalletResponse;
import com.shushan.thomework101.entity.response.WithdrawResponse;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 * 我的钱包/提现 Activity
 */

public class WithdrawControl {
    public interface WithdrawView extends LoadDataView {
        void getWalletSuccess(WalletResponse walletResponse);

        void getDefaultCardSuccess(WithdrawResponse withdrawResponse);

        void getMineBankCardSuccess(MineBankCardResponse mineBankCardResponse);

        void getWithDrawSuccess();
    }

    public interface PresenterWithdraw extends Presenter<WithdrawView> {
        /**
         * 我的钱包
         */
        void onRequestWallet(TokenRequest tokenRequest);

        /**
         * 默认提现卡号
         */
        void onRequestDefaultCard(TokenRequest tokenRequest);

        /**
         * 我的银行卡
         */
        void onRequestMineCardInfo(TokenRequest tokenRequest);

        /**
         * 去提现
         */
        void onRequestWithdraw(WithDrawRequest withDrawRequest);
    }

}
