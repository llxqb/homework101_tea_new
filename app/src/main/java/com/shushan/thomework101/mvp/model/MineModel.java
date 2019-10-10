package com.shushan.thomework101.mvp.model;


import com.google.gson.Gson;
import com.shushan.thomework101.entity.request.UploadPersonalInfoRequest;
import com.shushan.thomework101.network.networkapi.MineApi;

import javax.inject.Inject;

import io.reactivex.Observable;

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
     * 更新用户个人信息
     */
    public Observable<ResponseData> onRequestUploadPersonalInfo(UploadPersonalInfoRequest request) {
        return mMineApi.onRequestUploadPersonalInfo(new Gson().toJson(request)).map(mTransform::transformCommon);
    }

}
