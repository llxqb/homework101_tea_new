package com.shushan.thomework101.mvp.ui.activity.main;

import android.content.Context;

import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.DeviceInfoRequest;
import com.shushan.thomework101.entity.request.VersionUpdateRequest;
import com.shushan.thomework101.entity.response.VersionUpdateResponse;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.MainModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2017/4/27.
 * PresenterLoginImpl
 */

public class MainPresenterImpl implements MainControl.PresenterMain {

    private MainControl.MainView mMainView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public MainPresenterImpl(Context context, MainModel model, MainControl.MainView mainView) {
        mContext = context;
        mMainModel = model;
        mMainView = mainView;
    }

    /**
     * 检查版本更新
     */
    @Override
    public void onRequestVersionUpdate(VersionUpdateRequest versionUpdateRequest) {
        mMainView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMainModel.onRequestVersionUpdate(versionUpdateRequest).compose(mMainView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestVersionUpdateSuccess, throwable -> mMainView.showErrMessage(throwable),
                        () -> mMainView.dismissLoading());
        mMainView.addSubscription(disposable);
    }

    /**
     * 检查版本更新 成功
     */
    private void requestVersionUpdateSuccess(ResponseData responseData) {
        mMainView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            responseData.parseData(VersionUpdateResponse.class);
            if (responseData.parsedData != null) {
                VersionUpdateResponse response = (VersionUpdateResponse) responseData.parsedData;
                mMainView.getVersionUpdateSuccess(response);
            }
        } else {
//            mMainView.showToast(responseData.errorMsg);
        }
    }

    /**
     * 上传设备信息
     */
    @Override
    public void uploadDeviceInfo(DeviceInfoRequest deviceInfoRequest) {
        mMainView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMainModel.uploadDeviceInfo(deviceInfoRequest).compose(mMainView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::uploadDeviceInfoSuccess, throwable -> mMainView.showErrMessage(throwable),
                        () -> mMainView.dismissLoading());
        mMainView.addSubscription(disposable);
    }

    /**
     * 上传设备信息 成功
     */
    private void uploadDeviceInfoSuccess(ResponseData responseData) {
        mMainView.judgeToken(responseData.resultCode);
        if (responseData.resultCode != 0) {
            mMainView.showToast(responseData.errorMsg);
        }
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
