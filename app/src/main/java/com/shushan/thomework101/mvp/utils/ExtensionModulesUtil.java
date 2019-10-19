package com.shushan.thomework101.mvp.utils;

import android.content.Context;

import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;

/**
 * 融云扩展模块
 */
public class ExtensionModulesUtil {
    private static ExtensionModulesUtil instance;
    public static ExtensionModulesUtil getInstance() {
        if (instance == null) {
            synchronized (ExtensionModulesUtil.class) {
                if (instance == null) {
                    instance = new ExtensionModulesUtil();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        /**
         * 因为 SealExtensionModule 继承与融云默认 DefaultExtensionModule，
         * 需要先移除掉默认的扩展后再进行注册
         * 继承并覆盖默认的扩展模块可在自己需要的时机控制各默认模块的展示与隐藏
         */
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
            }
        }

        RongExtensionManager.getInstance().registerExtensionModule(new SealExtensionModule());

//        // 个人名片
//        RongExtensionManager.getInstance().registerExtensionModule(createContactCardExtensionModule());
//        // 语音输入
//        RongExtensionManager.getInstance().registerExtensionModule(new RecognizeExtensionModule());
//        // 小视频
//        RongExtensionManager.getInstance().registerExtensionModule(new SightExtensionModule());
//        // 戳一下
//        RongExtensionManager.getInstance().registerExtensionModule(new PokeExtensionModule());
    }

}
