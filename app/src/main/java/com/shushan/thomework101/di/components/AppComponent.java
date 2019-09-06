package com.shushan.thomework101.di.components;

import android.content.Context;

import com.google.gson.Gson;
import com.shushan.thomework101.di.ComponetGraph;
import com.shushan.thomework101.di.modules.AppModule;
import com.shushan.thomework101.entity.user.BuProcessor;
import com.shushan.thomework101.help.ImageLoaderHelper;
import com.shushan.thomework101.help.Sharedprefence;
import com.shushan.thomework101.mvp.model.ModelTransform;
import com.shushan.thomework101.mvp.utils.SharePreferenceUtil;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 全局类实例都统一交给ApplicationComponent管理
 * Created by li.liuon 18/1/11.
 * Singleton  表示单例
 * dependencies   Component中的依赖
 * SubComponent   Component中的包含
 * Scope      在PerActivity中    都有必要用自定义的Scope注解标注这些Component
 * Provides最终解决第三方类库依赖注入问题
 * 第三方类到这里注册
 * AppComponent中方法必须到AppModule中实现
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent extends ComponetGraph {
    Context getContext();

    Gson gson();

    ModelTransform modeTransform();

    ImageLoaderHelper imageLoaderHelper();

    Sharedprefence sharedprefence();

    SharePreferenceUtil sharePreferenceUtil();

    BuProcessor buProcessor();

//    CustomizeMessageItemProvider customizeMessageItemProvider();


//    DaoSession daoSession();
//
//    AMapLocationClient aMapLocationClient();//MainActivity  有用到  @Inject AMapLocationClient mAMapLocationClient;
//
//    AMapLocationClientOption aMapLocationClientOption();
}
