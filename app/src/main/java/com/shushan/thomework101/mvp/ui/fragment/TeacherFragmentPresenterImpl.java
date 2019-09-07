package com.shushan.thomework101.mvp.ui.fragment;

import android.content.Context;


import com.shushan.thomework101.mvp.model.MainModel;

import javax.inject.Inject;

/**
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class TeacherFragmentPresenterImpl implements TeacherFragmentControl.TeacherFragmentPresenter {

    private TeacherFragmentControl.TeacherView mTeacherView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public TeacherFragmentPresenterImpl(Context context, MainModel model, TeacherFragmentControl.TeacherView teacherView) {
        mContext = context;
        mMainModel = model;
        mTeacherView = teacherView;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mTeacherView = null;
    }


}
