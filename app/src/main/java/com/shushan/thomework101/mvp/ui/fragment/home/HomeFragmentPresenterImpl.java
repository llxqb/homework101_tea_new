package com.shushan.thomework101.mvp.ui.fragment.home;

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

public class HomeFragmentPresenterImpl implements HomeFragmentControl.homeFragmentPresenter {

    private HomeFragmentControl.HomeView mHomeView;
    private final MainModel mHomeFragmentModel;
    private final Context mContext;

    @Inject
    public HomeFragmentPresenterImpl(Context context, MainModel model, HomeFragmentControl.HomeView homeView) {
        mContext = context;
        mHomeFragmentModel = model;
        mHomeView = homeView;
    }


    /**
     * 请求homeFragment list 数据
     */
    @Override
    public void onRequestHomeInfo(HomeRequest homeRequest) {
        mHomeView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mHomeFragmentModel.onRequestHomeInfo(homeRequest).compose(mHomeView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::getHomeInfoSuccess, throwable -> mHomeView.showErrMessage(throwable),
                        () -> mHomeView.dismissLoading());
        mHomeView.addSubscription(disposable);
    }

    /**
     * 请求homeFragment list 数据成功
     */
    private void getHomeInfoSuccess(ResponseData responseData) {
        mHomeView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            responseData.parseData(HomeResponse.class);
            if (responseData.parsedData != null) {
                HomeResponse response = (HomeResponse) responseData.parsedData;
                mHomeView.getHomeInfoSuccess(response);
            }
        } else {
            mHomeView.showToast(responseData.errorMsg);
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mHomeView = null;
    }

}
