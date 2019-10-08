package com.shushan.thomework101.mvp.ui.activity.personalInfo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerPersonalInfoComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.PersonalInfoModule;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.help.DialogFactory;
import com.shushan.thomework101.mvp.ui.activity.mine.CustomerServiceActivity;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.ui.dialog.EditLabelDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑个人信息
 */
public class EditPersonalInfoActivity extends BaseActivity implements PersonalInfoControl.PersonalInfoView, EditLabelDialog.EditLabelDialogListener {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.common_right_iv)
    ImageView mCommonRightIv;
    @BindView(R.id.username_tv)
    TextView mUsernameTv;
    @BindView(R.id.subject_tv)
    TextView mSubjectTv;
    @BindView(R.id.grade_tv)
    TextView mGradeTv;
    @BindView(R.id.counselling_date1_tv)
    TextView mCounsellingDate1Tv;
    @BindView(R.id.counselling_time1_tv)
    TextView mCounsellingTime1Tv;
    @BindView(R.id.counselling_date2_tv)
    TextView mCounsellingDate2Tv;
    @BindView(R.id.counselling_time2_tv)
    TextView mCounsellingTime2Tv;
    @BindView(R.id.label1_tv)
    TextView mLabel1Tv;
    @BindView(R.id.label2_tv)
    TextView mLabel2Tv;
    @BindView(R.id.upload_photo_iv)
    ImageView mUploadPhotoIv;
    @BindView(R.id.teaching_experience_content_tv)
    TextView mTeachingExperienceContentTv;
    @BindView(R.id.teaching_style_content_tv)
    TextView mTeachingStyleContentTv;
    @BindView(R.id.teaching_philosophy_content_tv)
    TextView mTeachingPhilosophyContentTv;
    /**
     * 0：设置姓名
     * 1：标签1
     * 2：标签2
     */
    private int labelType;
    User mUser;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_edit_personal_info);
        initInjectData();
    }

    @Override
    public void initView() {
        mUser = mBuProcessor.getUser();
        mCommonTitleTv.setText("我的资料");
        mCommonRightIv.setImageResource(R.mipmap.tutor_service);
        mCommonRightIv.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.common_left_iv, R.id.common_right_iv, R.id.username_tv, R.id.label1_tv, R.id.label2_tv,
            R.id.upload_photo_btn_layout, R.id.teaching_experience_tv_edit_tv, R.id.teaching_style_tv_edit_tv, R.id.teaching_philosophy_tv_edit_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.common_right_iv:
                startActivitys(CustomerServiceActivity.class);
                break;
            case R.id.username_tv:
                //我的姓名
                labelType = 0;
                editLabelDialog("我的姓名", "请输入你的姓名", mUsernameTv.getText().toString());
                break;
            case R.id.label1_tv:
                //我的标签 1
                labelType = 1;
                editLabelDialog("我的标签", "请输入你的标签", mLabel1Tv.getText().toString());
                break;
            case R.id.label2_tv:
                labelType = 2;
                editLabelDialog("我的标签", "请输入你的标签", mLabel2Tv.getText().toString());
                break;
            case R.id.upload_photo_btn_layout:
                break;
            case R.id.teaching_experience_tv_edit_tv://教学经历
                EditTextInfoActivity.start(this,"教学经历");
                break;
            case R.id.teaching_style_tv_edit_tv:
                EditTextInfoActivity.start(this,"教学风格");
                break;
            case R.id.teaching_philosophy_tv_edit_tv:
                EditTextInfoActivity.start(this,"教育理念");
                break;
        }
    }

    private void editLabelDialog(String title, String hintText, String label) {
        EditLabelDialog editLabelDialog = EditLabelDialog.newInstance();
        editLabelDialog.setListener(this);
        editLabelDialog.setTitle(title, hintText);
        editLabelDialog.setName(label);
        DialogFactory.showDialogFragment(getSupportFragmentManager(), editLabelDialog, EditLabelDialog.TAG);
    }

    @Override
    public void editLabelBtnOkListener(String labelValue) {
        if (labelType == 0) {
            mUsernameTv.setText(labelValue);
        } else if (labelType == 1) {
            mLabel1Tv.setText(labelValue);
        } else {
            mLabel2Tv.setText(labelValue);
        }
    }


    private void initInjectData() {
        DaggerPersonalInfoComponent.builder().appComponent(getAppComponent())
                .personalInfoModule(new PersonalInfoModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
