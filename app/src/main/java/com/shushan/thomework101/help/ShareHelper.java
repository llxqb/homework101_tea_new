//package com.shushan.thomework101.help;
//
//import android.app.Activity;
//import android.content.ActivityNotFoundException;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//
//import com.shushan.thomework101.entity.constants.ServerConstant;
//import com.shushan.thomework101.mvp.ui.base.BaseActivity;
//import com.shushan.thomework101.mvp.utils.PicUtils;
//import com.shushan.thomework101.mvp.utils.ToastUtils;
//
//
///**
// * 分享帮助类
// */
//public class ShareHelper {
//    public static final int SHARE_TYPE_SESSION = 0;//分享到聊天界面
//    public static final int SHARE_TYPE_TIME_LINE = 1;//分享到朋友圈
//
//    public static IWXAPI regToWx(Context context) {
//        IWXAPI iwxapi = WXAPIFactory.createWXAPI(context, BuildConfig.WX_APP_ID, true);
//        iwxapi.registerApp(BuildConfig.WX_APP_ID);
//        return iwxapi;
//    }
//
//    public static void gotoWeChatMp(BaseActivity context) {
//        /*
//        JumpToBizProfile.Req req = new JumpToBizProfile.Req();
//        req.toUserName = "gh_6a3c0217b291";//公众号原始ID
//        req.profileType = JumpToBizProfile.JUMP_TO_NORMAL_BIZ_PROFILE;
//        req.extMsg = "";//若为服务号或订阅号则本字段为空，硬件号则填写相关的硬件二维码串
//        return ShuShanApplication.getInstance().getWxApi().sendReq(req);*/
//        try {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
//            intent.addCategory(Intent.CATEGORY_LAUNCHER);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.setComponent(cmp);
//            context.startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            context.showToast("检查到您手机没有安装微信，请安装后使用该功能");
//        }
//    }
//
//    public static boolean sharePic(Context context, IWXAPI iwxapi, int shareType, int resourceId) {
//        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), resourceId);
//        WXImageObject imgObj = new WXImageObject(bmp);
//        WXMediaMessage mediaMsg = new WXMediaMessage();
//        mediaMsg.mediaObject = imgObj;
//        //设置缩略图
//        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 50, 50, true);
//        bmp.recycle();
//        mediaMsg.thumbData = PicUtils.bmpToByteArray(thumbBmp, true);
//
//        //构造一个req
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = "img" + String.valueOf(System.currentTimeMillis());
//        req.message = mediaMsg;
//
//        switch (shareType) {
//            case SHARE_TYPE_SESSION:
//                req.scene = SendMessageToWX.Req.WXSceneSession;
//                break;
//            case SHARE_TYPE_TIME_LINE:
//                req.scene = SendMessageToWX.Req.WXSceneTimeline;
//                break;
//        }
//        return iwxapi.sendReq(req);
//    }
//
//    public static boolean sharePic(Context context, IWXAPI iwxapi, int shareType, Bitmap bmp) {
//        WXImageObject imgObj = new WXImageObject(bmp);
//        WXMediaMessage mediaMsg = new WXMediaMessage();
//        mediaMsg.mediaObject = imgObj;
//        //设置缩略图
//        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 50, 50, true);
//        //bmp.recycle();
//        mediaMsg.thumbData = PicUtils.bmpToByteArray(thumbBmp, true);
//
//        //构造一个req
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = "img" + String.valueOf(System.currentTimeMillis());
//        req.message = mediaMsg;
//
//        switch (shareType) {
//            case SHARE_TYPE_SESSION:
//                req.scene = SendMessageToWX.Req.WXSceneSession;
//                break;
//            case SHARE_TYPE_TIME_LINE:
//                req.scene = SendMessageToWX.Req.WXSceneTimeline;
//                break;
//        }
//        return iwxapi.sendReq(req);
//    }
//
//    /**
//     * 分享到微信
//     */
//    public static void shareWeChat(Context context, IWXAPI iwxapi, int shareType) {
//        if (ShareUtil.customerIsExist(context, "com.tencent.mm")) {
//            //初始化一个WXWebpageObject，填写url
//            WXWebpageObject webpage = new WXWebpageObject();
//            webpage.webpageUrl = "https://www.kencanme.com/";
//
//            //用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
//            WXMediaMessage msg = new WXMediaMessage(webpage);
//            msg.title = "注册作业101领取298元大礼包";
//            msg.description = "拍照检查作业，对错一目了然；“板书+语音”实时解答，辅导认真高效。";
////        Bitmap thumbBmp = BitmapFactory.decodeResource(getResources(), R.drawable.send_music_thumb);
////        msg.thumbData =ImageUtil.bmpToByteArray(thumbBmp, true);
//
//            //构造一个Req
//            SendMessageToWX.Req req = new SendMessageToWX.Req();
//            req.transaction = "webpage" + String.valueOf(System.currentTimeMillis());
//            req.message = msg;
//            switch (shareType) {
//                case SHARE_TYPE_SESSION:
//                    req.scene = SendMessageToWX.Req.WXSceneSession;
//                    break;
//                case SHARE_TYPE_TIME_LINE:
//                    req.scene = SendMessageToWX.Req.WXSceneTimeline;
//                    break;
//            }
////        req.userOpenId = getOpenId();
//
//            //调用api接口，发送数据到微信
//            iwxapi.sendReq(req);
//        } else {
//            ToastUtils.showShort(context, "检查到您手机没有安装微信，请安装后使用该功能");
//        }
//    }
//
//    /**
//     * 分享到QQ 、QQ空间
//     */
//    public static void shareToQQ(int type, String shareUrl, Activity activity) {
//        if (ShareUtil.customerIsExist(activity, "com.tencent.mobileqq")) {
//            Tencent mTencent = Tencent.createInstance(ServerConstant.QQ_APP_ID, activity);
//            Bundle bundle = new Bundle();
//            //这条分享消息被好友点击后的跳转URL。
//            bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, shareUrl);
//            //分享的标题。注：PARAM_TITLE、PARAM_IMAGE_URL、PARAM_	SUMMARY不能全为空，最少必须有一个是有值的。
//            bundle.putString(QQShare.SHARE_TO_QQ_TITLE, "注册作业101领取298元大礼包");
//            //分享的图片URL
////        bundle.putString(Constants.PARAM_IMAGE_URL,
////                "http://img3.cache.netease.com/photo/0005/2013-03-07/8PBKS8G400BV0005.jpg");
//            //分享的消息摘要，最长50个字
//            bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, "拍照检查作业，对错一目了然；“板书+语音”实时解答，辅导认真高效。");
//            //手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
//            bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, "作业101");
//            //标识该消息的来源应用，值为应用名称+AppId。
////        bundle.putString(Constants.PARAM_APP_SOURCE, "星期几" + AppId);
//            if (type == 2) {//分享到空间
//                bundle.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
//            }
//            mTencent.shareToQQ(activity, bundle, null);
//        } else {
//            ToastUtils.showShort(activity, "检查到您手机没有安装QQ，请安装后使用该功能");
//        }
//
//    }
//
//}
