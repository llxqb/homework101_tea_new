package com.shushan.thomework101.mvp.ui.activity.student;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerStudentDetailComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.StudentDetailModule;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 学生详情
 */
public class StudentDetailActivity extends BaseActivity implements StudentDetailControl.StudentDetailView {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.student_avatar_iv)
    ImageView mStudentAvatarIv;
    @BindView(R.id.student_name_tv)
    TextView mStudentNameTv;
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

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_student_detail);
        initInjectData();
    }

    @Override
    public void initView() {
        mCommonTitleTv.setText("学生详情");
    }

    @Override
    public void initData() {
        mRemarkEt.addTextChangedListener(edit_text_OnChange);
    }


    @OnClick({R.id.common_left_iv, R.id.textbook_version_layout, R.id.sure_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.textbook_version_layout:

                break;
            case R.id.sure_tv:

                break;
        }
    }

    private void initInjectData() {
        DaggerStudentDetailComponent.builder().appComponent(getAppComponent())
                .studentDetailModule(new StudentDetailModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
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
}
