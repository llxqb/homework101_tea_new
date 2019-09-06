package com.shushan.thomework101;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.shushan.thomework101.di.components.AppComponent;

import javax.inject.Inject;

/**
 * 作业101 学生端 application
 */
public class HomeworkApplication extends Application {

    private AppComponent mAppComponent;
    public Context mContext;
    @Inject
    Gson mGson;

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
//        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
//        mAppComponent.inject(this);//必须有
        //初始化内存泄漏检查工具
        //初始化融云
        //友盟init
    }


}
