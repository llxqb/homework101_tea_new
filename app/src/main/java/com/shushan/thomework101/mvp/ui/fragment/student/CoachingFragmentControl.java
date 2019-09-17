package com.shushan.thomework101.mvp.ui.fragment.student;


import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class CoachingFragmentControl {
    /**
     * 我的辅导记录、老师页面辅导反馈
     */
    public interface CoachingFragmentView extends LoadDataView {
//        void getInfoSuccess(CoachingFeedbackFragmentResponse response);

    }

    public interface CoachingFragmentPresenter extends Presenter<CoachingFragmentView> {
        /**
         * 请求homeFragment list 数据
         */
//        void onRequestInfo(HomeFragmentRequest homeFragmentRequest);

    }


}
