package com.shushan.thomework101.mvp.ui.activity.mine;

import android.content.Context;

import com.google.gson.Gson;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.FeedbackRequest;
import com.shushan.thomework101.entity.response.FeedBackResponse;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.MainModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2019/09/17.
 */

public class MineFeedbackPresenterImpl implements MineFeedbackControl.PresenterMineFeedback {

    private MineFeedbackControl.MineFeedbackView mMineFeedbackView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public MineFeedbackPresenterImpl(Context context, MainModel model, MineFeedbackControl.MineFeedbackView MineFeedbackView) {
        mContext = context;
        mMainModel = model;
        mMineFeedbackView = MineFeedbackView;
    }

    /**
     * 请求辅导反馈数据
     */
    @Override
    public void onRequestFeedbackInfo(FeedbackRequest feedbackRequest) {
        mMineFeedbackView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMainModel.onRequestFeedbackInfo(feedbackRequest).compose(mMineFeedbackView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestFeedbackInfoSuccess, throwable -> mMineFeedbackView.showErrMessage(throwable),
                        () -> mMineFeedbackView.dismissLoading());
        mMineFeedbackView.addSubscription(disposable);
    }

    /**
     * 请求辅导反馈数据成功
     */
    private void requestFeedbackInfoSuccess(ResponseData responseData) {
        mMineFeedbackView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            FeedBackResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), FeedBackResponse.class);
            mMineFeedbackView.getFeedbackInfoSuccess(response);
        } else {
            mMineFeedbackView.showToast(responseData.errorMsg);
        }
    }
    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
