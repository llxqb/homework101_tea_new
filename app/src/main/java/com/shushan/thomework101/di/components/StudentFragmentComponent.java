package com.shushan.thomework101.di.components;


import android.support.v7.app.AppCompatActivity;

import com.shushan.thomework101.di.modules.MainModule;
import com.shushan.thomework101.di.modules.StudentFragmentModule;
import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.ui.fragment.student.StudentFragment;

import dagger.Component;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {MainModule.class, StudentFragmentModule.class})
public interface StudentFragmentComponent {
    AppCompatActivity activity();

    void inject(StudentFragment fragment);
}
