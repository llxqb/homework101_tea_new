package com.shushan.thomework101.mvp.ui.fragment.student;


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
//        void getInfoSuccess(FeedbackFeedbackFragmentResponse response);

    }

    public interface FeedbackFragmentPresenter extends Presenter<FeedbackFragmentView> {
        /**
         * 请求homeFragment list 数据
         */
//        void onRequestInfo(HomeFragmentRequest homeFragmentRequest);

    }


}
