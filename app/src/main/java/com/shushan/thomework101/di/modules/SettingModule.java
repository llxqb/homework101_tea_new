package com.shushan.thomework101.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.shushan.thomework101.BuildConfig;
import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.model.MainModel;
import com.shushan.thomework101.mvp.model.ModelTransform;
import com.shushan.thomework101.mvp.ui.activity.mine.SettingControl;
import com.shushan.thomework101.mvp.ui.activity.mine.SettingPresenterImpl;
import com.shushan.thomework101.network.RetrofitUtil;
import com.shushan.thomework101.network.networkapi.MainApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class SettingModule {
    private final AppCompatActivity activity;
    private SettingControl.SettingView view;

    public SettingModule(AppCompatActivity activity, SettingControl.SettingView view) {
        this.activity = activity;
        this.view = view;
    }

    public SettingModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    SettingControl.SettingView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    SettingControl.PresenterSetting providePresenterSetting(SettingPresenterImpl settingPresenter) {
        return settingPresenter;
    }

    @Provides
    @PerActivity
    MainModel provideMainModel(Gson gson, ModelTransform modelTransform) {
        return new MainModel(new RetrofitUtil.Builder()
                .context(activity)
                .baseUrl(BuildConfig.WORK_TEA_BASE_URL)
                .isHttps(!BuildConfig.DEBUG)
//                .key(BuildConfig.STORE_NAME,BuildConfig.STORE_PASSWORD)
                .isToJson(false)
                .builder()
                .create(MainApi.class), gson, modelTransform);
    }


}
