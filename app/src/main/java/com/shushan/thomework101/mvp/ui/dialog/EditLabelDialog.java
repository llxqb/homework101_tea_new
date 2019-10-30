package com.shushan.thomework101.mvp.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.help.DialogFactory;
import com.shushan.thomework101.mvp.utils.ValueUtil;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 编辑我的标签dialog
 *
 * @author li.liu
 */
public class EditLabelDialog extends BaseDialogFragment {
    public static final String TAG = EditLabelDialog.class.getSimpleName();
    @BindView(R.id.name_hint_tv)
    TextView mNameHintTv;
    @BindView(R.id.name_et)
    EditText mNameEt;
    Unbinder unbinder;
    private EditLabelDialogListener dialogBtnListener;
    private String mTitle;
    private String mHintText;
    private String name;

    public static EditLabelDialog newInstance() {
        return new EditLabelDialog();
    }

    public void setListener(EditLabelDialogListener dialogBtnListener) {
        this.dialogBtnListener = dialogBtnListener;
    }

    public void setTitle(String title, String hintText) {
        this.mTitle = title;
        this.mHintText = hintText;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_name, container, true);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        if (!TextUtils.isEmpty(name)) {
            mNameEt.setText(name);
        } else {
            mNameEt.setHint(mHintText);
        }
        mNameHintTv.setText(mTitle);
//        mNameEt.addTextChangedListener(edit_text_OnChange);
    }


    @OnClick({R.id.cancel, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                closeEditNameDialog();
                break;
            case R.id.sure:
                String nameValue = mNameEt.getText().toString();
                if (!TextUtils.isEmpty(nameValue) || mTitle.equals("教材版本")) {
                    if (ValueUtil.containsEmoji(nameValue)) {
                        showToast("不支持输入Emoji表情符号");
                        return;
                    }
                    if (dialogBtnListener != null) {
                        dialogBtnListener.editLabelBtnOkListener(nameValue);
                    }
                    closeEditNameDialog();
                }
                break;
        }
    }


    public interface EditLabelDialogListener {
        void editLabelBtnOkListener(String labelValue);
    }

    public void closeEditNameDialog() {
        try {
            this.dismiss();
        } catch (Exception e) {
            DialogFactory.dismissDialogFragment(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), TAG);
        }
    }

//    public TextWatcher edit_text_OnChange = new TextWatcher() {
//
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            if (s.length() > 10) {
//                showToast("请小于10个字符");
//            }
//        }
//    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
