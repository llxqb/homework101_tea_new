package com.shushan.thomework101.mvp.ui.activity.main;

import android.content.Context;

import com.google.gson.Gson;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.SystemMsgRequest;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.response.SystemMsgResponse;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.MineModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2019/09/17.
 */

public class SystemMsgPresenterImpl implements SystemMsgControl.PresenterSystemMsg {

    private SystemMsgControl.SystemMsgView mSystemMsgView;
    private final MineModel mMineModel;
    private final Context mContext;

    @Inject
    public SystemMsgPresenterImpl(Context context, MineModel model, SystemMsgControl.SystemMsgView SystemMsgView) {
        mContext = context;
        mMineModel = model;
        mSystemMsgView = SystemMsgView;
    }


    /**
     * 请求系统消息
     */
    @Override
    public void onRequestSystemMsg(SystemMsgRequest systemMsgRequest) {
        mSystemMsgView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.onRequestSystemMsg(systemMsgRequest).compose(mSystemMsgView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestCoachingPriceListSuccess, throwable -> mSystemMsgView.showErrMessage(throwable),
                        () -> mSystemMsgView.dismissLoading());
        mSystemMsgView.addSubscription(disposable);
    }


    private void requestCoachingPriceListSuccess(ResponseData responseData) {
        mSystemMsgView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            SystemMsgResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), SystemMsgResponse.class);
            mSystemMsgView.getSystemMsgSuccess(response);
        } else {
            mSystemMsgView.showToast(responseData.errorMsg);
        }
    }
    /**
     * 清空消息列表
     */
    @Override
    public void onRequestDeleteMsg(TokenRequest tokenRequest) {
        mSystemMsgView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.onRequestDeleteMsg(tokenRequest).compose(mSystemMsgView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestDeleteMsgListSuccess, throwable -> mSystemMsgView.showErrMessage(throwable),
                        () -> mSystemMsgView.dismissLoading());
        mSystemMsgView.addSubscription(disposable);
    }


    private void requestDeleteMsgListSuccess(ResponseData responseData) {
        mSystemMsgView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            mSystemMsgView.getDeleteMsgSuccess();
        } else {
            mSystemMsgView.showToast(responseData.errorMsg);
        }
    }
    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
