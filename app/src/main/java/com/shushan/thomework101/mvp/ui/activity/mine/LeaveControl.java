package com.shushan.thomework101.mvp.ui.activity.mine;


import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 */

public class LeaveControl {
    public interface LeaveView extends LoadDataView {
//        void getLeaveSuccess(String token);
//        void getVerifyCodeSuccess(String code);
    }

    public interface PresenterLeave extends Presenter<LeaveView> {

//        /**
//         * 登录
//         */
//        void onRequestLeave(RegisterRequest LeaveRequest);
//        /**
//         * 获取验证码
//         * 验证类型(注册：100001，重置密码：100002,登录：100003)
//         */
//        void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest);
    }

}
