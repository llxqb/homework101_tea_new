package com.shushan.thomework101.mvp.ui.fragment.studentChange;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerStudentChangeRecordFragmentComponent;
import com.shushan.thomework101.di.modules.StudentChangeRecordFragmentModule;
import com.shushan.thomework101.di.modules.StudentReplaceDetailModule;
import com.shushan.thomework101.entity.response.StudentChangeRecordResponse;
import com.shushan.thomework101.mvp.ui.adapter.StudentChangeRecordAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我的学生变动  fragment
 * 全部
 */

public class StudentChangeOutRecordFragment extends BaseFragment implements StudentChangeRecordControl.StudentChangeRecordView {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    StudentChangeRecordAdapter mStudentChangeRecordAdapter;
    List<StudentChangeRecordResponse> studentChangeRecordResponseList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_change_all, container, false);
        unbinder = ButterKnife.bind(this, view);
        initializeInjector();
        initView();
        initData();
        return view;
    }


    @Override
    public void initView() {
        mStudentChangeRecordAdapter = new StudentChangeRecordAdapter(studentChangeRecordResponseList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mStudentChangeRecordAdapter);
    }

    @Override
    public void initData() {
        for (int i = 0; i < 10; i++) {
            StudentChangeRecordResponse studentChangeRecordResponse = new StudentChangeRecordResponse();
            studentChangeRecordResponseList.add(studentChangeRecordResponse);
        }
    }


    private void initializeInjector() {
        DaggerStudentChangeRecordFragmentComponent.builder().appComponent(((HomeworkApplication) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
                .studentReplaceDetailModule(new StudentReplaceDetailModule((AppCompatActivity) getActivity()))
                .studentChangeRecordFragmentModule(new StudentChangeRecordFragmentModule(this))
                .build().inject(this);
    }
}
