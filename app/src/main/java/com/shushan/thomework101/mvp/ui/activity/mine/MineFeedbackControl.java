package com.shushan.thomework101.mvp.ui.activity.mine;


import com.shushan.thomework101.entity.request.FeedbackRequest;
import com.shushan.thomework101.entity.response.FeedBackResponse;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 */

public class MineFeedbackControl {
    public interface MineFeedbackView extends LoadDataView {
        void getFeedbackInfoSuccess(FeedBackResponse response);
    }

    public interface PresenterMineFeedback extends Presenter<MineFeedbackView> {
        /**
         * 请求辅导反馈数据
         */
        void onRequestFeedbackInfo(FeedbackRequest feedbackRequest);
    }

}
