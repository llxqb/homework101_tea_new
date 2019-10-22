package com.shushan.thomework101.mvp.ui.activity.student;

import android.content.Context;

import com.google.gson.Gson;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.FeedbackRequest;
import com.shushan.thomework101.entity.request.SubmitFeedbackRequest;
import com.shushan.thomework101.entity.response.FeedBackResponse;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.MainModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2019/09/17.
 */

public class FeedbackPresenterImpl implements FeedbackControl.PresenterFeedback {

    private FeedbackControl.FeedbackView mFeedbackView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public FeedbackPresenterImpl(Context context, MainModel model, FeedbackControl.FeedbackView FeedbackView) {
        mContext = context;
        mMainModel = model;
        mFeedbackView = FeedbackView;
    }

    /**
     * 提交辅导反馈
     */
    @Override
    public void submitFeedbackInfo(SubmitFeedbackRequest submitFeedbackRequest) {
        mFeedbackView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMainModel.submitFeedbackInfo(submitFeedbackRequest).compose(mFeedbackView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::submitFeedbackInfoSuccess, throwable -> mFeedbackView.showErrMessage(throwable),
                        () -> mFeedbackView.dismissLoading());
        mFeedbackView.addSubscription(disposable);
    }


    private void submitFeedbackInfoSuccess(ResponseData responseData) {
        mFeedbackView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            mFeedbackView.submitFeedbackInfoSuccess();
        } else {
            mFeedbackView.showToast(responseData.errorMsg);
        }
    }

    /**
     * 请求辅导反馈数据
     */
    @Override
    public void onRequestFeedbackInfo(FeedbackRequest feedbackRequest) {
        mFeedbackView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMainModel.onRequestFeedbackInfo(feedbackRequest).compose(mFeedbackView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestFeedbackInfoSuccess, throwable -> mFeedbackView.showErrMessage(throwable),
                        () -> mFeedbackView.dismissLoading());
        mFeedbackView.addSubscription(disposable);
    }

    /**
     * 请求辅导反馈数据成功
     */
    private void requestFeedbackInfoSuccess(ResponseData responseData) {
        mFeedbackView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            FeedBackResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), FeedBackResponse.class);
            mFeedbackView.getFeedbackInfoSuccess(response);
        } else {
            mFeedbackView.showToast(responseData.errorMsg);
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
