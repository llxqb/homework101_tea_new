package com.shushan.thomework101.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.shushan.thomework101.BuildConfig;
import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.model.MineModel;
import com.shushan.thomework101.mvp.model.ModelTransform;
import com.shushan.thomework101.mvp.ui.activity.personalInfo.UploadVideoControl;
import com.shushan.thomework101.mvp.ui.activity.personalInfo.UploadVideoPresenterImpl;
import com.shushan.thomework101.network.RetrofitUtil;
import com.shushan.thomework101.network.networkapi.MineApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class UploadVideoModule {
    private final AppCompatActivity activity;
    private UploadVideoControl.UploadVideoView view;

    public UploadVideoModule(AppCompatActivity activity, UploadVideoControl.UploadVideoView view) {
        this.activity = activity;
        this.view = view;
    }

    public UploadVideoModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    UploadVideoControl.UploadVideoView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    UploadVideoControl.PresenterUploadVideo providePresenterUploadVideo(UploadVideoPresenterImpl uploadVideoPresenter) {
        return uploadVideoPresenter;
    }

    @Provides
    @PerActivity
    MineModel provideMineModel(Gson gson, ModelTransform modelTransform) {
        return new MineModel(new RetrofitUtil.Builder()
                .context(activity)
                .baseUrl(BuildConfig.WORK_TEA_BASE_URL)
                .isHttps(!BuildConfig.DEBUG)
//                .key(BuildConfig.STORE_NAME,BuildConfig.STORE_PASSWORD)
                .isToJson(false)
                .builder()
                .create(MineApi.class), gson, modelTransform);
    }
}
