package com.shushan.thomework101.mvp.ui.fragment.mine;

import android.content.Context;

import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.HomeRequest;
import com.shushan.thomework101.entity.response.HomeResponse;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.MainModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

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


    /**
     * 请求homeFragment list 数据
     */
    @Override
    public void onRequestHomeInfo(HomeRequest homeRequest) {
        mMineView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMainModel.onRequestHomeInfo(homeRequest).compose(mMineView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::getHomeInfoSuccess, throwable -> mMineView.showErrMessage(throwable),
                        () -> mMineView.dismissLoading());
        mMineView.addSubscription(disposable);
    }


    /**
     * 请求homeFragment list 数据成功
     */
    private void getHomeInfoSuccess(ResponseData responseData) {
        mMineView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            responseData.parseData(HomeResponse.class);
            if (responseData.parsedData != null) {
                HomeResponse response = (HomeResponse) responseData.parsedData;
                mMineView.getHomeInfoSuccess(response);
            }
        } else {
            mMineView.getHomeInfoFail();
            mMineView.showToast(responseData.errorMsg);
        }
    }
    
    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mMineView = null;
    }


}
