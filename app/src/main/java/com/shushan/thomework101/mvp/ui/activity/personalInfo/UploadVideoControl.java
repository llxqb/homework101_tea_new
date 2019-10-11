package com.shushan.thomework101.mvp.ui.activity.personalInfo;


import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.request.UploadPersonalInfoRequest;
import com.shushan.thomework101.entity.response.TopicResponse;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

import okhttp3.MultipartBody;

/**
 * Created by li.liu on 2019/09/17.
 */

public class UploadVideoControl {
    public interface UploadVideoView extends LoadDataView {

        void getTopicInfoSuccess(TopicResponse topicResponse);

        void getUploadVideoSuccess(String videoUrl);

        void getUploadPersonalVideoInfoSuccess();
    }

    public interface PresenterUploadVideo extends Presenter<UploadVideoView> {
        /**
         * 获取试讲题目
         */
        void onRequestTopicInfo(TokenRequest tokenRequest);

        /**
         * 上传视频文件
         */
        void uploadVideoRequest(MultipartBody.Part uploadVideo);

        /**
         * 上传试讲视频
         */
        void uploadPersonalVideoInfo(UploadPersonalInfoRequest uploadPersonalInfoRequest);


    }

}
