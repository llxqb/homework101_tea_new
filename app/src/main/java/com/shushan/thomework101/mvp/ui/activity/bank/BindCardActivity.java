package com.shushan.thomework101.mvp.ui.activity.bank;

import android.view.View;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 绑定银行卡
 */
public class BindCardActivity extends BaseActivity {

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

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_bind_card);
    }

    @Override
    public void initView() {
        mCommonTitleTv.setText("绑定银行卡");
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.common_left_iv, R.id.sure_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.sure_tv:
                //确认绑定
                break;
        }
    }
}
