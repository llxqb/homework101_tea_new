package com.shushan.thomework101.di.components;


import android.support.v7.app.AppCompatActivity;

import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.PersonalInfoModule;
import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.ui.activity.mine.teacherCenter.CoachingTimeActivity;
import com.shushan.thomework101.mvp.ui.activity.mine.teacherCenter.EditPersonalInfoActivity;
import com.shushan.thomework101.mvp.ui.activity.mine.teacherCenter.UploadCardActivity;
import com.shushan.thomework101.mvp.ui.activity.mine.teacherCenter.UploadVideoActivity;

import dagger.Component;

/**
 * LoginComponent继承了ActivityComponent，假如ActivityComponent中定义了创建类实例方法，则MainComponent中必须提供@Inject或@Provides对应的
 * 初始化类实例的方法
 * Created by li.liu on 18/1/19.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {PersonalInfoModule.class, ActivityModule.class})
public interface PersonalInfoComponent extends ActivityComponent {
    void inject(UploadCardActivity activity);//上传身份证

    void inject(UploadVideoActivity activity);//上传视频

    void inject(EditPersonalInfoActivity activity);//编辑个人信息

    void inject(CoachingTimeActivity activity);//设置辅导时间

    AppCompatActivity activity();

}
