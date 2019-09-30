package com.shushan.thomework101.mvp.ui.activity.bank;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerWithdrawComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.WithdrawModule;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 提现
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
    @BindView(R.id.bank_icon_iv)
    ImageView mBankIconIv;
    @BindView(R.id.bank_name_tv)
    TextView mBankNameTv;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_withdraw);
        initInjectData();
    }

    @Override
    public void initView() {
        mCommonTitleTv.setText("提现");

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.common_left_iv, R.id.add_wallet_rl,R.id.mine_card_rl, R.id.withdraw_all_money_tv, R.id.sure_tv})
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
                startActivitys(MineBankCardActivity.class);
                break;
            case R.id.withdraw_all_money_tv:

                break;
            case R.id.sure_tv:
                break;
        }
    }


    private void initInjectData() {
        DaggerWithdrawComponent.builder().appComponent(getAppComponent())
                .withdrawModule(new WithdrawModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
