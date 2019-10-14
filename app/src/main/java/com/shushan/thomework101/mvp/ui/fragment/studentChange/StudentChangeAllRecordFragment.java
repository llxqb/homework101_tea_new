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
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerStudentChangeRecordFragmentComponent;
import com.shushan.thomework101.di.modules.StudentChangeRecordFragmentModule;
import com.shushan.thomework101.di.modules.StudentReplaceDetailModule;
import com.shushan.thomework101.entity.request.StudentChangeRequest;
import com.shushan.thomework101.entity.response.StudentChangeRecordResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.adapter.StudentChangeRecordAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我的学生变动  fragment
 * 全部
 */

public class StudentChangeAllRecordFragment extends BaseFragment implements StudentChangeRecordControl.StudentChangeRecordView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    StudentChangeRecordAdapter mStudentChangeRecordAdapter;
    List<StudentChangeRecordResponse.DataBean> studentChangeRecordResponseList = new ArrayList<>();
    private View mEmptyView;
    private User mUser;
    private int page = 1;
    private int pageSize = 10;
    @Inject
    StudentChangeRecordControl.StudentChangeRecordFragmentPresenter mPresenter;

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
        mUser = mBuProcessor.getUser();
        initEmptyView();
        mStudentChangeRecordAdapter = new StudentChangeRecordAdapter(studentChangeRecordResponseList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mStudentChangeRecordAdapter);
    }

    @Override
    public void initData() {
        onRequestStudentChange();
    }

    private void initEmptyView() {
        mEmptyView = LayoutInflater.from(getActivity()).inflate(R.layout.empty_layout, (ViewGroup) mRecyclerView.getParent(), false);
        ImageView emptyIv = mEmptyView.findViewById(R.id.empty_iv);
        TextView emptyTv = mEmptyView.findViewById(R.id.empty_tv);
        emptyIv.setImageResource(R.mipmap.empty_student);
        emptyTv.setText(getResources().getString(R.string.empty_student_change));
    }


    private void onRequestStudentChange() {
        StudentChangeRequest request = new StudentChangeRequest();
        request.token = mUser.token;
        request.page = String.valueOf(page);
        request.pagesize = String.valueOf(pageSize);
        mPresenter.onRequestStudentChange(request);
    }

    @Override
    public void getStudentChangeSuccess(StudentChangeRecordResponse studentChangeRecordResponse) {
        if (!studentChangeRecordResponse.getData().isEmpty()) {
            mStudentChangeRecordAdapter.addData(studentChangeRecordResponse.getData());
        } else {
            mStudentChangeRecordAdapter.setEmptyView(mEmptyView);
        }
    }

    private void initializeInjector() {
        DaggerStudentChangeRecordFragmentComponent.builder().appComponent(((HomeworkApplication) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
                .studentReplaceDetailModule(new StudentReplaceDetailModule((AppCompatActivity) getActivity()))
                .studentChangeRecordFragmentModule(new StudentChangeRecordFragmentModule(this))
                .build().inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
