package com.shushan.thomework101;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.shushan.thomework101.di.components.AppComponent;
import com.shushan.thomework101.di.components.DaggerAppComponent;
import com.shushan.thomework101.di.modules.AppModule;

import javax.inject.Inject;

import io.rong.imkit.RongIM;

import static com.shushan.thomework101.mvp.utils.SystemUtils.getCurProcessName;

/**
 * 作业101 老师端 application
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
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mAppComponent.inject(this);//必须有
        //初始化内存泄漏检查工具
        //初始化融云
        initRongYun();
        //友盟init
    }

    /**
     * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
     * io.rong.push 为融云 push 进程名称，不可修改。
     */
    public void initRongYun() {
        if (getApplicationInfo().packageName
                .equals(getCurProcessName(getApplicationContext()))
                || "io.rong.push"
                .equals(getCurProcessName(getApplicationContext()))) {
            RongIM.init(this);
//            //注册自定义消息
//            RongIM.registerMessageType(CustomizeMessage.class);
//            //注册消息模板
//            mCustomizeMessageItemProvider = new CustomizeMessageItemProvider();
//            RongIM.registerMessageTemplate(mCustomizeMessageItemProvider);
        }
    }

}
