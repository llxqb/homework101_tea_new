package com.shushan.thomework101.mvp.ui.fragment.student;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerFeedbackFragmentComponent;
import com.shushan.thomework101.di.modules.FeedbackFragmentModule;
import com.shushan.thomework101.di.modules.MainModule;
import com.shushan.thomework101.entity.response.TodayFeedBackResponse;
import com.shushan.thomework101.mvp.ui.activity.student.StudentDetailActivity;
import com.shushan.thomework101.mvp.ui.activity.student.SubmitFeedbackContentActivity;
import com.shushan.thomework101.mvp.ui.adapter.TodayFeedBackAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 学生页面 -- 今日反馈fragment
 */

public class FeedbackFragment extends BaseFragment implements FeedbackFragmentControl.FeedbackFragmentView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    TodayFeedBackAdapter mTodayFeedBackAdapter;
    List<TodayFeedBackResponse> todayFeedBackResponseList = new ArrayList<>();

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
    public void initView() {
        mTodayFeedBackAdapter = new TodayFeedBackAdapter(todayFeedBackResponseList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mTodayFeedBackAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
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
        for (int i = 0; i < 10; i++) {
            TodayFeedBackResponse todayFeedBackResponse = new TodayFeedBackResponse();
            todayFeedBackResponseList.add(todayFeedBackResponse);
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
