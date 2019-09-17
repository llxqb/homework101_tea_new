package com.shushan.thomework101.mvp.ui.fragment.student;


import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class StudentFragmentControl {
    public interface StudentView extends LoadDataView {
//        void getInfoSuccess(TeacherFragmentResponse response);

    }

    public interface StudentFragmentPresenter extends Presenter<StudentView> {
        /**
         * 请求homeFragment list 数据
         */
//        void onRequestInfo(HomeFragmentRequest homeFragmentRequest);

    }


}
