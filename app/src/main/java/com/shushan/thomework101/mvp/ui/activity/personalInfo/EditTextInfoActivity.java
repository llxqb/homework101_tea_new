package com.shushan.thomework101.mvp.ui.activity.personalInfo;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * desc: 编辑教学文字信息
 */
public class EditTextInfoActivity extends BaseActivity {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.content_et)
    EditText mContentEt;
    @BindView(R.id.text_quantity_tv)
    TextView mTextQuantityTv;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_edit_text_info);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        mContentEt.addTextChangedListener(edit_text_OnChange);
        if (getIntent() != null) {
            String title = getIntent().getStringExtra("title");
            String editContent = getIntent().getStringExtra("editContent");
            mCommonTitleTv.setText(title);
            if (!TextUtils.isEmpty(editContent)) {
                mContentEt.setText(editContent);
            } else {
                mContentEt.setHint("请填写" + title);
            }
        }
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.common_left_iv, R.id.save_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.save_tv:
                onRequestSaveEditText();
                break;
        }
    }

    private void onRequestSaveEditText() {
        Intent i = new Intent();
        i.putExtra("editWord", mContentEt.getText().toString());
        setResult(RESULT_OK, i);
        finish();
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
            selectionStart = mContentEt.getSelectionStart();
            selectionEnd = mContentEt.getSelectionEnd();
            String worldTextNum = s.length() + "/300";
            if (s.length() > 300) {
                showToast("仅限300字以内");
                s.delete(selectionStart - 1, selectionEnd);
                int tempSelection = selectionStart;
                mContentEt.setSelection(tempSelection);
            } else {
                mTextQuantityTv.setText(worldTextNum);
            }
        }
    };
}
