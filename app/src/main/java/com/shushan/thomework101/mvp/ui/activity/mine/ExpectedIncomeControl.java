package com.shushan.thomework101.mvp.ui.activity.mine;


import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 */

public class ExpectedIncomeControl {
    public interface ExpectedIncomeView extends LoadDataView {
//        void getExpectedIncomeSuccess(String token);
//        void getVerifyCodeSuccess(String code);
    }

    public interface PresenterExpectedIncome extends Presenter<ExpectedIncomeView> {

//        /**
//         * 登录
//         */
//        void onRequestExpectedIncome(RegisterRequest ExpectedIncomeRequest);
//        /**
//         * 获取验证码
//         * 验证类型(注册：100001，重置密码：100002,登录：100003)
//         */
//        void onRequestVerifyCode(VerifyCodeRequest verifyCodeRequest);
    }

}
