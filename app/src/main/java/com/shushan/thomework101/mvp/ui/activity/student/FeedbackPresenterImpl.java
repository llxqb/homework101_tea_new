package com.shushan.thomework101.mvp.ui.activity.student;

import android.content.Context;

import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.SubmitFeedbackRequest;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.ResponseData;
import com.shushan.thomework101.mvp.model.StudentModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2019/09/17.
 */

public class FeedbackPresenterImpl implements FeedbackControl.PresenterFeedback {

    private FeedbackControl.FeedbackView mFeedbackView;
    private final StudentModel mStudentModel;
    private final Context mContext;

    @Inject
    public FeedbackPresenterImpl(Context context, StudentModel model, FeedbackControl.FeedbackView FeedbackView) {
        mContext = context;
        mStudentModel = model;
        mFeedbackView = FeedbackView;
    }


    /**
     * 提交辅导反馈
     */
    @Override
    public void submitFeedbackInfo(SubmitFeedbackRequest submitFeedbackRequest) {
        mFeedbackView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mStudentModel.submitFeedbackInfo(submitFeedbackRequest).compose(mFeedbackView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
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


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
