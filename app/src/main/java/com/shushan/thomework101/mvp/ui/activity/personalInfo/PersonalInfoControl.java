package com.shushan.thomework101.mvp.ui.activity.personalInfo;


import com.shushan.thomework101.entity.request.UploadImage;
import com.shushan.thomework101.entity.request.UploadPersonalInfoRequest;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 */

public class PersonalInfoControl {
    public interface PersonalInfoView extends LoadDataView {
        void getUploadImageSuccess(String picUrl);

        void getUploadPersonalGradeInfoSuccess();

        void getUploadPersonalCardInfoSuccess();

    }

    public interface PresenterPersonalInfo extends Presenter<PersonalInfoView> {
        /**
         * 上传图片
         */
        void uploadImageRequest(UploadImage uploadImage);

        /**
         * 设置老师辅导年级和科目
         */
        void uploadPersonalGradeInfo(UploadPersonalInfoRequest uploadPersonalInfoRequest);

        /**
         * 上传老师证书
         */
        void uploadPersonalCardInfo(UploadPersonalInfoRequest uploadPersonalInfoRequest);
    }

}
