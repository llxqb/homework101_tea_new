package com.shushan.thomework101.mvp.ui.activity.mine;

import android.content.Context;

import com.shushan.thomework101.mvp.model.MineModel;

import javax.inject.Inject;


/**
 * Created by li.liu on 2019/09/17.
 */

public class StudentReplaceDetailPresenterImpl implements StudentReplaceDetailControl.PresenterStudentReplaceDetail {

    private StudentReplaceDetailControl.StudentReplaceDetailView mStudentReplaceDetailView;
    private final MineModel mMineModel;
    private final Context mContext;

    @Inject
    public StudentReplaceDetailPresenterImpl(Context context, MineModel model, StudentReplaceDetailControl.StudentReplaceDetailView StudentReplaceDetailView) {
        mContext = context;
        mMineModel = model;
        mStudentReplaceDetailView = StudentReplaceDetailView;
    }



    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
