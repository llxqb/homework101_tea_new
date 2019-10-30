package com.shushan.thomework101.help;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.Gson;
import com.shushan.thomework101.entity.constants.ActivityConstant;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;

/**
 * 友盟消息点击处理helper
 */
public class UmengMessageHandlerHelper extends UmengMessageHandler {
    private String TAG = "UmengMessageHandlerHelper";
    @Override
    public void dealWithCustomMessage(Context context, UMessage uMessage) {
        super.dealWithCustomMessage(context, uMessage);
        Log.d(TAG, "dealWithCustomMessage : extra = " + uMessage.extra);

    }

    @Override
    public void dealWithNotificationMessage(Context context, UMessage uMessage) {
        super.dealWithNotificationMessage(context, uMessage);
        Log.d(TAG, "dealWithNotificationMessage : extra = " + uMessage.extra);

    }

    @Override
    public Notification getNotification(Context context, UMessage msg) {
        Log.d(TAG, "dealWithNotificationMessage : msg = " + new Gson().toJson(msg));
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ActivityConstant.UM_PUSH_MESSAGE));
        return super.getNotification(context, msg);
    }
}

