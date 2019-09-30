package com.shushan.thomework101.mvp.ui.activity.bank;

import android.view.View;
import android.widget.TextView;

import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerWithdrawComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.WithdrawModule;
import com.shushan.thomework101.mvp.ui.activity.mine.ExpectedCommissionIncomeActivity;
import com.shushan.thomework101.mvp.ui.activity.mine.ExpectedTotalIncomeActivity;
import com.shushan.thomework101.mvp.ui.activity.mine.RevenueIncomeActivity;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的钱包
 */
public class WalletActivity extends BaseActivity implements WithdrawControl.WithdrawView {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.earned_income_tv)
    TextView mEarnedIncomeTv;
    @BindView(R.id.total_income_tv)
    TextView mTotalIncomeTv;
    @BindView(R.id.commission_income_tv)
    TextView mCommissionIncomeTv;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_wallet);
        ((HomeworkApplication) getApplication()).getAppComponent().inject(this);
        initInjectData();
    }

    @Override
    public void initView() {
        mCommonTitleTv.setText("我的钱包");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.common_left_iv, R.id.money_detailed_tv, R.id.withdraw_tv, R.id.total_income_rl, R.id.commission_income_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.money_detailed_tv://已到手金额明细
                startActivitys(RevenueIncomeActivity.class);
                break;
            case R.id.withdraw_tv://提现
                startActivitys(WithdrawActivity.class);
                break;
            case R.id.total_income_rl://预计总收益
                startActivitys(ExpectedTotalIncomeActivity.class);
                break;
            case R.id.commission_income_rl://预计提成总收益
                startActivitys(ExpectedCommissionIncomeActivity.class);
                break;
        }
    }

    private void initInjectData() {
        DaggerWithdrawComponent.builder().appComponent(getAppComponent())
                .withdrawModule(new WithdrawModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }

}
