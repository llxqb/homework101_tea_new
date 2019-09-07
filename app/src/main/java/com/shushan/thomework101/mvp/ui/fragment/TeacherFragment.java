package com.shushan.thomework101.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerTeacherFragmentComponent;
import com.shushan.thomework101.di.modules.MainModule;
import com.shushan.thomework101.di.modules.TeacherFragmentModule;
import com.shushan.thomework101.mvp.ui.base.BaseFragment;
import com.shushan.thomework101.mvp.utils.StatusBarUtil;

import java.util.Objects;

/**
 * TeacherFragment
 * 老师
 */

public class TeacherFragment extends BaseFragment implements TeacherFragmentControl.TeacherView {

    public static TeacherFragment newInstance() {
        return new TeacherFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher, container, false);
        initializeInjector();
        StatusBarUtil.setTransparentForImageView(getActivity(), null);
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
        DaggerTeacherFragmentComponent.builder().appComponent(((HomeworkApplication) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
                .mainModule(new MainModule((AppCompatActivity) getActivity()))
                .teacherFragmentModule(new TeacherFragmentModule(this))
                .build().inject(this);
    }

}
