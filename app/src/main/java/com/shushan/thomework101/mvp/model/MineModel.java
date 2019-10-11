package com.shushan.thomework101.mvp.model;


import com.google.gson.Gson;
import com.shushan.thomework101.entity.request.SetCounsellingTimeRequest;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.request.UploadImage;
import com.shushan.thomework101.entity.request.UploadPersonalInfoRequest;
import com.shushan.thomework101.network.networkapi.MineApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * Created by li.liu on 2019/09/17.
 * 我的相关
 */

public class MineModel {
    private final MineApi mMineApi;
    private final Gson mGson;
    private final ModelTransform mTransform;

    @Inject
    public MineModel(MineApi api, Gson gson, ModelTransform transform) {
        mMineApi = api;
        mGson = gson;
        mTransform = transform;
    }

    /**
     * 上传图片
     */
    public Observable<ResponseData> uploadImageRequest(UploadImage reUploadImage) {
        return mMineApi.uploadImageRequest(new Gson().toJson(reUploadImage)).map(mTransform::transformCommon);
    }
    /**
     * 上传视频
     */
    public Observable<ResponseData> uploadVideoRequest(MultipartBody.Part uploadVideo) {
        return mMineApi.uploadVideoRequest(uploadVideo).map(mTransform::transformCommon);
    }

    /**
     * 设置老师辅导年级和科目
     */
    public Observable<ResponseData> uploadPersonalGradeInfo(UploadPersonalInfoRequest request) {
        return mMineApi.uploadPersonalGradeInfo(new Gson().toJson(request)).map(mTransform::transformCommon);
    }
    /**
     * 上传老师证书
     */
    public Observable<ResponseData> uploadPersonalCardInfo(UploadPersonalInfoRequest request) {
        return mMineApi.uploadPersonalCardInfo(new Gson().toJson(request)).map(mTransform::transformCommon);
    }
    /**
     * 上传试讲视频
     */
    public Observable<ResponseData> uploadPersonalVideoInfo(UploadPersonalInfoRequest request) {
        return mMineApi.uploadPersonalVideoInfo(new Gson().toJson(request)).map(mTransform::transformCommon);
    }
    /**
     * 获取试讲题目
     */
    public Observable<ResponseData> onRequestTopicInfo(TokenRequest request) {
        return mMineApi.onRequestTopicInfo(new Gson().toJson(request)).map(mTransform::transformCommon);
    }
    /**
     * 设置辅导时间
     */
    public Observable<ResponseData> setCounsellingTime(SetCounsellingTimeRequest request) {
        return mMineApi.setCounsellingTime(new Gson().toJson(request)).map(mTransform::transformCommon);
    }
    /**
     * 完善个人资料
     */
    public Observable<ResponseData> uploadPersonalInfo(UploadPersonalInfoRequest request) {
        return mMineApi.uploadPersonalInfo(new Gson().toJson(request)).map(mTransform::transformCommon);
    }
    /**
     * 修改个人资料
     */
    public Observable<ResponseData> updatePersonalInfo(UploadPersonalInfoRequest request) {
        return mMineApi.updatePersonalInfo(new Gson().toJson(request)).map(mTransform::transformCommon);
    }

}
