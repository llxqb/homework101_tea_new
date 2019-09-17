package com.shushan.thomework101.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.shushan.thomework101.BuildConfig;
import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.model.ModelTransform;
import com.shushan.thomework101.mvp.model.UserModel;
import com.shushan.thomework101.mvp.ui.activity.teacherCenter.PersonalInfoControl;
import com.shushan.thomework101.mvp.ui.activity.teacherCenter.PersonalInfoPresenterImpl;
import com.shushan.thomework101.network.RetrofitUtil;
import com.shushan.thomework101.network.networkapi.UserApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class PersonalInfoModule {
    private final AppCompatActivity activity;
    private PersonalInfoControl.PersonalInfoView view;

    public PersonalInfoModule(AppCompatActivity activity, PersonalInfoControl.PersonalInfoView view) {
        this.activity = activity;
        this.view = view;
    }

    public PersonalInfoModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    PersonalInfoControl.PersonalInfoView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    PersonalInfoControl.PresenterPersonalInfo providePresenterPersonalInfo(PersonalInfoPresenterImpl PersonalInfoPresenter) {
        return PersonalInfoPresenter;
    }

    @Provides
    @PerActivity
    UserModel provideUserModel(Gson gson, ModelTransform modelTransform) {
        return new UserModel(new RetrofitUtil.Builder()
                .context(activity)
                .baseUrl(BuildConfig.WORK_TEA_BASE_URL)
                .isHttps(!BuildConfig.DEBUG)
//                .key(BuildConfig.STORE_NAME,BuildConfig.STORE_PASSWORD)
                .isToJson(false)
                .builder()
                .create(UserApi.class), gson, modelTransform);
    }
}
