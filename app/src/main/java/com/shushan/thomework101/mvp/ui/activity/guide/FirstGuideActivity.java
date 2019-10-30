package com.shushan.thomework101.mvp.ui.activity.guide;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.mvp.ui.activity.guide.login.LoginActivity;
import com.shushan.thomework101.mvp.ui.adapter.LocalImagesPagerAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class FirstGuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.btn_start_right_now)
    ImageView mBtnStartRightNow;

    private int[] resIdArr = new int[]{R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3, R.mipmap.guide_4, R.mipmap.guide_5};

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_first_guide);
        ((HomeworkApplication) getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        LocalImagesPagerAdapter adapter = new LocalImagesPagerAdapter(this, resIdArr);
        mViewpager.setAdapter(adapter);
        mViewpager.addOnPageChangeListener(this);
    }


    @OnClick(R.id.btn_start_right_now)
    public void onViewClicked() {
        startActivitys(LoginActivity.class);
        mSharePreferenceUtil.setData("first_guide", true);
        finish();
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == (resIdArr.length - 1)) {
            mBtnStartRightNow.setVisibility(View.VISIBLE);
        } else {
            mBtnStartRightNow.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int position) {

    }
}
