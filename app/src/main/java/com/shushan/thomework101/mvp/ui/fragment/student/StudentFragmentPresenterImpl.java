package com.shushan.thomework101.mvp.ui.fragment.student;

import android.content.Context;

import com.shushan.thomework101.mvp.model.MainModel;

import javax.inject.Inject;

/**
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class StudentFragmentPresenterImpl implements StudentFragmentControl.StudentFragmentPresenter {

    private StudentFragmentControl.StudentView mStudentView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public StudentFragmentPresenterImpl(Context context, MainModel model, StudentFragmentControl.StudentView studentView) {
        mContext = context;
        mMainModel = model;
        mStudentView = studentView;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mStudentView = null;
    }


}
