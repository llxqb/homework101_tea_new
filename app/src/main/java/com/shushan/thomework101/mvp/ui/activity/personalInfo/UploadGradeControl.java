package com.shushan.thomework101.mvp.ui.activity.personalInfo;


import com.shushan.thomework101.entity.request.UploadPersonalInfoRequest;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 */

public class UploadGradeControl {
    public interface UpdateGradeView extends LoadDataView {
        void getUploadPersonalGradeInfoSuccess();
    }

    public interface PresenterUpdateGrade extends Presenter<UpdateGradeView> {

        /**
         * 设置老师辅导年级和科目
         */
        void uploadPersonalGradeInfo(UploadPersonalInfoRequest uploadPersonalInfoRequest);
    }

}
