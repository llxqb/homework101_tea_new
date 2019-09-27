package com.shushan.thomework101.mvp.ui.activity.rongCloud;


import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class ConversationControl {
    public interface ConversationView extends LoadDataView {
//        void getConversationSuccess(String token);
//        void getVerifyCodeSuccess(String code);
    }

    public interface PresenterConversation extends Presenter<ConversationView> {

//        /**
//         * 登录
//         */
//        void onRequestConversation(RegisterRequest ConversationRequest);
//        /**
//         * 获取验证码
//         * 验证类型(注册：100001，重置密码：100002,登录：100003)
//         */
//        void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest);
    }

}
