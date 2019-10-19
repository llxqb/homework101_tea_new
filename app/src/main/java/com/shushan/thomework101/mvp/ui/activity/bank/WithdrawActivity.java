package com.shushan.thomework101.mvp.ui.activity.bank;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerWithdrawComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.WithdrawModule;
import com.shushan.thomework101.entity.constants.ActivityConstant;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.request.WithDrawRequest;
import com.shushan.thomework101.entity.response.MineBankCardResponse;
import com.shushan.thomework101.entity.response.WalletResponse;
import com.shushan.thomework101.entity.response.WithdrawResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.utils.BankUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc:提现
 */
public class WithdrawActivity extends BaseActivity implements WithdrawControl.WithdrawView {
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.withdraw_money_et)
    EditText mWithdrawMoneyEt;
    @BindView(R.id.can_withdraw_money_tv)
    TextView mCanWithdrawMoneyTv;
    @BindView(R.id.sure_tv)
    TextView mSureTv;
    @BindView(R.id.bank_layout)
    RelativeLayout mBankLayout;
    @BindView(R.id.bank_icon_iv)
    ImageView mBankIconIv;
    @BindView(R.id.bank_name_tv)
    TextView mBankNameTv;
    @BindView(R.id.card_num_tv)
    TextView mCardNumTv;
    @BindView(R.id.add_wallet_rl)
    RelativeLayout mAddWalletRl;//无银行卡
    @BindView(R.id.mine_card_rl)
    RelativeLayout mMineCardRl;//有银行卡
    String withdrawMoney;//可提现金额
    private User mUser;
    /**
     * 银行卡号
     */
    private String cardNo = null;
    @Inject
    WithdrawControl.PresenterWithdraw mPresenter;

    public static void start(Context context, String withdrawMoney) {
        Intent intent = new Intent(context, WithdrawActivity.class);
        intent.putExtra("withdrawMoney", withdrawMoney);
        context.startActivity(intent);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_withdraw);
        initInjectData();
        mUser = mBuProcessor.getUser();
    }

    @Override
    public void onReceivePro(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(ActivityConstant.UPDATE_CARD_INFO)) {
            onRequestDefaultCard();
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
        mCommonTitleTv.setText("提现");
        mWithdrawMoneyEt.addTextChangedListener(edit_text_OnChange);

    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            withdrawMoney = getIntent().getStringExtra("withdrawMoney");
            String withdrawMoneyValue = "可提现金额：¥" + withdrawMoney;
            mCanWithdrawMoneyTv.setText(withdrawMoneyValue);
        }
        onRequestDefaultCard();
    }


    @OnClick({R.id.common_left_iv, R.id.add_wallet_rl, R.id.mine_card_rl, R.id.withdraw_all_money_tv, R.id.sure_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.add_wallet_rl:
                //添加银行卡
                startActivitys(AddBankCardActivity.class);
                break;
            case R.id.mine_card_rl:
                //查看我的银行卡
                Intent intent = new Intent(WithdrawActivity.this, MineBankCardActivity.class);
                startActivityForResult(intent, 101);
                break;
            case R.id.withdraw_all_money_tv:
                mWithdrawMoneyEt.setText(withdrawMoney);
                break;
            case R.id.sure_tv:
                if (verify()) {
                    onRequestWithdraw();
                }
                break;
        }
    }

    private boolean verify() {
        String WithdrawMoneyValue = mWithdrawMoneyEt.getText().toString();
        if (cardNo == null) {
            showToast("请先添加银行卡");
            return false;
        }
        if (TextUtils.isEmpty(WithdrawMoneyValue) || Float.parseFloat(WithdrawMoneyValue) == 0) {
            showToast("提现金额必须大于0");
            return false;
        }
        if (Float.parseFloat(WithdrawMoneyValue) > Float.parseFloat(withdrawMoney)) {
            showToast("提现金额小于可提现金额");
            return false;
        }
        return true;
    }

    /**
     * 请求 提现默认卡号
     */
    private void onRequestDefaultCard() {
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.token = mUser.token;
        mPresenter.onRequestDefaultCard(tokenRequest);
    }

    /**
     * 去提现
     */
    private void onRequestWithdraw() {
        WithDrawRequest withDrawRequest = new WithDrawRequest();
        withDrawRequest.token = mUser.token;
        withDrawRequest.card = cardNo;
        withDrawRequest.money = Float.parseFloat(mWithdrawMoneyEt.getText().toString());
        mPresenter.onRequestWithdraw(withDrawRequest);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 101) {
            updateCardInfo(data);
        }
    }

    private void updateCardInfo(Intent resultIntent) {
        String label = resultIntent.getStringExtra("label").toLowerCase();
        String bank = resultIntent.getStringExtra("bank");
        cardNo = resultIntent.getStringExtra("card_no");
        BankUtil.labelToBankIcon(label, mBankIconIv, mBankLayout);
        mBankNameTv.setText(bank);
        mCardNumTv.setText(BankUtil.getBankHidedNum(cardNo));
    }


    @Override
    public void getWalletSuccess(WalletResponse withdrawResponse) {
    }


    @Override
    public void getDefaultCardSuccess(WithdrawResponse withdrawResponse) {
        WithdrawResponse.BankBean bankBean = withdrawResponse.getBank();
        cardNo = bankBean.getCard_no();
        if (withdrawResponse.getBank() == null || new Gson().toJson(bankBean).equals("{}")) {
            mAddWalletRl.setVisibility(View.VISIBLE);
            mMineCardRl.setVisibility(View.GONE);
        } else {
            mAddWalletRl.setVisibility(View.GONE);
            mMineCardRl.setVisibility(View.VISIBLE);
            BankUtil.labelToBankIcon(bankBean.getLabel(), mBankIconIv, mBankLayout);
            mBankNameTv.setText(bankBean.getBank());
            mCardNumTv.setText(BankUtil.getBankHidedNum(bankBean.getCard_no()));
        }
    }

    @Override
    public void getMineBankCardSuccess(MineBankCardResponse mineBankCardResponse) {

    }

    @Override
    public void getWithDrawSuccess() {
//        onRequestDefaultCard();
        showToast("申请提现成功，请等待审核");
        //TODO 刷新可提现金额数字
    }


    public TextWatcher edit_text_OnChange = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(s.toString())) {
                if (Float.parseFloat(s.toString()) > 0 && Float.parseFloat(s.toString()) <= Float.parseFloat(withdrawMoney)) {
                    mSureTv.setBackgroundResource(R.drawable.withdraw_sure_bg);
                } else {
                    mSureTv.setBackgroundResource(R.drawable.not_withdraw_sure_bg);
                }
            }
        }
    };


    private void initInjectData() {
        DaggerWithdrawComponent.builder().appComponent(getAppComponent())
                .withdrawModule(new WithdrawModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
