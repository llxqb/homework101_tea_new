package com.shushan.thomework101.mvp.ui.activity.personalInfo;

import android.content.Context;

import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.request.UploadPersonalInfoRequest;
import com.shushan.thomework101.entity.response.TopicResponse;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.MineModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.MultipartBody;


/**
 * Created by li.liu on 2019/09/17.
 */

public class UploadVideoPresenterImpl implements UploadVideoControl.PresenterUploadVideo {

    private UploadVideoControl.UploadVideoView mUploadVideoView;
    private final MineModel mMineModel;
    private final Context mContext;

    @Inject
    public UploadVideoPresenterImpl(Context context, MineModel model, UploadVideoControl.UploadVideoView UploadVideoView) {
        mContext = context;
        mMineModel = model;
        mUploadVideoView = UploadVideoView;
    }

    /**
     * 获取试讲题目
     */
    @Override
    public void onRequestTopicInfo(TokenRequest tokenRequest) {
        mUploadVideoView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.onRequestTopicInfo(tokenRequest).compose(mUploadVideoView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::getTopicInfoSuccess, throwable -> mUploadVideoView.showErrMessage(throwable),
                        () -> mUploadVideoView.dismissLoading());
        mUploadVideoView.addSubscription(disposable);
    }

    /**
     * 获取试讲题目成功
     */
    private void getTopicInfoSuccess(ResponseData responseData) {
        mUploadVideoView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            responseData.parseData(TopicResponse.class);
            if (responseData.parsedData != null) {
                TopicResponse response = (TopicResponse) responseData.parsedData;
                mUploadVideoView.getTopicInfoSuccess(response);
            }
        } else {
            mUploadVideoView.showToast(responseData.errorMsg);
        }
    }

    /**
     * 上传视频文件
     */
    @Override
    public void uploadVideoRequest(MultipartBody.Part uploadVideo) {
        mUploadVideoView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.uploadVideoRequest(uploadVideo).compose(mUploadVideoView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestUploadVideoSuccess, throwable -> mUploadVideoView.showErrMessage(throwable),
                        () -> mUploadVideoView.dismissLoading());
        mUploadVideoView.addSubscription(disposable);
    }


    private void requestUploadVideoSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            mUploadVideoView.getUploadVideoSuccess(responseData.result);
        } else {
            mUploadVideoView.showToast(responseData.errorMsg);
        }
    }

    /**
     * 上传试讲视频
     */
    @Override
    public void uploadPersonalVideoInfo(UploadPersonalInfoRequest uploadPersonalInfoRequest) {
        mUploadVideoView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.uploadPersonalVideoInfo(uploadPersonalInfoRequest).compose(mUploadVideoView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::uploadPersonalVideoInfoSuccess, throwable -> mUploadVideoView.showErrMessage(throwable),
                        () -> mUploadVideoView.dismissLoading());
        mUploadVideoView.addSubscription(disposable);
    }

    /**
     * 上传试讲视频成功
     */
    private void uploadPersonalVideoInfoSuccess(ResponseData responseData) {
        mUploadVideoView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            mUploadVideoView.getUploadPersonalVideoInfoSuccess();
        } else {
            mUploadVideoView.showToast(responseData.errorMsg);
        }
    }
  

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
