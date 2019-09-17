package com.shushan.thomework101.mvp.ui.fragment.student;

import android.content.Context;

import com.shushan.thomework101.mvp.model.MainModel;

import javax.inject.Inject;

/**
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class FeedbackFragmentPresenterImpl implements FeedbackFragmentControl.FeedbackFragmentPresenter {

    private FeedbackFragmentControl.FeedbackFragmentView mFeedbackFragmentView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public FeedbackFragmentPresenterImpl(Context context, MainModel model, FeedbackFragmentControl.FeedbackFragmentView feedbackFragmentView) {
        mContext = context;
        mMainModel = model;
        mFeedbackFragmentView = feedbackFragmentView;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mFeedbackFragmentView = null;
    }


}
