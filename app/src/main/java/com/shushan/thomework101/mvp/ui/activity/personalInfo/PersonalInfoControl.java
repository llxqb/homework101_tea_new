package com.shushan.thomework101.mvp.ui.activity.personalInfo;


import com.shushan.thomework101.entity.request.UploadPersonalInfoRequest;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 */

public class PersonalInfoControl {
    public interface PersonalInfoView extends LoadDataView {
        void getUploadPersonalInfoSuccess();
    }

    public interface PresenterPersonalInfo extends Presenter<PersonalInfoView> {
        /**
         * 更新用户个人信息
         */
        void onRequestUploadPersonalInfo(UploadPersonalInfoRequest uploadPersonalInfoRequest);
    }

}
