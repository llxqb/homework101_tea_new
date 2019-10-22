package com.shushan.thomework101.mvp.ui.activity.student;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerFeedbackComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.FeedbackModule;
import com.shushan.thomework101.entity.constants.ActivityConstant;
import com.shushan.thomework101.entity.request.SubmitFeedbackRequest;
import com.shushan.thomework101.entity.response.FeedBackResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 提交反馈内容
 */
public class SubmitFeedbackContentActivity extends BaseActivity implements FeedbackControl.FeedbackView {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.feedback_date_tv)
    TextView mFeedbackDateTv;
    @BindView(R.id.advantage_ev)
    EditText mAdvantageEv;
    @BindView(R.id.text_quantity_tv)
    TextView mTextQuantityTv;
    @BindView(R.id.disadvantage_ev)
    EditText mDisadvantageEv;
    @BindView(R.id.disadvantage_text_quantity_tv)
    TextView mDisadvantageTextQuantityTv;
    private User mUser;
    /**
     * 辅导反馈列表页返回的id
     */
    String feedbackId;
    @Inject
    FeedbackControl.PresenterFeedback mPresenter;

    public static void start(Context context, String feedbackId, String stuName) {
        Intent intent = new Intent(context, SubmitFeedbackContentActivity.class);
        intent.putExtra("feedbackId", feedbackId);
        intent.putExtra("stuName", stuName);
        context.startActivity(intent);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_submit_feedback_content);
        initInjectData();
        mUser = mBuProcessor.getUser();
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        mAdvantageEv.addTextChangedListener(advantage_text_OnChange);
        mDisadvantageEv.addTextChangedListener(disadvantage_text_OnChange);
        if (getIntent() != null) {
            feedbackId = getIntent().getStringExtra("feedbackId");
            String stuName = getIntent().getStringExtra("stuName");
            String title = stuName + "-辅导反馈";
            mCommonTitleTv.setText(title);
        }
    }


    @OnClick({R.id.common_left_iv, R.id.sure_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.sure_tv:
                //提交
                onSubmitFeedback();
                break;
        }
    }

    /**
     * 提交反馈内容
     */
    private void onSubmitFeedback() {
        SubmitFeedbackRequest submitFeedbackRequest = new SubmitFeedbackRequest();
        submitFeedbackRequest.token = mUser.token;
        submitFeedbackRequest.id = feedbackId;
        submitFeedbackRequest.defect = mAdvantageEv.getText().toString();
        submitFeedbackRequest.merit = mDisadvantageEv.getText().toString();
        mPresenter.submitFeedbackInfo(submitFeedbackRequest);
    }

    @Override
    public void submitFeedbackInfoSuccess() {
        showToast("反馈成功");
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ActivityConstant.UPDATE_FEEDBACK_LIST));
        finish();
    }

    @Override
    public void getFeedbackInfoSuccess(FeedBackResponse response) {
    }


    public TextWatcher advantage_text_OnChange = new TextWatcher() {
        private int selectionStart;
        private int selectionEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            selectionStart = mAdvantageEv.getSelectionStart();
            selectionEnd = mAdvantageEv.getSelectionEnd();
            String worldTextNum = s.length() + "/100";
            if (s.length() > 100) {
                showToast("仅限100字以内");
                s.delete(selectionStart - 1, selectionEnd);
                int tempSelection = selectionStart;
                mAdvantageEv.setSelection(tempSelection);
            } else {
                mTextQuantityTv.setText(worldTextNum);
            }
        }
    };
    public TextWatcher disadvantage_text_OnChange = new TextWatcher() {
        private int selectionStart;
        private int selectionEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            selectionStart = mDisadvantageEv.getSelectionStart();
            selectionEnd = mDisadvantageEv.getSelectionEnd();
            String worldTextNum = s.length() + "/100";
            if (s.length() > 100) {
                showToast("仅限100字以内");
                s.delete(selectionStart - 1, selectionEnd);
                int tempSelection = selectionStart;
                mDisadvantageEv.setSelection(tempSelection);
            } else {
                mDisadvantageTextQuantityTv.setText(worldTextNum);
            }
        }
    };


    private void initInjectData() {
        DaggerFeedbackComponent.builder().appComponent(getAppComponent())
                .feedbackModule(new FeedbackModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
