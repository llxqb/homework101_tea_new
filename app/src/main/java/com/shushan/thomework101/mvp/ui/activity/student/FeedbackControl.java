package com.shushan.thomework101.mvp.ui.activity.student;


import com.shushan.thomework101.entity.request.FeedbackRequest;
import com.shushan.thomework101.entity.request.SubmitFeedbackRequest;
import com.shushan.thomework101.entity.response.FeedBackResponse;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 */

public class FeedbackControl {
    public interface FeedbackView extends LoadDataView {
        void submitFeedbackInfoSuccess();
        void getFeedbackInfoSuccess(FeedBackResponse response);
    }

    public interface PresenterFeedback extends Presenter<FeedbackView> {

        /**
         * 辅导反馈提交
         */
        void submitFeedbackInfo(SubmitFeedbackRequest submitFeedbackRequest);
        /**
         * 请求辅导反馈数据
         */
        void onRequestFeedbackInfo(FeedbackRequest feedbackRequest);
    }

}
