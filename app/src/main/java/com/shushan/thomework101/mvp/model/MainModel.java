package com.shushan.thomework101.mvp.model;

import com.google.gson.Gson;
import com.shushan.thomework101.entity.request.DeviceInfoRequest;
import com.shushan.thomework101.entity.request.FeedbackRequest;
import com.shushan.thomework101.entity.request.HomeRequest;
import com.shushan.thomework101.entity.request.MineStudentListRequest;
import com.shushan.thomework101.entity.request.SubmitFeedbackRequest;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.request.VersionUpdateRequest;
import com.shushan.thomework101.network.networkapi.MainApi;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by helei on 2017/4/28.
 * LoginModel
 */

public class MainModel {
    private final MainApi mMainApi;
    private final Gson mGson;
    private final ModelTransform mTransform;

    @Inject
    public MainModel(MainApi api, Gson gson, ModelTransform transform) {
        mMainApi = api;
        mGson = gson;
        mTransform = transform;
    }

    /**
     * 检查版本更新
     */
    public Observable<ResponseData> onRequestVersionUpdate(VersionUpdateRequest request) {
        return mMainApi.onRequestVersionUpdate(mGson.toJson(request)).map(mTransform::transformCommon);
    }
    /**
     * 上传设备信息
     */
    public Observable<ResponseData> uploadDeviceInfo(DeviceInfoRequest request) {
        return mMainApi.uploadDeviceInfo(mGson.toJson(request)).map(mTransform::transformCommon);
    }
    /**
     * 请求homeFragment list 数据
     */
    public Observable<ResponseData> onRequestHomeInfo(HomeRequest request) {
        return mMainApi.onRequestHomeInfo(mGson.toJson(request)).map(mTransform::transformCommon);
    }

    /**
     * 请求我的学生 list 数据
     */
    public Observable<ResponseData> onRequestMineStudentInfo(MineStudentListRequest request) {
        return mMainApi.onRequestMineStudentInfo(mGson.toJson(request)).map(mTransform::transformListType);
    }

    /**
     * 提交辅导反馈
     */
    public Observable<ResponseData> submitFeedbackInfo(SubmitFeedbackRequest request) {
        return mMainApi.submitFeedbackInfo(new Gson().toJson(request)).map(mTransform::transformCommon);
    }


    /**
     * 请求辅导反馈
     */
    public Observable<ResponseData> onRequestFeedbackInfo(FeedbackRequest request) {
        return mMainApi.onRequestFeedbackInfo(mGson.toJson(request)).map(mTransform::transformListType);
    }

    /**
     * 查看是否有未读消息
     */
    public Observable<ResponseData> onRequestUnReadInfo(TokenRequest request) {
        return mMainApi.onRequestUnReadInfo(mGson.toJson(request)).map(mTransform::transformCommon);
    }

}
