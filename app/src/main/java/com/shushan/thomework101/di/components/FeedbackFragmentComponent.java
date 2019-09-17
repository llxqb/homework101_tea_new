package com.shushan.thomework101.di.components;


import android.support.v7.app.AppCompatActivity;

import com.shushan.thomework101.di.modules.FeedbackFragmentModule;
import com.shushan.thomework101.di.modules.MainModule;
import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.ui.fragment.student.FeedbackFragment;

import dagger.Component;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {MainModule.class, FeedbackFragmentModule.class})
public interface FeedbackFragmentComponent {
    AppCompatActivity activity();

    void inject(FeedbackFragment fragment);//我的老师

}
