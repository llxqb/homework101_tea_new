package com.shushan.thomework101.mvp.ui.fragment;

import android.content.Context;


import com.shushan.thomework101.mvp.model.MainModel;

import javax.inject.Inject;

/**
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class MimeFragmentPresenterImpl implements MimeFragmentControl.MimeFragmentPresenter {

    private MimeFragmentControl.MimeView mMimeView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public MimeFragmentPresenterImpl(Context context, MainModel model, MimeFragmentControl.MimeView MimeView) {
        mContext = context;
        mMainModel = model;
        mMimeView = MimeView;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mMimeView = null;
    }


}
