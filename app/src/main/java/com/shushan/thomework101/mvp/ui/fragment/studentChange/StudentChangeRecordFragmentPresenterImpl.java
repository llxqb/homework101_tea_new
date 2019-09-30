package com.shushan.thomework101.mvp.ui.fragment.studentChange;

import android.content.Context;

import com.shushan.thomework101.mvp.model.MainModel;

import javax.inject.Inject;

/**
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class StudentChangeRecordFragmentPresenterImpl implements StudentChangeRecordControl.StudentChangeRecordFragmentPresenter {

    private StudentChangeRecordControl.StudentChangeRecordView mStudentChangeRecordView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public StudentChangeRecordFragmentPresenterImpl(Context context, MainModel model, StudentChangeRecordControl.StudentChangeRecordView studentChangeRecordView) {
        mContext = context;
        mMainModel = model;
        mStudentChangeRecordView = studentChangeRecordView;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mStudentChangeRecordView = null;
    }


}
