package com.shushan.thomework101.mvp.ui.activity.bank;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerWithdrawComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.WithdrawModule;
import com.shushan.thomework101.entity.constants.ActivityConstant;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.response.MineBankCardResponse;
import com.shushan.thomework101.entity.response.WalletResponse;
import com.shushan.thomework101.entity.response.WithdrawResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.adapter.BankCardAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc:我的银行卡
 */
public class MineBankCardActivity extends BaseActivity implements WithdrawControl.WithdrawView {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.common_right_iv)
    ImageView mCommonRightIv;
    @BindView(R.id.card_recycler_view)
    RecyclerView mCardRecyclerView;
    BankCardAdapter mBankCardAdapter;
    List<MineBankCardResponse.DataBean> bankCardList = new ArrayList<>();
    private User mUser;
    @Inject
    WithdrawControl.PresenterWithdraw mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_mine_bank_card);
        initInjectData();
        mUser = mBuProcessor.getUser();
    }

    @Override
    public void onReceivePro(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(ActivityConstant.UPDATE_CARD_INFO)) {
            onRequestMineCard();
        }
        super.onReceivePro(context, intent);
    }

    @Override
    public void addFilter() {
        super.addFilter();
        mFilter.addAction(ActivityConstant.UPDATE_CARD_INFO);
    }

    @Override
    public void initView() {
        mCommonTitleTv.setText("我的银行卡");
        mCommonRightIv.setImageResource(R.mipmap.add);
        mCommonRightIv.setVisibility(View.VISIBLE);
        mBankCardAdapter = new BankCardAdapter(bankCardList);
        mCardRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCardRecyclerView.setAdapter(mBankCardAdapter);
        mBankCardAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            MineBankCardResponse.DataBean dataBean = (MineBankCardResponse.DataBean) adapter.getItem(position);
            if (dataBean != null) {
                Intent intent = new Intent();
                intent.putExtra("label", dataBean.getLabel());
                intent.putExtra("bank", dataBean.getBank());
                intent.putExtra("card_no", dataBean.getCard_no());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void initData() {
        onRequestMineCard();
    }


    @OnClick({R.id.common_left_iv, R.id.common_right_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.common_right_iv:
                startActivitys(AddBankCardActivity.class);
                break;
        }
    }

    private void onRequestMineCard() {
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.token = mUser.token;
        mPresenter.onRequestMineCardInfo(tokenRequest);
    }

    @Override
    public void getWalletSuccess(WalletResponse walletResponse) {

    }

    @Override
    public void getDefaultCardSuccess(WithdrawResponse withdrawResponse) {

    }

    @Override
    public void getMineBankCardSuccess(MineBankCardResponse mineBankCardResponse) {
        mBankCardAdapter.setNewData(mineBankCardResponse.getData());
    }

    @Override
    public void getWithDrawSuccess() {

    }

    private void initInjectData() {
        DaggerWithdrawComponent.builder().appComponent(getAppComponent())
                .withdrawModule(new WithdrawModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }

}
