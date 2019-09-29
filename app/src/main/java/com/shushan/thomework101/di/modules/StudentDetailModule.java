package com.shushan.thomework101.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.shushan.thomework101.BuildConfig;
import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.model.ModelTransform;
import com.shushan.thomework101.mvp.model.StudentModel;
import com.shushan.thomework101.mvp.ui.activity.student.StudentDetailControl;
import com.shushan.thomework101.mvp.ui.activity.student.StudentDetailPresenterImpl;
import com.shushan.thomework101.network.RetrofitUtil;
import com.shushan.thomework101.network.networkapi.StudentApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class StudentDetailModule {
    private final AppCompatActivity activity;
    private StudentDetailControl.StudentDetailView view;

    public StudentDetailModule(AppCompatActivity activity, StudentDetailControl.StudentDetailView view) {
        this.activity = activity;
        this.view = view;
    }

    public StudentDetailModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    StudentDetailControl.StudentDetailView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    StudentDetailControl.PresenterStudentDetail providePresenterStudentDetail(StudentDetailPresenterImpl studentDetailPresenter) {
        return studentDetailPresenter;
    }

    @Provides
    @PerActivity
    StudentModel provideStudentDetailModel(Gson gson, ModelTransform modelTransform) {
        return new StudentModel(new RetrofitUtil.Builder()
                .context(activity)
                .baseUrl(BuildConfig.WORK_TEA_BASE_URL)
                .isHttps(!BuildConfig.DEBUG)
//                .key(BuildConfig.STORE_NAME,BuildConfig.STORE_PASSWORD)
                .isToJson(false)
                .builder()
                .create(StudentApi.class), gson, modelTransform);
    }


}
