package com.shushan.thomework101.mvp.ui.fragment.student;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerFeedbackFragmentComponent;
import com.shushan.thomework101.di.modules.FeedbackFragmentModule;
import com.shushan.thomework101.di.modules.MainModule;
import com.shushan.thomework101.mvp.ui.base.BaseFragment;

import java.util.Objects;

/**
 * 学生页面 -- 今日反馈fragment
 */

public class FeedbackFragment extends BaseFragment implements FeedbackFragmentControl.FeedbackFragmentView {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        initializeInjector();
        initView();
        initData();
        return view;
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {
    }


    private void initializeInjector() {
        DaggerFeedbackFragmentComponent.builder().appComponent(((HomeworkApplication) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
                .mainModule(new MainModule((AppCompatActivity) getActivity()))
                .feedbackFragmentModule(new FeedbackFragmentModule(this))
                .build().inject(this);
    }

}
