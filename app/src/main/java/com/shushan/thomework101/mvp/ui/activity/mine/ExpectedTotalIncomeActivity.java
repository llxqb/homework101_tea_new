package com.shushan.thomework101.mvp.ui.activity.mine;

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
import com.shushan.thomework101.entity.request.ExpectedIncomeRequest;
import com.shushan.thomework101.entity.response.ExpectedIncomeResponse;
import com.shushan.thomework101.entity.response.RevenueIncomeResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.adapter.ExpectedIncomeAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc：预计总收益
 */
public class ExpectedTotalIncomeActivity extends BaseActivity implements ExpectedIncomeControl.ExpectedIncomeView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.expected_income_tv)
    TextView mExpectedIncomeTv;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    ExpectedIncomeAdapter mExpectedIncomeAdapter;
    List<ExpectedIncomeResponse.ListBean> expectedIncomeResponseList = new ArrayList<>();
    private View mEmptyView;
    private User mUser;
    private int page = 1;
    private int pageSize = 10;
    @Inject
    ExpectedIncomeControl.PresenterExpectedIncome mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_total_income);
        initInjectData();
        mUser = mBuProcessor.getUser();
    }

    @Override
    public void initView() {
        initEmptyView();
        mCommonTitleTv.setText("预计收益");
        mExpectedIncomeAdapter = new ExpectedIncomeAdapter(expectedIncomeResponseList, mImageLoaderHelper);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mExpectedIncomeAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mExpectedIncomeAdapter);
    }

    @Override
    public void initData() {
        onRequestTotalIncome();
    }

    private void initEmptyView() {
        mEmptyView = LayoutInflater.from(this).inflate(R.layout.empty_layout, (ViewGroup) mRecyclerView.getParent(), false);
        ImageView emptyIv = mEmptyView.findViewById(R.id.empty_iv);
        TextView emptyTv = mEmptyView.findViewById(R.id.empty_tv);
        emptyIv.setImageResource(R.mipmap.empty_money);
        emptyTv.setText(getResources().getString(R.string.empty_money));
    }

    private void onRequestTotalIncome() {
        ExpectedIncomeRequest expectedIncomeRequest = new ExpectedIncomeRequest();
        expectedIncomeRequest.token = mUser.token;
        expectedIncomeRequest.label = "0";//0全部2提成
        expectedIncomeRequest.page = String.valueOf(page);
        expectedIncomeRequest.pagesize = String.valueOf(pageSize);
        mPresenter.onRequestExpectedIncome(expectedIncomeRequest);
    }

    @Override
    public void getExpectedIncomeSuccess(ExpectedIncomeResponse expectedIncomeResponse) {
        expectedIncomeResponseList = expectedIncomeResponse.getList();
        mExpectedIncomeTv.setText(String.valueOf(expectedIncomeResponse.getAll()));
        if (!expectedIncomeResponse.getList().isEmpty()) {
            mExpectedIncomeAdapter.addData(expectedIncomeResponse.getList());
        } else {
            mExpectedIncomeAdapter.setEmptyView(mEmptyView);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        if (!expectedIncomeResponseList.isEmpty()) {
            if (page == 1 && expectedIncomeResponseList.size() < pageSize) {
                mExpectedIncomeAdapter.loadMoreEnd(true);
            } else {
                if (expectedIncomeResponseList.size() < pageSize) {
                    mExpectedIncomeAdapter.loadMoreEnd();
                } else {
                    //等于10条
                    page++;
                    mExpectedIncomeAdapter.loadMoreComplete();
                    onRequestTotalIncome();
                }
            }
        } else {
            mExpectedIncomeAdapter.loadMoreEnd();
        }
    }

    @Override
    public void getRevenueIncomeSuccess(RevenueIncomeResponse revenueIncomeResponse) {

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
