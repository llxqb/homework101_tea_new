package com.shushan.thomework101.mvp.ui.activity.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerExpectedIncomeComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.ExpectedIncomeModule;
import com.shushan.thomework101.entity.response.ExpectedIncomeResponse;
import com.shushan.thomework101.mvp.ui.adapter.ExpectedIncomeAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 预计提成总收益收益
 */
public class ExpectedCommissionIncomeActivity extends BaseActivity implements ExpectedIncomeControl.ExpectedIncomeView {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.expected_income_tv)
    TextView mExpectedIncomeTv;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    ExpectedIncomeAdapter mExpectedIncomeAdapter;
    List<ExpectedIncomeResponse> expectedIncomeResponseList = new ArrayList<>();
    private View mEmptyView;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_expected_income);
        initInjectData();
    }

    @Override
    public void initView() {
        initEmptyView();
        mCommonTitleTv.setText("预计提成收益");
        mExpectedIncomeAdapter = new ExpectedIncomeAdapter(expectedIncomeResponseList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mExpectedIncomeAdapter);
    }

    @Override
    public void initData() {
//        for (int i = 0; i < 10; i++) {
//            ExpectedIncomeResponse expectedIncomeResponse = new ExpectedIncomeResponse();
//            expectedIncomeResponseList.add(expectedIncomeResponse);
//        }
        mExpectedIncomeAdapter.setEmptyView(mEmptyView);
    }

    private void initEmptyView() {
        mEmptyView = LayoutInflater.from(this).inflate(R.layout.empty_layout, (ViewGroup) mRecyclerView.getParent(), false);
        ImageView emptyIv = mEmptyView.findViewById(R.id.empty_iv);
        TextView emptyTv = mEmptyView.findViewById(R.id.empty_tv);
        emptyIv.setImageResource(R.mipmap.empty_money);
        emptyTv.setText(getResources().getString(R.string.empty_money));
    }

    @OnClick(R.id.common_left_iv)
    public void onViewClicked() {
        finish();
    }


    private void initInjectData() {
        DaggerExpectedIncomeComponent.builder().appComponent(getAppComponent())
                .expectedIncomeModule(new ExpectedIncomeModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }

}
