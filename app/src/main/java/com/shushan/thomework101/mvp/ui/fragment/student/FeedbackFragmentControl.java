package com.shushan.thomework101.mvp.ui.fragment.student;


import com.shushan.thomework101.entity.request.FeedbackRequest;
import com.shushan.thomework101.entity.response.FeedBackResponse;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class FeedbackFragmentControl {
    /**
     * 我的辅导记录、老师页面辅导反馈
     */
    public interface FeedbackFragmentView extends LoadDataView {
        void getFeedbackInfoSuccess(FeedBackResponse response);

    }

    public interface FeedbackFragmentPresenter extends Presenter<FeedbackFragmentView> {
        /**
         * 请求辅导反馈数据
         */
        void onRequestFeedbackInfo(FeedbackRequest feedbackRequest);

    }


}
