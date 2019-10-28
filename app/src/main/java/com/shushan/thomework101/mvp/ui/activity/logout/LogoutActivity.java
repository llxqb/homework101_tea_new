package com.shushan.thomework101.mvp.ui.activity.logout;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerLogoutComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.LogoutModule;
import com.shushan.thomework101.entity.constants.Constant;
import com.shushan.thomework101.entity.request.LogoutRequest;
import com.shushan.thomework101.entity.request.VerifyCodeRequest;
import com.shushan.thomework101.entity.response.VerifyCodeResponse;
import com.shushan.thomework101.help.DialogFactory;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.ui.dialog.CommonDialog;
import com.shushan.thomework101.mvp.utils.ValueUtil;
import com.shushan.thomework101.mvp.views.TimeButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注销账号
 */
public class LogoutActivity extends BaseActivity implements LogoutControl.LogoutView, CommonDialog.CommonDialogListener {

    @BindView(R.id.phone_et)
    EditText mPhoneEt;
    @BindView(R.id.verification_code_et)
    EditText mVerificationCodeEt;
    @BindView(R.id.code_bt)
    TimeButton mCodeBt;
    @Inject
    LogoutControl.PresenterLogout mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_logout);
        initInjectData();
    }

    @Override
    public void initView() {
        mPhoneEt.addTextChangedListener(search_text_OnChange);
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.common_left_iv, R.id.code_bt, R.id.sure_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.code_bt:
                String mPhoneEtValue = mPhoneEt.getText().toString();
                if (TextUtils.isEmpty(mPhoneEtValue) || !ValueUtil.isMobilePhone(mPhoneEtValue)) {
                    showToast("请先输入正确的手机号");
                    return;
                }
                mCodeBt.setRun(true);
                //倒计时结束后显示背景
                mCodeBt.setBeforBg(R.drawable.bg_red_round_solid_5);
                mCodeBt.setBeforTextBg(getResources().getColor(R.color.white));
                //倒计时时候背景
                mCodeBt.setAfterBg(R.drawable.bg_line_round_solid_5);
                mCodeBt.setTextColor(getResources().getColor(R.color.color999));
                onRequestVerifyCode();
                break;
            case R.id.sure_tv:
                if (verificationText()) {
                    DialogFactory.showCommonDialog(this, "注销账号", "注销账号，将清除您在平台的一切信息。确定注销吗？", "取消", "确定注销", Constant.COMMON_DIALOG_STYLE_1);
                }
                break;
        }
    }


    /**
     * 获取验证码
     */
    private void onRequestVerifyCode() {
        VerifyCodeRequest verifyCodeRequest = new VerifyCodeRequest();
        verifyCodeRequest.mobile = mPhoneEt.getText().toString();
        mPresenter.onRequestVerifyCode(verifyCodeRequest);
    }

    @Override
    public void getVerifyCodeSuccess(VerifyCodeResponse verifyCodeResponse) {
    }

    @Override
    public void commonDialogBtnOkListener() {
        onRequestLogout();
    }

    /**
     * 注销账号
     */
    private void onRequestLogout() {
        String mPhoneEtValue = mPhoneEt.getText().toString();
        String mVerificationCodeEtValue = mVerificationCodeEt.getText().toString();
        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.token = mBuProcessor.getToken();
        logoutRequest.mobile = mPhoneEtValue;
        logoutRequest.code = mVerificationCodeEtValue;
        mPresenter.onRequestLogout(logoutRequest);
    }


    @Override
    public void getLogoutSuccess() {
        startActivitys(LogoutSuccessActivity.class);
    }


    /**
     * 验证输入文字
     */
    private boolean verificationText() {
        String mPhoneEtValue = mPhoneEt.getText().toString();
        String mVerificationCodeEtValue = mVerificationCodeEt.getText().toString();
        if (TextUtils.isEmpty(mPhoneEtValue)) {
            showToast("手机号码不能为空");
            return false;
        }
        if (TextUtils.isEmpty(mVerificationCodeEtValue)) {
            showToast("验证码不能为空");
            return false;
        }
        if (mVerificationCodeEtValue.length() != 6) {
            showToast("验证码输入有误");
            return false;
        }
        return true;
    }


    public TextWatcher search_text_OnChange = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (ValueUtil.isChinaPhoneLegal(s.toString())) {
                mCodeBt.setBackgroundResource(R.drawable.bg_red_round_solid_5);
                mCodeBt.setTextColor(getResources().getColor(R.color.white));
            } else {
                mCodeBt.setBackgroundResource(R.drawable.bg_line_round_solid_5);
                mCodeBt.setTextColor(getResources().getColor(R.color.color999));
            }
        }
    };


    private void initInjectData() {
        DaggerLogoutComponent.builder().appComponent(getAppComponent())
                .logoutModule(new LogoutModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }

}
