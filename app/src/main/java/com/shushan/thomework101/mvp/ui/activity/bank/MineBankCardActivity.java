package com.shushan.thomework101.mvp.ui.activity.bank;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc:我的银行卡
 */
public class MineBankCardActivity extends BaseActivity {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.common_right_iv)
    ImageView mCommonRightIv;
    @BindView(R.id.card_recycler_view)
    RecyclerView mCardRecyclerView;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_mine_bank_card);
    }

    @Override
    public void initView() {
        mCommonTitleTv.setText("我的银行卡");
        mCommonRightIv.setImageResource(R.mipmap.add);
        mCommonRightIv.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {

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
}
