package com.shushan.thomework101.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.shushan.thomework101.BuildConfig;
import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.model.GuideModel;
import com.shushan.thomework101.mvp.model.ModelTransform;
import com.shushan.thomework101.mvp.ui.activity.logout.LogoutControl;
import com.shushan.thomework101.mvp.ui.activity.logout.LogoutPresenterImpl;
import com.shushan.thomework101.network.RetrofitUtil;
import com.shushan.thomework101.network.networkapi.GuideApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class LogoutModule {
    private final AppCompatActivity activity;
    private LogoutControl.LogoutView view;

    public LogoutModule(AppCompatActivity activity, LogoutControl.LogoutView view) {
        this.activity = activity;
        this.view = view;
    }

    public LogoutModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    LogoutControl.LogoutView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    LogoutControl.PresenterLogout providePresenterLogout(LogoutPresenterImpl logoutPresenter) {
        return logoutPresenter;
    }

    @Provides
    @PerActivity
    GuideModel provideGuideModel(Gson gson, ModelTransform modelTransform) {
        return new GuideModel(new RetrofitUtil.Builder()
                .context(activity)
                .baseUrl(BuildConfig.WORK_TEA_BASE_URL)
                .isHttps(!BuildConfig.DEBUG)
//                .key(BuildConfig.STORE_NAME,BuildConfig.STORE_PASSWORD)
                .isToJson(false)
                .builder()
                .create(GuideApi.class), gson, modelTransform);
    }




}
