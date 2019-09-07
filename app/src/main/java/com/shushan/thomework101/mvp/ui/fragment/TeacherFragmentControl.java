package com.shushan.thomework101.mvp.ui.fragment;


import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class TeacherFragmentControl {
    public interface TeacherView extends LoadDataView {
//        void getInfoSuccess(TeacherFragmentResponse response);

    }

    public interface TeacherFragmentPresenter extends Presenter<TeacherView> {
        /**
         * 请求homeFragment list 数据
         */
//        void onRequestInfo(HomeFragmentRequest homeFragmentRequest);

    }


}
