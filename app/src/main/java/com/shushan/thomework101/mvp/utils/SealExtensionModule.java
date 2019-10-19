package com.shushan.thomework101.mvp.utils;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.emoticon.IEmoticonTab;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.plugin.ImagePlugin;
import io.rong.imkit.widget.provider.FilePlugin;
import io.rong.imlib.model.Conversation;

/**
 * ClassName: SealExtensionModule
 * Desciption: //自定义扩展区域
 * author: li.liu
 * date: 2019-10-17
 */
public class SealExtensionModule extends DefaultExtensionModule {

    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
        List<IPluginModule> pluginModuleList = new ArrayList<>();
        IPluginModule image = new ImagePlugin();
//        IPluginModule location = new DefaultLocationPlugin();
//        IPluginModule audio = new AudioPlugin();
//        IPluginModule video = new VideoPlugin();
        IPluginModule file = new FilePlugin();

        if (conversationType.equals(Conversation.ConversationType.GROUP) ||
                conversationType.equals(Conversation.ConversationType.DISCUSSION) ||
                conversationType.equals(Conversation.ConversationType.PRIVATE)) {
            pluginModuleList.add(image);
//            pluginModuleList.add(location);
//            pluginModuleList.add(audio);
//            pluginModuleList.add(video);
            pluginModuleList.add(file);
        } else {
            pluginModuleList.add(image);
        }

        return pluginModuleList;
    }

    @Override
    public List<IEmoticonTab> getEmoticonTabs() {
        return super.getEmoticonTabs();
    }
}

