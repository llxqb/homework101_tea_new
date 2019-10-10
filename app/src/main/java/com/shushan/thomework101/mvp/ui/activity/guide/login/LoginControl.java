package com.shushan.thomework101.mvp.ui.activity.guide.login;


import com.shushan.thomework101.entity.request.LoginRequest;
import com.shushan.thomework101.entity.request.VerifyCodeRequest;
import com.shushan.thomework101.entity.response.LoginResponse;
import com.shushan.thomework101.entity.response.VerifyCodeResponse;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 */

public class LoginControl {
    public interface LoginView extends LoadDataView {
        void getVerifyCodeSuccess(VerifyCodeResponse verifyCodeResponse);

        void getLoginSuccess(LoginResponse loginResponse);
    }

    public interface PresenterLogin extends Presenter<LoginView> {

        /**
         * 获取验证码
         */
        void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest);

        /**
         * 登录
         */
        void onRequestLogin(LoginRequest loginRequest);
    }

}
