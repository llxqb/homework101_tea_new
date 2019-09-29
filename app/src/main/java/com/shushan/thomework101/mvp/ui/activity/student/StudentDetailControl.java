package com.shushan.thomework101.mvp.ui.activity.student;


import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 */

public class StudentDetailControl {
    public interface StudentDetailView extends LoadDataView {
//        void getStudentDetailSuccess(String token);
//        void getVerifyCodeSuccess(String code);
    }

    public interface PresenterStudentDetail extends Presenter<StudentDetailView> {

//        /**
//         * 登录
//         */
//        void onRequestStudentDetail(RegisterRequest StudentDetailRequest);
//        /**
//         * 获取验证码
//         * 验证类型(注册：100001，重置密码：100002,登录：100003)
//         */
//        void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest);
    }

}
