package com.shushan.thomework101.mvp.utils;

import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.shushan.thomework101.mvp.presenter.LoadDataView;

import java.util.Objects;

/**
 * HomeFragment util
 */
public class HomeUtil {
    /**
     * 跳转到微信
     */
    public static void toWechat(Context context, LoadDataView loadDataView, String wxNum) {
        try {
            ClipboardManager cm = (ClipboardManager) Objects.requireNonNull(context).getSystemService(Context.CLIPBOARD_SERVICE);
            // 将文本内容放到系统剪贴板里。
            cm.setText(wxNum);
            loadDataView.showToast("账号已复制");
            //跳转到微信
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            loadDataView.showToast("检查到您手机没有安装微信，请安装后使用该功能");
        }
    }

    /**
     * 判断审核状态
     *
     * @param cardState     身份证审核  0未上传  1待审核   2审核通过   3审核不通过
     * @param videoState    试讲视频    0未完成  1待审核   2审核通过   3审核不通过
     * @param trainState    岗前培训    1待完成  2已完成
     * @param guideState    设置辅导时间    0未设置  1审核通过  2待审核    3审核不通过
     * @param completeState 完善个人资料    0未完成  1已经完成  2待审核    3审核不通过
     * @return 1：未完成  上传身份证和上传试讲视频  2：岗前培训  3：完善资料和设置辅导时间
     */
    public static int checkState(int cardState, int videoState, int trainState, int guideState, int completeState) {
        int checkState = 0;
        if (cardState != 2 || videoState != 2) {
            return 1;
        } else if (trainState != 2) {
            return 2;
        } else if (guideState != 1 || completeState != 1) {
            return 3;
        }
        return checkState;
    }

//    /**
//     * 返回按钮显示状态值
//     *
//     * @param cardState  身份证审核  0未上传  1待审核   2审核通过   3审核不通过
//     * @param videoState 试讲视频    0未完成  1待审核   2审核通过   3审核不通过
//     * @param trainState 岗前培训    1待完成  2已完成
//     * @param guideState 完善资料    0未设置  1审核通过  2待审核    3审核不通过
//     * @return stateNum：0 不通过 1 通过
//     */
//    public static int stateNum(int cardState, int videoState, int trainState, int guideState) {
//        int stateNum;
//        if (cardState == 0) {
//            stateNum = "去完成";
//        } else if (cardState == 1) {
//            stateNum = "待审核";
//        } else if (cardState == 2) {
//            stateNum = "已完成";
//        } else if (cardState == 3) {
//            stateNum = "去完成";
//        }
//
//        if (videoState == 0) {
//            stateNum = "去完成";
//        } else if (videoState == 1) {
//            stateNum = "待审核";
//        } else if (videoState == 2) {
//            stateNum = "已完成";
//        } else if (videoState == 3) {
//            stateNum = "去完成";
//        }
//
//        if (trainState == 1) {
//            stateNum = "去完成";
//        } else if (videoState == 2) {
//            stateNum = "已完成";
//        }
//
//        if (guideState == 0) {
//            stateNum = "去完成";
//        } else if (videoState == 1) {
//            stateNum = "已完成";
//        } else if (videoState == 2) {
//            stateNum = "待审核";
//        } else if (videoState == 3) {
//            stateNum = "去完成";
//        }
//        return stateNum;
//    }

    /**
     * 返回按钮显示状态名称
     *
     * @param cardState     身份证审核  0未上传  1待审核   2审核通过   3审核不通过
     * @param videoState    试讲视频    0未完成  1待审核   2审核通过   3审核不通过
     * @param trainState    岗前培训    1待完成  2已完成
     * @param guideState    设置辅导时间    0未设置  1审核通过  2待审核    3审核不通过
     * @param completeState 完善个人资料    0未完成  1审核通过  2待审核    3审核不通过
     * @return stateName：状态名称
     */
    public static String stateName(int cardState, int videoState, int trainState, int guideState, int completeState) {
        LogUtils.e("cardState:"+cardState+" videoState:"+videoState+" trainState:"+trainState+" guideState:"+guideState+" completeState:"+completeState);
        String stateName = "";
        if (cardState == 0) {
            stateName = "去完成";
        } else if (cardState == 1) {
            stateName = "待审核";
        } else if (cardState == 2) {
            stateName = "已完成";
        } else if (cardState == 3) {
            stateName = "去完成";
        }

        if (videoState == 0) {
            stateName = "去完成";
        } else if (videoState == 1) {
            stateName = "待审核";
        } else if (videoState == 2) {
            stateName = "已完成";
        } else if (videoState == 3) {
            stateName = "去完成";
        }

        if (trainState == 1) {
            stateName = "去完成";
        } else if (trainState == 2) {
            stateName = "已完成";
        }

        if (guideState == 0) {
            stateName = "去完成";
        } else if (guideState == 1) {
            stateName = "已完成";
        } else if (guideState == 2) {
            stateName = "待审核";
        } else if (guideState == 3) {
            stateName = "去完成";
        }

        if (completeState == 0) {
            stateName = "去完成";
        } else if (completeState == 1) {
            stateName = "已完成";
        } else if (completeState == 2) {
            stateName = "待审核";
        } else if (completeState == 3) {
            stateName = "去完成";
        }
        LogUtils.e("stateName:"+stateName);
        return stateName;
    }
}
