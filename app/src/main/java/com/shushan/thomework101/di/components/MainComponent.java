package com.shushan.thomework101.di.components;


import android.support.v7.app.AppCompatActivity;


import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.MainModule;
import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.ui.activity.main.MainActivity;
import com.shushan.thomework101.mvp.ui.activity.main.MainControl;

import dagger.Component;

/**
 * LoginComponent继承了ActivityComponent，假如ActivityComponent中定义了创建类实例方法，则MainComponent中必须提供@Inject或@Provides对应的
 * 初始化类实例的方法
 * Created by li.liu on 18/1/19.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {MainModule.class, ActivityModule.class})
public interface MainComponent extends ActivityComponent {
    //对LoginActivity进行依赖注入
    void inject(MainActivity mainActivity);

    AppCompatActivity activity();

    MainControl.MainView view();

}
