package com.shushan.thomework101.mvp.ui.fragment.student;

import android.content.Context;

import com.shushan.thomework101.mvp.model.MainModel;

import javax.inject.Inject;

/**
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class CoachingFragmentPresenterImpl implements CoachingFragmentControl.CoachingFragmentPresenter {

    private CoachingFragmentControl.CoachingFragmentView mCoachingFragmentView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public CoachingFragmentPresenterImpl(Context context, MainModel model, CoachingFragmentControl.CoachingFragmentView CoachingFragmentView) {
        mContext = context;
        mMainModel = model;
        mCoachingFragmentView = CoachingFragmentView;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mCoachingFragmentView = null;
    }


}
