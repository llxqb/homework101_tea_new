package com.shushan.thomework101.mvp.ui.fragment.mine;

import android.content.Context;

import com.shushan.thomework101.mvp.model.MainModel;

import javax.inject.Inject;

/**
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class MineFragmentPresenterImpl implements MineFragmentControl.MineFragmentPresenter {

    private MineFragmentControl.MineView mMineView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public MineFragmentPresenterImpl(Context context, MainModel model, MineFragmentControl.MineView MineView) {
        mContext = context;
        mMainModel = model;
        mMineView = MineView;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mMineView = null;
    }


}
