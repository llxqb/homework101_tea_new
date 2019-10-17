package com.shushan.thomework101.mvp.utils;

import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.InformationNotificationMessage;

/**
 *
 */
public class ConversationUtil {


    /*
     * 发送小灰条通知消息
     * "我是消息内容"
     * start_coach
     */
    public static void SendInformationNotificationMessage(String mTargetId, Conversation.ConversationType mConversationType, String text, String textType) {
        InformationNotificationMessage informationNotificationMessage = InformationNotificationMessage.obtain(text);
        informationNotificationMessage.setExtra(textType);
        informationNotificationMessage.setMessage(text);
        Message myMessage = Message.obtain(mTargetId, mConversationType, informationNotificationMessage);
        RongIM.getInstance().sendMessage(myMessage, null, null, new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {
                //消息本地数据库存储成功的回调
            }

            @Override
            public void onSuccess(Message message) {
                //消息通过网络发送成功的回调
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                //消息发送失败的回调
            }
        });
    }
}
