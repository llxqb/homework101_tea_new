package com.shushan.thomework101.mvp.ui.fragment.studentChange;


import com.shushan.thomework101.entity.request.StudentChangeRequest;
import com.shushan.thomework101.entity.response.StudentChangeRecordResponse;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class StudentChangeRecordControl {
    public interface StudentChangeRecordView extends LoadDataView {
        void getStudentChangeSuccess(StudentChangeRecordResponse studentChangeRecordResponse);
    }

    public interface StudentChangeRecordFragmentPresenter extends Presenter<StudentChangeRecordView> {
        /**
         * 学生变动
         */
        void onRequestStudentChange(StudentChangeRequest StudentReplaceDetailRequest);
    }


}
