package com.shushan.thomework101.mvp.ui.activity.student;


import com.shushan.thomework101.entity.request.SaveStudentInfoRequest;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 */

public class StudentDetailControl {
    public interface StudentDetailView extends LoadDataView {
        void saveStudentInfoSuccess();
    }

    public interface PresenterStudentDetail extends Presenter<StudentDetailView> {

        /**
         * 保存学生信息
         */
        void saveStudentInfo(SaveStudentInfoRequest saveStudentInfoRequest);
    }

}
