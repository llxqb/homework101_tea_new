package com.shushan.thomework101.mvp.ui.activity.mine;

import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc:操作介绍
 */
public class IntroductionActivity extends BaseActivity {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.image)
    ImageView mImage;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_introduction);
    }

    @Override
    public void initView() {
        mCommonTitleTv.setText("操作介绍");
    }

    @Override
    public void initData() {

    }


    @OnClick(R.id.common_left_iv)
    public void onViewClicked() {
        finish();
    }
}
