package com.shushan.thomework101.mvp.ui.activity.rongCloud;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.shushan.thomework101.entity.constants.ActivityConstant;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.InformationNotificationMessage;

/**
 * 融云接收消息监听
 */
public class MyReceiveMessageListener implements RongIMClient.OnReceiveMessageListener {
    Context mContext;

    public MyReceiveMessageListener(Context context) {
        mContext = context;
    }

    /**
     * 收到消息的处理。
     *
     * @param message 收到的消息实体。
     * @param left    剩余未拉取消息数目。
     * @return 收到消息是否处理完成，true 表示自己处理铃声和后台通知，false 走融云默认处理方式。
     */
    @Override
    public boolean onReceived(Message message, int left) {
        //开发者根据自己需求自行处理
        MessageContent messageContent = message.getContent();
        if (messageContent instanceof InformationNotificationMessage) {
            String extra = ((InformationNotificationMessage) messageContent).getExtra();
//            LogUtils.e("content:" + ((InformationNotificationMessage) messageContent).getMessage());
//            LogUtils.e("Extra:" + extra);
            //发送广播
            Intent intent = new Intent();
            intent.setAction(ActivityConstant.SHOW_NOTIFICATION_MESSAGE);
            intent.putExtra("extra", extra);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        }
        //有融云未读消息，学生显示小红点
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(ActivityConstant.RC_UNREAD_MSG));
        return false;
    }
}