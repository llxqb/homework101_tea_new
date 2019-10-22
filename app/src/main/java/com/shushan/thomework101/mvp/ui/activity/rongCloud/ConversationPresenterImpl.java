package com.shushan.thomework101.mvp.ui.activity.rongCloud;

import android.content.Context;

import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.FeedbackIdRequest;
import com.shushan.thomework101.entity.request.UserInfoByRidRequest;
import com.shushan.thomework101.entity.response.FeedbackIdResponse;
import com.shushan.thomework101.entity.response.UserInfoByRidResponse;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.ResponseData;
import com.shushan.thomework101.mvp.model.StudentModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2017/4/27.
 * PresenterConversationImpl
 */

public class ConversationPresenterImpl implements ConversationControl.PresenterConversation {
    private ConversationControl.ConversationView mConversationView;
    private final StudentModel mStudentModel;
    private final Context mContext;

    @Inject
    public ConversationPresenterImpl(Context context, StudentModel model, ConversationControl.ConversationView ConversationView) {
        mContext = context;
        mStudentModel = model;
        mConversationView = ConversationView;
    }

    /**
     * 根据融云第三方id获取用户头像和昵称
     */
    @Override
    public void onRequestUserInfoByRid(UserInfoByRidRequest userInfoByRidRequest) {
        mConversationView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mStudentModel.onRequestUserInfoByRid(userInfoByRidRequest).compose(mConversationView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestUserInfoByRidSuccess, throwable -> mConversationView.showErrMessage(throwable),
                        () -> mConversationView.dismissLoading());
        mConversationView.addSubscription(disposable);
    }


    private void requestUserInfoByRidSuccess(ResponseData responseData) {
        mConversationView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            responseData.parseData(UserInfoByRidResponse.class);
            if (responseData.parsedData != null) {
                UserInfoByRidResponse response = (UserInfoByRidResponse) responseData.parsedData;
                mConversationView.getUserInfoByRidSuccess(response);
            }
        } else {
            mConversationView.showToast(responseData.errorMsg);
        }
    }
    /**
     * 学生结束辅导，老师去反馈，查询反馈id
     */
    @Override
    public void onRequestFeedBackId(FeedbackIdRequest feedbackIdRequest) {
        mConversationView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mStudentModel.onRequestFeedBackId(feedbackIdRequest).compose(mConversationView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestFeedbackIdSuccess, throwable -> mConversationView.showErrMessage(throwable),
                        () -> mConversationView.dismissLoading());
        mConversationView.addSubscription(disposable);
    }


    private void requestFeedbackIdSuccess(ResponseData responseData) {
        mConversationView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            responseData.parseData(FeedbackIdResponse.class);
            if (responseData.parsedData != null) {
                FeedbackIdResponse response = (FeedbackIdResponse) responseData.parsedData;
                mConversationView.getFeedBackIdSuccess(response);
            }
        } else {
            mConversationView.showToast(responseData.errorMsg);
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
