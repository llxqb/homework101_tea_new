package com.shushan.thomework101.mvp.ui.activity.mine;

import android.content.Context;

import com.shushan.thomework101.R;
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

public class SettingPresenterImpl implements SettingControl.PresenterSetting {

    private SettingControl.SettingView mSettingView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public SettingPresenterImpl(Context context, MainModel model, SettingControl.SettingView SettingView) {
        mContext = context;
        mMainModel = model;
        mSettingView = SettingView;
    }

    /**
     * 检查版本更新
     */
    @Override
    public void onRequestVersionUpdate(VersionUpdateRequest versionUpdateRequest) {
        mSettingView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMainModel.onRequestVersionUpdate(versionUpdateRequest).compose(mSettingView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestVersionUpdateSuccess, throwable -> mSettingView.showErrMessage(throwable),
                        () -> mSettingView.dismissLoading());
        mSettingView.addSubscription(disposable);
    }

    /**
     * 检查版本更新 成功
     */
    private void requestVersionUpdateSuccess(ResponseData responseData) {
        mSettingView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            responseData.parseData(VersionUpdateResponse.class);
            if (responseData.parsedData != null) {
                VersionUpdateResponse response = (VersionUpdateResponse) responseData.parsedData;
                mSettingView.getVersionUpdateSuccess(response);
            }
        } else {
            mSettingView.showToast("已经是最新版本");
        }
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
