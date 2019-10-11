package com.shushan.thomework101.mvp.ui.activity.guide.login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerLoginComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.LoginModule;
import com.shushan.thomework101.entity.request.LoginRequest;
import com.shushan.thomework101.entity.request.VerifyCodeRequest;
import com.shushan.thomework101.entity.response.LoginResponse;
import com.shushan.thomework101.entity.response.VerifyCodeResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.activity.guide.SubjectSelectActivity;
import com.shushan.thomework101.mvp.ui.activity.main.MainActivity;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.utils.LogUtils;
import com.shushan.thomework101.mvp.utils.LoginUtils;
import com.shushan.thomework101.mvp.utils.SystemUtils;
import com.shushan.thomework101.mvp.utils.ValueUtil;
import com.shushan.thomework101.mvp.views.TimeButton;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity implements LoginControl.LoginView {
    @BindView(R.id.phone_et)
    EditText mPhoneEt;
    @BindView(R.id.verification_code_et)
    EditText mVerificationCodeEt;
    @BindView(R.id.code_bt)
    TimeButton mCodeBt;
    @BindView(R.id.start_login_iv)
    ImageView mStartLoginIv;
    @BindView(R.id.checkbox)
    CheckBox mCheckbox;
    @BindView(R.id.service_agreement_tv)
    TextView mServiceAgreementTv;
    @BindView(R.id.privacy_agreement_tv)
    TextView mPrivacyAgreementTv;
    private User mUser;
    @Inject
    LoginControl.PresenterLogin mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_login);
        initInjectData();
    }

    @Override
    public void initView() {
        mUser = mBuProcessor.getUser();
        mPhoneEt.addTextChangedListener(search_text_OnChange);
        mVerificationCodeEt.addTextChangedListener(code_text_OnChange);
    }

    @Override
    public void initData() {
        verificationCheckBox = mCheckbox.isChecked();
        mCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            verificationCheckBox = isChecked;
            isNextRed();
        });
    }

    @OnClick({R.id.code_bt, R.id.start_login_iv, R.id.service_agreement_tv, R.id.privacy_agreement_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            case R.id.start_login_iv:
                if (verificationText()) {
                    checkPermissions();
                }
                break;
            case R.id.service_agreement_tv:
                break;
            case R.id.privacy_agreement_tv:
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
        LogUtils.e("verifyCodeResponse:" + new Gson().toJson(verifyCodeResponse));
        //TODO 删除
        mVerificationCodeEt.setText(verifyCodeResponse.getCode() + "");
    }

    /**
     * 登录
     */
    private void reqLoginInfo() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.mobile = mPhoneEt.getText().toString();
        loginRequest.code = mVerificationCodeEt.getText().toString();
        loginRequest.deviceId = SystemUtils.getUUID(this, mSharePreferenceUtil);
        mPresenter.onRequestLogin(loginRequest);
    }


    @Override
    public void getLoginSuccess(LoginResponse loginResponse) {
        User mUser = LoginUtils.saveLoginUser(loginResponse);
        mBuProcessor.setLoginUser(mUser);
        if (!mBuProcessor.isFinishFirstWrite()) {
            startActivitys(SubjectSelectActivity.class);
        } else {
            startActivitys(MainActivity.class);
        }
        finish();
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
        if (!mCheckbox.isChecked()) {
            showToast("请阅读协议");
            return false;
        }
        return true;
    }

    /**
     * 检查app 权限
     */
    @SuppressLint("CheckResult")
    private void checkPermissions() {
        RxPermissions mRxPermissions = new RxPermissions(this);
        mRxPermissions.request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CALL_PHONE
        ).subscribe(permission -> {
            if (permission) {
                reqLoginInfo();
            } else {
                showToast("请允许权限");
            }
        });
    }

    private boolean verificationPhone = false;
    private boolean verificationCode = false;
    private boolean verificationCheckBox = false;

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
                verificationPhone = true;
                mCodeBt.setBackgroundResource(R.drawable.bg_red_round_solid_5);
                mCodeBt.setTextColor(getResources().getColor(R.color.white));
            } else {
                verificationPhone = false;
                mCodeBt.setBackgroundResource(R.drawable.bg_line_round_solid_5);
                mCodeBt.setTextColor(getResources().getColor(R.color.color999));
            }
            isNextRed();
        }
    };

    public TextWatcher code_text_OnChange = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            verificationCode = s.toString().length() == 6;
            isNextRed();
        }
    };

    /**
     * 下一步按钮是否变亮
     */
    private void isNextRed() {
        if (verificationPhone && verificationCode && verificationCheckBox) {
            mStartLoginIv.setImageResource(R.mipmap.landing_nextstep_2);
        } else {
            mStartLoginIv.setImageResource(R.mipmap.landing_nextstep_1);
        }
    }


    private void initInjectData() {
        DaggerLoginComponent.builder().appComponent(getAppComponent())
                .loginModule(new LoginModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
