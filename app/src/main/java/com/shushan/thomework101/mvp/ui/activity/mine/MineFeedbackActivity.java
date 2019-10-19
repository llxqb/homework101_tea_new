package com.shushan.thomework101.mvp.ui.activity.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerMineFeedbackComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.MineFeedbackModule;
import com.shushan.thomework101.entity.response.FeedBackResponse;
import com.shushan.thomework101.mvp.ui.activity.student.StudentDetailActivity;
import com.shushan.thomework101.mvp.ui.activity.student.SubmitFeedbackContentActivity;
import com.shushan.thomework101.mvp.ui.adapter.TodayFeedBackAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.utils.DateUtil;
import com.shushan.thomework101.mvp.utils.SelectDialogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的辅导反馈
 */
public class MineFeedbackActivity extends BaseActivity implements MineFeedbackControl.MineFeedbackView {
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

    List<String> subjectList = new ArrayList<>();
    private View mEmptyView;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_mine_feedback);
        initInjectData();
    }

    @Override
    public void initView() {
        initEmptyView();
        mCommonTitleTv.setText("辅导反馈");
        mTodayFeedBackAdapter = new TodayFeedBackAdapter(todayFeedBackResponseList,mImageLoaderHelper);
        mFeedbackRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFeedbackRecyclerView.setAdapter(mTodayFeedBackAdapter);
        mFeedbackRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.student_avatar_iv:
                        startActivitys(StudentDetailActivity.class);
                        break;
                    case R.id.look_counselling_content_tv:
                        showToast("查看辅导内容");
                        break;
                    case R.id.edit_counselling_content_tv:
                        startActivitys(SubmitFeedbackContentActivity.class);
                        break;
//                    case R.id.item_mine_student_layout:
//                        showToast("" + position);
//                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        String[] subject = getResources().getStringArray(R.array.subject);
        subjectList.add("全部");
        subjectList.addAll(Arrays.asList(subject));
//        for (int i = 0; i < 10; i++) {
//            TodayFeedBackResponse todayFeedBackResponse = new TodayFeedBackResponse();
//            todayFeedBackResponseList.add(todayFeedBackResponse);
//        }
        mTodayFeedBackAdapter.setEmptyView(mEmptyView);
    }

    private void initEmptyView() {
        mEmptyView = LayoutInflater.from(this).inflate(R.layout.empty_layout, (ViewGroup) mFeedbackRecyclerView.getParent(), false);
        ImageView emptyIv = mEmptyView.findViewById(R.id.empty_iv);
        TextView emptyTv = mEmptyView.findViewById(R.id.empty_tv);
        emptyIv.setImageResource(R.mipmap.empty_feedback);
        emptyTv.setText(getResources().getString(R.string.empty_mine_feedback));
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
                mSelectDateTv.setText(DateUtil.dateTranString(date, "yyyy年MM月dd日"));
            }
        }).showBirthdayDialog();
    }


    /**
     * 选择科目
     */
    private void selectSubject() {
        new SelectDialogUtil(this, new SelectDialogUtil.SelectPickerListener() {
            @Override
            public void getSelectText(String text) {
                mSelectSubjectTv.setText(text);
            }

            @Override
            public void getSelectDate(Date date) {

            }
        }).selectSubject("选择科目", subjectList);
    }

    private void initInjectData() {
        DaggerMineFeedbackComponent.builder().appComponent(getAppComponent())
                .mineFeedbackModule(new MineFeedbackModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
