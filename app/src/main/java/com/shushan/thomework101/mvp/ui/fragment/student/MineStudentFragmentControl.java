package com.shushan.thomework101.mvp.ui.fragment.student;


import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class MineStudentFragmentControl {
    /**
     * 我的辅导记录、老师页面辅导反馈
     */
    public interface MineStudentFragmentView extends LoadDataView {
//        void getInfoSuccess(MineStudentFeedbackFragmentResponse response);

    }

    public interface MineStudentFragmentPresenter extends Presenter<MineStudentFragmentView> {
        /**
         * 请求homeFragment list 数据
         */
//        void onRequestInfo(HomeFragmentRequest homeFragmentRequest);

    }


}
