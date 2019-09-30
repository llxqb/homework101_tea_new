package com.shushan.thomework101.mvp.ui.activity.bank;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc: 添加银行卡
 */
public class AddBankCardActivity extends BaseActivity {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.card_name_et)
    EditText mCardNameEt;
    @BindView(R.id.card_num_et)
    EditText mCardNumEt;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_add_bank_card);
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

                break;
        }
    }
}
