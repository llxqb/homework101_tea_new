package com.shushan.thomework101.mvp.ui.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerFeedbackComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.FeedbackModule;
import com.shushan.thomework101.entity.constants.ActivityConstant;
import com.shushan.thomework101.entity.request.FeedbackRequest;
import com.shushan.thomework101.entity.response.FeedBackResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.activity.student.FeedbackControl;
import com.shushan.thomework101.mvp.ui.activity.student.SubmitFeedbackContentActivity;
import com.shushan.thomework101.mvp.ui.adapter.TodayFeedBackAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.utils.DateUtil;
import com.shushan.thomework101.mvp.utils.SelectDialogUtil;
import com.shushan.thomework101.mvp.utils.UserUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

/**
 * 我的辅导反馈
 */
public class MineFeedbackActivity extends BaseActivity implements FeedbackControl.FeedbackView, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.common_left_iv)
    ImageView mCommonLeftIv;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.common_toolbar_layout)
    RelativeLayout mCommonToolbarLayout;
    @BindView(R.id.select_date_tv)
    TextView mSelectDateTv;
    @BindView(R.id.select_subject_tv)
    TextView mSelectSubjectTv;
    @BindView(R.id.feedback_recycler_view)
    RecyclerView mFeedbackRecyclerView;
    TodayFeedBackAdapter mTodayFeedBackAdapter;
    List<FeedBackResponse.DataBean> todayFeedBackResponseList = new ArrayList<>();
    List<String> studentTypeList = new ArrayList<>();
    private View mEmptyView;
    private User mUser;
    private int page = 1;
    private int pageSize = 10;
    /**
     * 请求辅导反馈数据
     * search_time:当天年月日时间戳，不传默认当天
     * state:0全部1免费体验学生2月辅导3季辅导4年辅导 不传默认全部
     */
    private String search_time;
    private String state;
    @Inject
    FeedbackControl.PresenterFeedback mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_mine_feedback);
        initInjectData();
        mUser = mBuProcessor.getUser();
    }

    @Override
    public void onReceivePro(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(ActivityConstant.UPDATE_FEEDBACK_LIST)) {
            page = 1;
            onRequestFeedbackInfo();
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
        initEmptyView();
        mCommonTitleTv.setText("辅导反馈");
        mTodayFeedBackAdapter = new TodayFeedBackAdapter(todayFeedBackResponseList, mImageLoaderHelper);
        mFeedbackRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFeedbackRecyclerView.setAdapter(mTodayFeedBackAdapter);
        mTodayFeedBackAdapter.setOnLoadMoreListener(this, mFeedbackRecyclerView);
        mFeedbackRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                FeedBackResponse.DataBean dataBean = (FeedBackResponse.DataBean) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.look_counselling_content_tv:
                        //启动单聊页面
                        if (dataBean != null) {
                            RongIM.getInstance().startPrivateChat(MineFeedbackActivity.this, dataBean.getThird_id(), dataBean.getName());
                        }
                        break;
                    case R.id.edit_counselling_content_tv:
                        if (dataBean != null && dataBean.getStatus() != 1) {
                            SubmitFeedbackContentActivity.start(MineFeedbackActivity.this, String.valueOf(dataBean.getId()), dataBean.getName(), dataBean.getFeedback_time());
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        mSelectDateTv.setText(DateUtil.getTimeByPattern(DateUtil.TIME_YYMMDD_CHINA));//默认显示当前日期
        String[] studentType = getResources().getStringArray(R.array.student_type);
        studentTypeList.addAll(Arrays.asList(studentType));
        onRequestFeedbackInfo();
    }


    @OnClick({R.id.common_left_iv, R.id.select_date_ll, R.id.select_subject_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.select_date_ll:
                showBirthdayDialog();
                break;
            case R.id.select_subject_ll:
                selectSubject();
                break;
        }
    }


    private void onRequestFeedbackInfo() {
        FeedbackRequest feedbackRequest = new FeedbackRequest();
        feedbackRequest.token = mUser.token;
        if (!TextUtils.isEmpty(search_time)) {
            feedbackRequest.search_time = search_time;
        }
        if (!TextUtils.isEmpty(state)) {
            feedbackRequest.state = state;
        }
        feedbackRequest.page = String.valueOf(page);
        feedbackRequest.pagesize = String.valueOf(pageSize);
        mPresenter.onRequestFeedbackInfo(feedbackRequest);
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

    @Override
    public void getFeedbackInfoSuccess(FeedBackResponse response) {
        todayFeedBackResponseList = response.getData();
        //加载更多这样设置
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

    @Override
    public void submitFeedbackInfoSuccess() {
    }


    private void initEmptyView() {
        mEmptyView = LayoutInflater.from(this).inflate(R.layout.empty_layout, (ViewGroup) mFeedbackRecyclerView.getParent(), false);
        ImageView emptyIv = mEmptyView.findViewById(R.id.empty_iv);
        TextView emptyTv = mEmptyView.findViewById(R.id.empty_tv);
        emptyIv.setImageResource(R.mipmap.empty_feedback);
        emptyTv.setText(getResources().getString(R.string.empty_mine_feedback));
    }


    /**
     * 选择生日弹框
     */
    private void showBirthdayDialog() {
        new SelectDialogUtil(this, new SelectDialogUtil.SelectPickerListener() {
            @Override
            public void getSelectText(String text) {
            }

            @Override
            public void getSelectDate(Date date) {
                String dateTitle = DateUtil.dateTranString(date, DateUtil.TIME_YYMMDD_CHINA);
                mSelectDateTv.setText(dateTitle);
                search_time = DateUtil.getTime(dateTitle, DateUtil.TIME_YYMMDD_CHINA);
                page = 1;
                onRequestFeedbackInfo();
            }
        }).showBirthdayDialog();
    }


    /**
     * 选择学生辅导类型
     */
    private void selectSubject() {
        new SelectDialogUtil(this, new SelectDialogUtil.SelectPickerListener() {
            @Override
            public void getSelectText(String text) {
                mSelectSubjectTv.setText(text);
                page = 1;
                state = String.valueOf(UserUtil.studentTypeToInt(text));
                onRequestFeedbackInfo();
            }

            @Override
            public void getSelectDate(Date date) {

            }
        }).selectSubject("选择学生辅导类型", studentTypeList);
    }

    private void initInjectData() {
        DaggerFeedbackComponent.builder().appComponent(getAppComponent())
                .feedbackModule(new FeedbackModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
