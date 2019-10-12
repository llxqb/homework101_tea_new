package com.shushan.thomework101.mvp.ui.fragment.student;

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
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class FeedbackFragmentPresenterImpl implements FeedbackFragmentControl.FeedbackFragmentPresenter {

    private FeedbackFragmentControl.FeedbackFragmentView mFeedbackFragmentView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public FeedbackFragmentPresenterImpl(Context context, MainModel model, FeedbackFragmentControl.FeedbackFragmentView feedbackFragmentView) {
        mContext = context;
        mMainModel = model;
        mFeedbackFragmentView = feedbackFragmentView;
    }


    /**
     * 请求辅导反馈数据
     */
    @Override
    public void onRequestFeedbackInfo(FeedbackRequest feedbackRequest) {
        mFeedbackFragmentView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMainModel.onRequestFeedbackInfo(feedbackRequest).compose(mFeedbackFragmentView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestFeedbackInfoSuccess, throwable -> mFeedbackFragmentView.showErrMessage(throwable),
                        () -> mFeedbackFragmentView.dismissLoading());
        mFeedbackFragmentView.addSubscription(disposable);
    }

    /**
     * 请求辅导反馈数据成功
     */
    private void requestFeedbackInfoSuccess(ResponseData responseData) {
        mFeedbackFragmentView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            FeedBackResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), FeedBackResponse.class);
            mFeedbackFragmentView.getFeedbackInfoSuccess(response);
        } else {
            mFeedbackFragmentView.showToast(responseData.errorMsg);
        }
    }
    
    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mFeedbackFragmentView = null;
    }


   
}
