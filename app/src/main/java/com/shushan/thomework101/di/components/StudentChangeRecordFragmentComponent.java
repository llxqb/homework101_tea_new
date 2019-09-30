package com.shushan.thomework101.di.components;


import android.support.v7.app.AppCompatActivity;

import com.shushan.thomework101.di.modules.StudentChangeRecordFragmentModule;
import com.shushan.thomework101.di.modules.StudentReplaceDetailModule;
import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.ui.fragment.studentChange.StudentChangeAllRecordFragment;
import com.shushan.thomework101.mvp.ui.fragment.studentChange.StudentChangeInRecordFragment;
import com.shushan.thomework101.mvp.ui.fragment.studentChange.StudentChangeOutRecordFragment;

import dagger.Component;

/**
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {StudentReplaceDetailModule.class, StudentChangeRecordFragmentModule.class})
public interface StudentChangeRecordFragmentComponent {
    AppCompatActivity activity();

    void inject(StudentChangeAllRecordFragment fragment);
    void inject(StudentChangeInRecordFragment fragment);
    void inject(StudentChangeOutRecordFragment fragment);
}
