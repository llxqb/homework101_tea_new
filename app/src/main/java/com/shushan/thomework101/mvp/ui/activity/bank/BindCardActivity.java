package com.shushan.thomework101.mvp.ui.activity.bank;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerAddBankCardComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.AddBankCardModule;
import com.shushan.thomework101.entity.request.BindCardRequest;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.response.BankInfoResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 绑定银行卡
 */
public class BindCardActivity extends BaseActivity implements AddBankCardControl.AddBankCardView {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.card_name_tv)
    TextView mCardNameTv;
    @BindView(R.id.card_num_tv)
    TextView mCardNumTv;
    @BindView(R.id.bank_name_tv)
    TextView mBankNameTv;
    @BindView(R.id.card_type_tv)
    TextView mCardTypeTv;
    private User mUser;
    @Inject
    AddBankCardControl.PresenterAddBankCard mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_bind_card);
        initInjectData();
        mUser = mBuProcessor.getUser();
    }

    @Override
    public void initView() {
        mCommonTitleTv.setText("绑定银行卡");
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            String username = getIntent().getStringExtra("username");
            String cardNo = getIntent().getStringExtra("cardNo");
            String cardInfo = getIntent().getStringExtra("cardInfo");
            if (!TextUtils.isEmpty(cardInfo)) {
                String bank = cardInfo.split("-")[0];
                String cardType = cardInfo.split("-")[2];
                mCardNameTv.setText(username);
                mCardNumTv.setText(cardNo);
                mBankNameTv.setText(bank);
                mCardTypeTv.setText(cardType);
            }
        }
        //银行信息
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.token = mUser.token;
        mPresenter.onRequestBankInfo(tokenRequest);
    }


    @OnClick({R.id.common_left_iv, R.id.sure_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.sure_tv:
                //确认绑定
                bindBankCardInfo();
                break;
        }
    }

    private void bindBankCardInfo() {
        BindCardRequest bindCardRequest = new BindCardRequest();
        bindCardRequest.token = mUser.token;
        bindCardRequest.realname = mCardNameTv.getText().toString();
        bindCardRequest.card_no = mCardNumTv.getText().toString();
        bindCardRequest.bank = mBankNameTv.getText().toString();
        bindCardRequest.label = getLabelInfo();
        //根据银行名称查找label信息
        mPresenter.bingBankCard(bindCardRequest);
    }

    @Override
    public void getBankByCardSuccess(String data) {

    }

    BankInfoResponse mBankInfoResponse;

    @Override
    public void getBankInfoSuccess(BankInfoResponse bankInfoResponse) {
        mBankInfoResponse = bankInfoResponse;
    }

    private String getLabelInfo() {
        for (BankInfoResponse.DataBean dataBean : mBankInfoResponse.getData()) {
            if (dataBean.getBank().contains(mBankNameTv.getText().toString())) {
                return dataBean.getLabel();
            }
        }
        return "";
    }


    @Override
    public void getBindCardSuccess() {
        showToast("绑定成功");
        Intent i = new Intent();
        setResult(RESULT_OK, i);
        finish();
    }


    private void initInjectData() {
        DaggerAddBankCardComponent.builder().appComponent(getAppComponent())
                .addBankCardModule(new AddBankCardModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
