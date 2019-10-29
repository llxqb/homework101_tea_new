package com.shushan.thomework101.mvp.ui.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerExpectedIncomeComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.ExpectedIncomeModule;
import com.shushan.thomework101.entity.request.RevenueIncomeRequest;
import com.shushan.thomework101.entity.response.ExpectedIncomeResponse;
import com.shushan.thomework101.entity.response.RevenueIncomeResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.activity.bank.WithdrawActivity;
import com.shushan.thomework101.mvp.ui.adapter.RevenueIncomeAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 已到手金额明细
 */
public class RevenueIncomeActivity extends BaseActivity implements ExpectedIncomeControl.ExpectedIncomeView, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.expected_income_tv)
    TextView mExpectedIncomeTv;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    RevenueIncomeAdapter mRevenueIncomeAdapter;
    List<RevenueIncomeResponse.DataBean> expectedIncomeResponseList = new ArrayList<>();
    private View mEmptyView;
    private User mUser;
    private int page = 1;
    private int pageSize = 10;
    String withDrawMoney;//可提现金额

    @Inject
    ExpectedIncomeControl.PresenterExpectedIncome mPresenter;

    public static void start(Context context, String revenueIncome, String withDrawMoney) {
        Intent intent = new Intent(context, RevenueIncomeActivity.class);
        intent.putExtra("revenueIncome", revenueIncome);
        intent.putExtra("withDrawMoney", withDrawMoney);
        context.startActivity(intent);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_revenue_income);
        initInjectData();
        mUser = mBuProcessor.getUser();
    }

    @Override
    public void initView() {
        initEmptyView();
        mCommonTitleTv.setText("已到手金额");
        mRevenueIncomeAdapter = new RevenueIncomeAdapter(expectedIncomeResponseList, mImageLoaderHelper);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRevenueIncomeAdapter);
        mRevenueIncomeAdapter.setOnLoadMoreListener(this, mRecyclerView);
    }


    @Override
    public void initData() {
        if (getIntent() != null) {
            String revenueIncome = getIntent().getStringExtra("revenueIncome");//已到手金额
            withDrawMoney = getIntent().getStringExtra("withDrawMoney");
            mExpectedIncomeTv.setText(revenueIncome);
        }
        onRequestRevenueIncome();
    }

    private void initEmptyView() {
        mEmptyView = LayoutInflater.from(this).inflate(R.layout.empty_layout, (ViewGroup) mRecyclerView.getParent(), false);
        ImageView emptyIv = mEmptyView.findViewById(R.id.empty_iv);
        TextView emptyTv = mEmptyView.findViewById(R.id.empty_tv);
        emptyIv.setImageResource(R.mipmap.empty_money);
        emptyTv.setText(getResources().getString(R.string.empty_money));
    }

    @OnClick({R.id.common_left_iv, R.id.withdraw_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.withdraw_tv:
                //提现
                WithdrawActivity.start(this, withDrawMoney);
                break;
        }
    }

    /**
     * 已到手金额明细
     */
    private void onRequestRevenueIncome() {
        RevenueIncomeRequest revenueIncomeRequest = new RevenueIncomeRequest();
        revenueIncomeRequest.token = mUser.token;
        revenueIncomeRequest.page = String.valueOf(page);
        revenueIncomeRequest.pagesize = String.valueOf(pageSize);
        mPresenter.onRequestRevenueIncome(revenueIncomeRequest);
    }

    @Override
    public void getExpectedIncomeSuccess(ExpectedIncomeResponse expectedIncomeResponse) {
    }

    @Override
    public void getRevenueIncomeSuccess(RevenueIncomeResponse revenueIncomeResponse) {
        expectedIncomeResponseList = revenueIncomeResponse.getData();
        if (!revenueIncomeResponse.getData().isEmpty()) {
            mRevenueIncomeAdapter.addData(revenueIncomeResponse.getData());
        } else {
            mRevenueIncomeAdapter.setEmptyView(mEmptyView);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        if (!expectedIncomeResponseList.isEmpty()) {
            if (page == 1 && expectedIncomeResponseList.size() < pageSize) {
                mRevenueIncomeAdapter.loadMoreEnd(true);
            } else {
                if (expectedIncomeResponseList.size() < pageSize) {
                    mRevenueIncomeAdapter.loadMoreEnd();
                } else {
                    //等于10条
                    page++;
                    mRevenueIncomeAdapter.loadMoreComplete();
                    onRequestRevenueIncome();
                }
            }
        } else {
            mRevenueIncomeAdapter.loadMoreEnd();
        }
    }

    private void initInjectData() {
        DaggerExpectedIncomeComponent.builder().appComponent(getAppComponent())
                .expectedIncomeModule(new ExpectedIncomeModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
