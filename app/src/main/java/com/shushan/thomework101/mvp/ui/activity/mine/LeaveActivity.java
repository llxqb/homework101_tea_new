package com.shushan.thomework101.mvp.ui.activity.mine;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerLeaveComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.LeaveModule;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.utils.DateUtil;
import com.shushan.thomework101.mvp.utils.LogUtils;
import com.shushan.thomework101.mvp.utils.SelectDialogUtil;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc：请假
 */
public class LeaveActivity extends BaseActivity implements LeaveControl.LeaveView {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.common_right_iv)
    ImageView mCommonRightIv;
    @BindView(R.id.start_time_tv)
    TextView mStartTimeTv;
    @BindView(R.id.end_time_tv)
    TextView mEndTimeTv;
    @BindView(R.id.total_time_et)
    EditText mTotalTimeEt;
    @BindView(R.id.leave_reason_et)
    EditText mLeaveReasonEt;
    @BindView(R.id.text_quantity_tv)
    TextView mTextQuantityTv;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_leave);
        initInjectData();
    }

    @Override
    public void initView() {
        mCommonTitleTv.setText("请假");
        mCommonRightIv.setImageResource(R.mipmap.tutor_service);
        mCommonRightIv.setVisibility(View.VISIBLE);
        mLeaveReasonEt.addTextChangedListener(edit_text_OnChange);
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.common_left_iv, R.id.common_right_iv, R.id.start_time_tv, R.id.end_time_tv, R.id.sure_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.common_right_iv:
                startActivitys(CustomerServiceActivity.class);
                break;
            case R.id.start_time_tv:
                showTimeDialog(mStartTimeTv);
                break;
            case R.id.end_time_tv:
                showTimeDialog(mEndTimeTv);
                break;
            case R.id.sure_tv:
                //进行提交
                if (valid()) {
                    onReqSubmit();
                }
                break;
        }
    }

    private void onReqSubmit() {

    }

    private boolean valid() {
        LogUtils.e("mStartTimeTv:" + mStartTimeTv.getText().toString());
        if (TextUtils.isEmpty(mStartTimeTv.getText().toString())) {
            showToast("开始时间不能为空");
            return false;
        }
        if (TextUtils.isEmpty(mEndTimeTv.getText().toString())) {
            showToast("结束时间不能为空");
            return false;
        }
        if (TextUtils.isEmpty(mTotalTimeEt.getText().toString())) {
            showToast("请假时长不能为空");
            return false;
        }
        if (TextUtils.isEmpty(mLeaveReasonEt.getText().toString())) {
            showToast("请假理由不能为空");
            return false;
        }
        return true;
    }

    /**
     * 选择生日弹框
     */
    private void showTimeDialog(TextView textView) {
        new SelectDialogUtil(this, new SelectDialogUtil.SelectPickerListener() {
            @Override
            public void getSelectText(String text) {
            }

            @Override
            public void getSelectDate(Date date) {
                textView.setText(DateUtil.dateTranString(date, "yyyy-MM-dd HH:mm"));
            }
        }).showBirthdayDialog();
    }


    public TextWatcher edit_text_OnChange = new TextWatcher() {
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
            selectionStart = mLeaveReasonEt.getSelectionStart();
            selectionEnd = mLeaveReasonEt.getSelectionEnd();
            String worldTextNum = s.length() + "/50";
            if (s.length() > 50) {
                showToast("仅限50字以内");
                s.delete(selectionStart - 1, selectionEnd);
                int tempSelection = selectionStart;
                mLeaveReasonEt.setSelection(tempSelection);
            } else {
                mTextQuantityTv.setText(worldTextNum);
            }
        }
    };

    private void initInjectData() {
        DaggerLeaveComponent.builder().appComponent(getAppComponent())
                .leaveModule(new LeaveModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
