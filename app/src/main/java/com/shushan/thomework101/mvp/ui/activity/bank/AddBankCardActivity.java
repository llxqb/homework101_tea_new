package com.shushan.thomework101.mvp.ui.activity.bank;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerAddBankCardComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.AddBankCardModule;
import com.shushan.thomework101.entity.constants.ActivityConstant;
import com.shushan.thomework101.entity.response.BankInfoResponse;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc: 添加银行卡
 */
public class AddBankCardActivity extends BaseActivity implements AddBankCardControl.AddBankCardView {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.card_name_et)
    EditText mCardNameEt;
    @BindView(R.id.card_num_et)
    EditText mCardNumEt;
    @Inject
    AddBankCardControl.PresenterAddBankCard mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_add_bank_card);
        initInjectData();
    }

    @Override
    public void initView() {
        mCommonTitleTv.setText("添加银行卡");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.common_left_iv, R.id.next_step_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.next_step_tv:
                if (verify()) {
                    searchBankByCard();
                }
                break;
        }
    }

    private boolean verify() {
        String name = mCardNameEt.getText().toString();
        String cardNo = mCardNumEt.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showToast("持卡人姓名不能为空");
            return false;
        }
        if (TextUtils.isEmpty(cardNo)) {
            showToast("银行卡号不能为空");
            return false;
        }
        return true;
    }


    private void searchBankByCard() {
        mPresenter.searchBankByCard(mCardNumEt.getText().toString());
    }

    @Override
    public void getBankByCardSuccess(String data) {
//        BindCardActivity.start(this, , , data);
        Intent intent = new Intent(this, BindCardActivity.class);
        intent.putExtra("username", mCardNameEt.getText().toString());
        intent.putExtra("cardNo", mCardNumEt.getText().toString());
        intent.putExtra("cardInfo", data);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ActivityConstant.UPDATE_CARD_INFO));
            finish();
        }
    }

    @Override
    public void getBankInfoSuccess(BankInfoResponse bankInfoResponse) {

    }

    @Override
    public void getBindCardSuccess() {

    }

    private void initInjectData() {
        DaggerAddBankCardComponent.builder().appComponent(getAppComponent())
                .addBankCardModule(new AddBankCardModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
