package com.shushan.thomework101.mvp.model;


import com.google.gson.Gson;
import com.shushan.thomework101.entity.request.LoginRequest;
import com.shushan.thomework101.entity.request.LogoutRequest;
import com.shushan.thomework101.entity.request.VerifyCodeRequest;
import com.shushan.thomework101.network.networkapi.GuideApi;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by li.liu on 2019/09/18.
 * 引导模块
 */

public class GuideModel {
    private final GuideApi mGuideApi;
    private final Gson mGson;
    private final ModelTransform mTransform;

    @Inject
    public GuideModel(GuideApi api, Gson gson, ModelTransform transform) {
        mGuideApi = api;
        mGson = gson;
        mTransform = transform;
    }

    /**
     * 获取验证码
     */
    public Observable<ResponseData> onRequestVerifyCode(VerifyCodeRequest request) {
        return mGuideApi.onRequestVerifyCode(new Gson().toJson(request)).map(mTransform::transformCommon);
    }


    /**
     * 登录
     */
    public Observable<ResponseData> onRequestLogin(LoginRequest request) {
        return mGuideApi.onRequestLogin(new Gson().toJson(request)).map(mTransform::transformCommon);
    }
    /**
     * 注销账号
     */
    public Observable<ResponseData> onRequestLogout(LogoutRequest request) {
        return mGuideApi.onRequestLogout(new Gson().toJson(request)).map(mTransform::transformCommon);
    }
}
