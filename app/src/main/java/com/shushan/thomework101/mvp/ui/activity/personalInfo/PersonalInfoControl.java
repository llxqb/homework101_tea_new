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

        void getUploadPersonalCardInfoSuccess();

        void getUploadPersonalInfoSuccess();

        void getUpdatePersonalInfoSuccess();
    }

    public interface PresenterPersonalInfo extends Presenter<PersonalInfoView> {
        /**
         * 上传图片
         */
        void uploadImageRequest(UploadImage uploadImage);

        /**
         * 上传老师证书
         */
        void uploadPersonalCardInfo(UploadPersonalInfoRequest uploadPersonalInfoRequest);
        /**
         * 完善个人资料（注册流程）
         */
        void uploadPersonalInfo(UploadPersonalInfoRequest uploadPersonalInfoRequest);
        /**
         * 修改个人资料
         */
        void updatePersonalInfo(UploadPersonalInfoRequest uploadPersonalInfoRequest);
    }

}
