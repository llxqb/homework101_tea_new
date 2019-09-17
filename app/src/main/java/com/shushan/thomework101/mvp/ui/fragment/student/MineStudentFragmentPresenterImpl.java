package com.shushan.thomework101.mvp.ui.fragment.student;

import android.content.Context;

import com.shushan.thomework101.mvp.model.MainModel;

import javax.inject.Inject;

/**
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class MineStudentFragmentPresenterImpl implements MineStudentFragmentControl.MineStudentFragmentPresenter {

    private MineStudentFragmentControl.MineStudentFragmentView mMineStudentFragmentView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public MineStudentFragmentPresenterImpl(Context context, MainModel model, MineStudentFragmentControl.MineStudentFragmentView MineStudentFragmentView) {
        mContext = context;
        mMainModel = model;
        mMineStudentFragmentView = MineStudentFragmentView;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mMineStudentFragmentView = null;
    }


}
