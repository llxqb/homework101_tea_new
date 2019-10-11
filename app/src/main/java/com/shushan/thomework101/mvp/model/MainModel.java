package com.shushan.thomework101.mvp.model;

import com.google.gson.Gson;
import com.shushan.thomework101.entity.request.HomeRequest;
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
     * 请求homeFragment list 数据
     */
    public Observable<ResponseData> onRequestHomeInfo(HomeRequest request) {
        return mMainApi.onRequestHomeInfo(mGson.toJson(request)).map(mTransform::transformCommon);
    }


}
