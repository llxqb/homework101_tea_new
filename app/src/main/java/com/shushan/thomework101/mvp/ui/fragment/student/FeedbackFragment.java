package com.shushan.thomework101.mvp.ui.fragment.student;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerFeedbackFragmentComponent;
import com.shushan.thomework101.di.modules.FeedbackFragmentModule;
import com.shushan.thomework101.di.modules.MainModule;
import com.shushan.thomework101.entity.constants.ActivityConstant;
import com.shushan.thomework101.entity.request.FeedbackRequest;
import com.shushan.thomework101.entity.response.FeedBackResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.activity.student.SubmitFeedbackContentActivity;
import com.shushan.thomework101.mvp.ui.adapter.TodayFeedBackAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;

/**
 * 学生页面 -- 今日反馈fragment
 */

public class FeedbackFragment extends BaseFragment implements FeedbackFragmentControl.FeedbackFragmentView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.swipe_ly)
    SwipeRefreshLayout mSwipeLy;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    TodayFeedBackAdapter mTodayFeedBackAdapter;
    List<FeedBackResponse.DataBean> todayFeedBackResponseList = new ArrayList<>();
    private View mEmptyView;
    private User mUser;

    private int page = 1;
    private int pageSize = 10;
    @Inject
    FeedbackFragmentControl.FeedbackFragmentPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        unbinder = ButterKnife.bind(this, view);
        initializeInjector();
        initView();
        initData();
        return view;
    }

    @Override
    public void onReceivePro(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(ActivityConstant.UPDATE_FEEDBACK_LIST)) {
            onRefresh();
        }
        super.onReceivePro(context, intent);
    }

    @Override
    public void addFilter() {
        super.addFilter();
        mFilter.addAction(ActivityConstant.UPDATE_FEEDBACK_LIST);
    }

    @Override
    public void initView() {
        mUser = mBuProcessor.getUser();
        initEmptyView();
        mSwipeLy.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeLy.setOnRefreshListener(this);
        mTodayFeedBackAdapter = new TodayFeedBackAdapter(todayFeedBackResponseList, mImageLoaderHelper);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mTodayFeedBackAdapter);
        mTodayFeedBackAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                FeedBackResponse.DataBean dataBean = (FeedBackResponse.DataBean) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.look_counselling_content_tv:
                        //启动单聊页面
                        if (dataBean != null) {
                            RongIM.getInstance().startPrivateChat(Objects.requireNonNull(getActivity()), dataBean.getThird_id(), dataBean.getName());
                        }
                        break;
                    case R.id.edit_counselling_content_tv://填写辅导反馈
                        if (dataBean != null && dataBean.getStatus() != 1) {
                            SubmitFeedbackContentActivity.start(getActivity(), String.valueOf(dataBean.getId()), dataBean.getName(), dataBean.getFeedback_time());
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        onRequestFeedbackInfo();
    }

    @Override
    public void onRefresh() {
        page = 1;
        onRequestFeedbackInfo();
    }


    /**
     * 请求辅导反馈数据
     */
    private void onRequestFeedbackInfo() {
        FeedbackRequest feedbackRequest = new FeedbackRequest();
        feedbackRequest.token = mUser.token;
        feedbackRequest.page = page + "";
        feedbackRequest.pagesize = String.valueOf(pageSize);
        mPresenter.onRequestFeedbackInfo(feedbackRequest);
    }

    @Override
    public void getFeedbackInfoSuccess(FeedBackResponse response) {
        todayFeedBackResponseList = response.getData();
        if (mSwipeLy.isRefreshing()) {
            mSwipeLy.setRefreshing(false);
            if (!response.getData().isEmpty()) {
                mTodayFeedBackAdapter.setNewData(response.getData());
            } else {
                mTodayFeedBackAdapter.setNewData(null);
                mTodayFeedBackAdapter.setEmptyView(mEmptyView);
            }
        } else {
            if (!response.getData().isEmpty()) {
                if (page == 1) {
                    mTodayFeedBackAdapter.setNewData(response.getData());
                } else {
                    mTodayFeedBackAdapter.addData(response.getData());
                }
            } else {
                if (page == 1) {
                    mTodayFeedBackAdapter.setNewData(null);
                    mTodayFeedBackAdapter.setEmptyView(mEmptyView);
                }
            }
        }
    }

    @Override
    public void getFeedbackInfoFail() {
        dismissLoading();
        if (mSwipeLy.isRefreshing()) {
            mSwipeLy.setRefreshing(false);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        if (!todayFeedBackResponseList.isEmpty()) {
            if (page == 1 && todayFeedBackResponseList.size() < pageSize) {
                mTodayFeedBackAdapter.loadMoreEnd(true);
            } else {
                if (todayFeedBackResponseList.size() < pageSize) {
                    mTodayFeedBackAdapter.loadMoreEnd();
                } else {
                    //等于10条
                    page++;
                    mTodayFeedBackAdapter.loadMoreComplete();
                    onRequestFeedbackInfo();
                }
            }
        } else {
            mTodayFeedBackAdapter.loadMoreEnd();
        }
    }

    private void initEmptyView() {
        mEmptyView = LayoutInflater.from(getActivity()).inflate(R.layout.empty_layout, (ViewGroup) mRecyclerView.getParent(), false);
        ImageView emptyIv = mEmptyView.findViewById(R.id.empty_iv);
        TextView emptyTv = mEmptyView.findViewById(R.id.empty_tv);
        emptyIv.setImageResource(R.mipmap.empty_feedback);
        if (!mUser.checkPass) {
            emptyTv.setText(getResources().getString(R.string.empty_feedback));
        } else {
            emptyTv.setText("暂无反馈信息");
        }
    }


    private void initializeInjector() {
        DaggerFeedbackFragmentComponent.builder().appComponent(((HomeworkApplication) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
                .mainModule(new MainModule((AppCompatActivity) getActivity()))
                .feedbackFragmentModule(new FeedbackFragmentModule(this))
                .build().inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
