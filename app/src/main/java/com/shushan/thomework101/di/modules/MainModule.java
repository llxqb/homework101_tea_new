package com.shushan.thomework101.di.modules;


import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.shushan.thomework101.BuildConfig;
import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.model.MainModel;
import com.shushan.thomework101.mvp.model.ModelTransform;
import com.shushan.thomework101.mvp.ui.activity.main.MainControl;
import com.shushan.thomework101.mvp.ui.activity.main.MainPresenterImpl;
import com.shushan.thomework101.mvp.ui.fragment.home.HomeFragmentControl;
import com.shushan.thomework101.mvp.ui.fragment.home.HomeFragmentPresenterImpl;
import com.shushan.thomework101.mvp.ui.fragment.mine.MineFragmentControl;
import com.shushan.thomework101.mvp.ui.fragment.mine.MineFragmentPresenterImpl;
import com.shushan.thomework101.mvp.ui.fragment.student.StudentFragmentControl;
import com.shushan.thomework101.mvp.ui.fragment.student.StudentFragmentPresenterImpl;
import com.shushan.thomework101.network.RetrofitUtil;
import com.shushan.thomework101.network.networkapi.MainApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class MainModule {
    private final AppCompatActivity activity;
    private MainControl.MainView view;

    public MainModule(AppCompatActivity activity, MainControl.MainView view) {
        this.activity = activity;
        this.view = view;
    }

    public MainModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    MainControl.MainView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    MainControl.PresenterMain providePresenterMain(MainPresenterImpl presenterMain) {
        return presenterMain;
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


    //add
    @Provides
    @PerActivity
   HomeFragmentControl.homeFragmentPresenter providePresenterHomeFragment(HomeFragmentPresenterImpl homeFragmentPresenter) {
        return homeFragmentPresenter;
    }

    @Provides
    @PerActivity
    StudentFragmentControl.StudentFragmentPresenter providePresenterStudentFragment(StudentFragmentPresenterImpl studentFragmentPresenter) {
        return studentFragmentPresenter;
    }

    @Provides
    @PerActivity
    MineFragmentControl.MineFragmentPresenter providePresenterMineFragment(MineFragmentPresenterImpl mineFragmentPresenter) {
        return mineFragmentPresenter;
    }


}
