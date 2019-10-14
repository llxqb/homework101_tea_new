package com.shushan.thomework101.mvp.ui.activity.mine;


import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 */

public class StudentReplaceDetailControl {
    public interface StudentReplaceDetailView extends LoadDataView {

    }

    public interface PresenterStudentReplaceDetail extends Presenter<StudentReplaceDetailView> {


    }

}
