package com.shushan.thomework101;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shushan.thomework101.di.components.AppComponent;
import com.shushan.thomework101.di.components.DaggerAppComponent;
import com.shushan.thomework101.di.modules.AppModule;
import com.shushan.thomework101.entity.constants.ServerConstant;
import com.shushan.thomework101.mvp.ui.activity.rongCloud.MyReceiveMessageListener;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import javax.inject.Inject;

import io.rong.imkit.RongIM;

import static com.shushan.thomework101.mvp.utils.SystemUtils.getCurProcessName;

/**
 * 作业101 老师端 application
 */
public class HomeworkApplication extends Application {
    private String TAG = "HomeworkApplication";
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
        initUeng();
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
            //融云接收消息的监听
            RongIM.setOnReceiveMessageListener(new MyReceiveMessageListener(getApplicationContext()));
//            //注册自定义消息
//            RongIM.registerMessageType(CustomizeMessage.class);
//            //注册消息模板
//            mCustomizeMessageItemProvider = new CustomizeMessageItemProvider();
//            RongIM.registerMessageTemplate(mCustomizeMessageItemProvider);
        }
    }


    /**
     * 初始化友盟
     */
    private void initUeng() {
//        UMConfigure.setLogEnabled(true);
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, ServerConstant.UMENG_SECRET);
        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(this);

        new Thread(new Runnable() {
            @Override
            public void run() {

                //注册推送服务，每次调用register方法都会回调该接口
                mPushAgent.register(new IUmengRegisterCallback() {
                    @Override
                    public void onSuccess(String deviceToken) {
                        //服务后台位置：应用管理 -> 应用信息 -> Appkey
                        Log.e(TAG, "注册成功：deviceToken：-------->  " + deviceToken);
                    }

                    @Override
                    public void onFailure(String s, String s1) {
                        Log.e(TAG, "注册失败：-------->  " + "s:" + s + ",s1:" + s1);
                    }
                });
            }
        }).start();

    }

}
