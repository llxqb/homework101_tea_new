package com.shushan.thomework101.mvp.ui.activity.logout;


import com.shushan.thomework101.entity.request.LogoutRequest;
import com.shushan.thomework101.entity.request.VerifyCodeRequest;
import com.shushan.thomework101.entity.response.VerifyCodeResponse;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 */

public class LogoutControl {
    public interface LogoutView extends LoadDataView {
        void getVerifyCodeSuccess(VerifyCodeResponse verifyCodeResponse);

        void getLogoutSuccess();

    }

    public interface PresenterLogout extends Presenter<LogoutView> {

        /**
         * 获取验证码
         */
        void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest);

        /**
         * 注销账号
         */
        void onRequestLogout(LogoutRequest LogoutRequest);
    }

}
