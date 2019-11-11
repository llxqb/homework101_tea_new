package com.shushan.thomework101.mvp.ui.activity.student;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerStudentDetailComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.StudentDetailModule;
import com.shushan.thomework101.entity.constants.ActivityConstant;
import com.shushan.thomework101.entity.constants.Constant;
import com.shushan.thomework101.entity.request.SaveStudentInfoRequest;
import com.shushan.thomework101.entity.request.StudentDetailInfoRequest;
import com.shushan.thomework101.entity.response.StudentDetailInfoResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.help.DialogFactory;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.ui.dialog.EditLabelDialog;
import com.shushan.thomework101.mvp.utils.DateUtil;
import com.shushan.thomework101.mvp.views.CircleImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 学生详情
 */
public class StudentDetailActivity extends BaseActivity implements StudentDetailControl.StudentDetailView, EditLabelDialog.EditLabelDialogListener {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.student_avatar_iv)
    CircleImageView mStudentAvatarIv;
    @BindView(R.id.student_name_tv)
    TextView mStudentNameTv;
    @BindView(R.id.grade)
    TextView mGrade;
    @BindView(R.id.counselling_type_tv)
    TextView mCounsellingTypeTv;
    @BindView(R.id.end_date_tv)
    TextView mEndDateTv;
    @BindView(R.id.textbook_version_tv)
    TextView mTextbookVersionTv;
    @BindView(R.id.text_quantity_tv)
    TextView mTextQuantityTv;
    @BindView(R.id.remark_et)
    EditText mRemarkEt;
    private User mUser;
    /**
     * 学生id
     */
    private String sId;
    /**
     * 学生列表id,更新时候使用
     */
    private String id;
    @Inject
    StudentDetailControl.PresenterStudentDetail mPresenter;

    public static void start(Context context, String sId) {
        Intent intent = new Intent(context, StudentDetailActivity.class);
        intent.putExtra("sId", sId);
        context.startActivity(intent);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_student_detail);
        initInjectData();
        mUser = mBuProcessor.getUser();
    }

    @Override
    public void initView() {
        mCommonTitleTv.setText("学生详情");
    }

    @Override
    public void initData() {
        mRemarkEt.addTextChangedListener(edit_text_OnChange);
        if (getIntent() != null) {
            sId = getIntent().getStringExtra("sId");//学生id
            onRequestStudentInfo();
        }
    }


    @OnClick({R.id.common_left_iv, R.id.textbook_version_layout, R.id.sure_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.textbook_version_layout:
                editLabelDialog(mTextbookVersionTv.getText().toString());
                break;
            case R.id.sure_tv:
                saveStudentInfo();
                break;
        }
    }

    private void onRequestStudentInfo() {
        StudentDetailInfoRequest request = new StudentDetailInfoRequest();
        request.token = mBuProcessor.getToken();
        request.s_id = sId;
        mPresenter.onRequestStudentInfo(request);
    }

    @Override
    public void getStudentInfoSuccess(StudentDetailInfoResponse studentDetailInfoResponse) {
        mImageLoaderHelper.displayImage(this, studentDetailInfoResponse.getCover(), mStudentAvatarIv, Constant.LOADING_AVATOR);
        mStudentNameTv.setText(studentDetailInfoResponse.getName());
        if (!TextUtils.isEmpty(studentDetailInfoResponse.getGrade())) {
            mGrade.setVisibility(View.VISIBLE);
            mGrade.setText(studentDetailInfoResponse.getGrade());
        }
        mCounsellingTypeTv.setText(studentDetailInfoResponse.getStatus());
        mEndDateTv.setText(DateUtil.getStrTime(studentDetailInfoResponse.getEnd_time(), DateUtil.TIME_YYMMDD));
        id = String.valueOf(studentDetailInfoResponse.getId());
        if (studentDetailInfoResponse.getStatus().equals("未付费")) {
            mEndDateTv.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(studentDetailInfoResponse.getVersion())) {
            mTextbookVersionTv.setText(studentDetailInfoResponse.getVersion());
        }
        if (!TextUtils.isEmpty(studentDetailInfoResponse.getRemark())) {
            mRemarkEt.setText(studentDetailInfoResponse.getRemark());
            String remarkValue = studentDetailInfoResponse.getRemark().length() + "/100";
            mTextQuantityTv.setText(remarkValue);
        }
    }

    /**
     * 保存学生信息
     */
    private void saveStudentInfo() {
        SaveStudentInfoRequest request = new SaveStudentInfoRequest();
        request.token = mUser.token;
        request.version = mTextbookVersionTv.getText().toString();
        request.id = id;
        request.remark = mRemarkEt.getText().toString();
        mPresenter.saveStudentInfo(request);
    }


    @Override
    public void saveStudentInfoSuccess() {
        showToast("保存成功");
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ActivityConstant.CHANGE_STUDENT_LIST));
        finish();
    }


    private void editLabelDialog(String versionText) {
        EditLabelDialog editLabelDialog = EditLabelDialog.newInstance();
        editLabelDialog.setListener(this);
        editLabelDialog.setTitle("教材版本", "填写教材版本");
        editLabelDialog.setName(versionText);
        DialogFactory.showDialogFragment(getSupportFragmentManager(), editLabelDialog, EditLabelDialog.TAG);
    }

    @Override
    public void editLabelBtnOkListener(String labelValue) {
        mTextbookVersionTv.setText(labelValue);
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
            selectionStart = mRemarkEt.getSelectionStart();
            selectionEnd = mRemarkEt.getSelectionEnd();
            String worldTextNum = s.length() + "/100";
            if (s.length() > 100) {
                showToast("仅限100字以内");
                s.delete(selectionStart - 1, selectionEnd);
                int tempSelection = selectionStart;
                mRemarkEt.setSelection(tempSelection);
            } else {
                mTextQuantityTv.setText(worldTextNum);
            }
        }
    };

    private void initInjectData() {
        DaggerStudentDetailComponent.builder().appComponent(getAppComponent())
                .studentDetailModule(new StudentDetailModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
