package com.shushan.thomework101.mvp.ui.activity.personalInfo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerPersonalInfoComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.PersonalInfoModule;
import com.shushan.thomework101.entity.constants.ActivityConstant;
import com.shushan.thomework101.entity.constants.Constant;
import com.shushan.thomework101.entity.request.UploadImage;
import com.shushan.thomework101.entity.request.UploadPersonalInfoRequest;
import com.shushan.thomework101.entity.response.HomeResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.help.DialogFactory;
import com.shushan.thomework101.mvp.ui.activity.mine.CustomerServiceActivity;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.ui.dialog.AvatarPopupWindow;
import com.shushan.thomework101.mvp.ui.dialog.CommonDialog;
import com.shushan.thomework101.mvp.ui.dialog.EditLabelDialog;
import com.shushan.thomework101.mvp.utils.LogUtils;
import com.shushan.thomework101.mvp.utils.PicUtils;
import com.shushan.thomework101.mvp.utils.UserUtil;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑个人信息
 */
public class EditPersonalInfoActivity extends BaseActivity implements PersonalInfoControl.PersonalInfoView, EditLabelDialog.EditLabelDialogListener,
        AvatarPopupWindow.PopupWindowListener, TakePhoto.TakeResultListener, InvokeListener, CommonDialog.CommonDialogListener {

    @BindView(R.id.edit_personal_info_layout)
    LinearLayout mEditPersonalInfoLayout;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.common_right_iv)
    ImageView mCommonRightIv;
    @BindView(R.id.username_tv)
    TextView mUsernameTv;
    @BindView(R.id.mine_personal_show_layout)
    ConstraintLayout mMinePersonalShowLayout;
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
    @BindView(R.id.sure_tv)
    TextView mSureTv;
    /**
     * 0：设置姓名
     * 1：标签1
     * 2：标签2
     */
    private int labelType;
    User mUser;
    /**
     * type
     * 1: 注册流程：个人资料
     * 2: 我的资料
     */
    private int type;
    private String avatarUrl;
    private List<String> labelString = new ArrayList<>();

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private Uri uri;
    //裁剪使用
    private CropOptions cropOptions;
    //成功取得照片
    Bitmap bitmap;

    @Inject
    PersonalInfoControl.PresenterPersonalInfo mPresenter;

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, EditPersonalInfoActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_edit_personal_info);
        initInjectData();
        mUser = mBuProcessor.getUser();
        File file = new File(getExternalCacheDir(), System.currentTimeMillis() + ".png");
        uri = Uri.fromFile(file);
        int size = Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        cropOptions = new CropOptions.Builder().setOutputX(size).setOutputX(size).setWithOwnCrop(false).create();
    }


    @Override
    public void initView() {
        mCommonRightIv.setImageResource(R.mipmap.tutor_service);
        mCommonRightIv.setVisibility(View.VISIBLE);
        if (getIntent() != null) {
            type = getIntent().getIntExtra("type", 0);
            if (type == 1) {
                mCommonTitleTv.setText("个人资料");
                mMinePersonalShowLayout.setVisibility(View.GONE);
                mSureTv.setVisibility(View.VISIBLE);
            } else {
                mCommonTitleTv.setText("我的资料");
                mMinePersonalShowLayout.setVisibility(View.VISIBLE);
                mSureTv.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void initData() {
        if (!TextUtils.isEmpty(mUser.name)) {
            mUsernameTv.setText(mUser.name);
        }
        if (mUser.subject != 0) {
            mSubjectTv.setText(UserUtil.subjectIntToString(mUser.subject));
        }
        if (!TextUtils.isEmpty(mUser.grades)) {
            mGradeTv.setText(UserUtil.gradeArrayToString(mUser.grades));
        }

        if (mUser.coachingTime != null) {
            HomeResponse.UserBean.GuideTimeBean guideTimeBean = new Gson().fromJson(mUser.coachingTime, HomeResponse.UserBean.GuideTimeBean.class);
            mCounsellingDate1Tv.setText(UserUtil.dayArrayToString(guideTimeBean.getWorkday()));
            mCounsellingTime1Tv.setText(guideTimeBean.getWork_time());
            mCounsellingDate2Tv.setText(UserUtil.dayArrayToString(guideTimeBean.getOff_day()));
            mCounsellingTime2Tv.setText(guideTimeBean.getOff_time());
        }
        //我的标签
        if (!TextUtils.isEmpty(mUser.labels)) {
            if (mUser.labels.contains(",")) {
                mLabel1Tv.setText(mUser.labels.split(",")[0]);
                mLabel2Tv.setText(mUser.labels.split(",")[1]);
            } else {
                mLabel1Tv.setText(mUser.labels);
            }
        }
        mImageLoaderHelper.displayCircularImage(this, mUser.cover, mUploadPhotoIv, R.mipmap.id_photo);
        mTeachingExperienceContentTv.setText(mUser.teachingExperience);
        mTeachingStyleContentTv.setText(mUser.teachingStyle);
        mTeachingPhilosophyContentTv.setText(mUser.teachingPhilosophy);
    }

    Intent intent;

    @OnClick({R.id.common_left_iv, R.id.common_right_iv, R.id.username_tv, R.id.label1_tv, R.id.label2_tv,
            R.id.upload_photo_btn_layout, R.id.teaching_experience_tv_edit_tv, R.id.teaching_style_tv_edit_tv, R.id.teaching_philosophy_tv_edit_tv, R.id.sure_tv})
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
                new AvatarPopupWindow(this, this).initPopWindow(mEditPersonalInfoLayout);
                break;
            case R.id.teaching_experience_tv_edit_tv://教学经历
                intent = new Intent(this, EditTextInfoActivity.class);
                intent.putExtra("title", "教学经历");
                intent.putExtra("editContent", mTeachingExperienceContentTv.getText().toString());
                startActivityForResult(intent, 1);
                break;
            case R.id.teaching_style_tv_edit_tv:
                intent = new Intent(this, EditTextInfoActivity.class);
                intent.putExtra("title", "教学风格");
                intent.putExtra("editContent", mTeachingStyleContentTv.getText().toString());
                startActivityForResult(intent, 2);
                break;
            case R.id.teaching_philosophy_tv_edit_tv:
                intent = new Intent(this, EditTextInfoActivity.class);
                intent.putExtra("title", "教育理念");
                intent.putExtra("editContent", mTeachingPhilosophyContentTv.getText().toString());
                startActivityForResult(intent, 3);
                break;
            case R.id.sure_tv:
                if (verify()) {
                    uploadPersonalInfo();
                }
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
            if (type == 2) {
                updatePersonalInfo(labelValue, null, null, null, null, null);
            }
        } else if (labelType == 1) {
            mLabel1Tv.setText(labelValue);
            if (type == 2) {
                updatePersonalInfo(null, null, stringToList(labelValue + "," + mLabel1Tv.getText().toString()).toString(), null, null, null);
            }
        } else {
            mLabel2Tv.setText(labelValue);
            if (type == 2) {
                updatePersonalInfo(null, null, stringToList(mLabel1Tv.getText().toString()).toString() + "," + labelValue, null, null, null);
            }
        }
    }

    /**
     * 字符串转集合
     */
    private List<String> stringToList(String text) {
        List<String> stringList = new ArrayList<>();
        if (!TextUtils.isEmpty(text)) {
            stringList.add(text.split(",")[0]);
            stringList.add(text.split(",")[1]);
        }
        return stringList;
    }


    @Override
    public void takeSuccess(TResult result) {
        bitmap = BitmapFactory.decodeFile(result.getImage().getCompressPath());
        String path = PicUtils.convertIconToString(PicUtils.ImageCompressL(bitmap));
        LogUtils.d("path:" + result.getImage().getCompressPath());
        //上传图片
        uploadImage(path);
    }

    /**
     * 上传图片
     */
    private void uploadImage(String filename) {
        UploadImage uploadImage = new UploadImage();
        uploadImage.pic = filename;
        mPresenter.uploadImageRequest(uploadImage);
    }

    @Override
    public void getUploadImageSuccess(String picUrl) {
        avatarUrl = picUrl;
        mImageLoaderHelper.displayCircularImage(this, avatarUrl, mUploadPhotoIv, 0);
        if (type == 2) {
            updatePersonalInfo(null, avatarUrl, null, null, null, null);
        }
    }


    /**
     * 完善个人资料
     */
    private void uploadPersonalInfo() {
        labelString.add(mLabel1Tv.getText().toString());
        labelString.add(mLabel2Tv.getText().toString());
        UploadPersonalInfoRequest request = new UploadPersonalInfoRequest();
        request.token = mUser.token;
        request.name = mUsernameTv.getText().toString();
        request.cover = avatarUrl;
        request.label = new Gson().toJson(labelString);
        request.experience = mTeachingExperienceContentTv.getText().toString();
        request.style = mTeachingStyleContentTv.getText().toString();
        request.idea = mTeachingPhilosophyContentTv.getText().toString();
        LogUtils.e("request:" + new Gson().toJson(request));
        mPresenter.uploadPersonalInfo(request);
    }

    /**
     * 注册流程，上传个人信息成功
     */
    @Override
    public void getUploadPersonalInfoSuccess() {
        showUnderReviewDialog();
    }

    /**
     * 显示正在审核中dialog
     */
    private void showUnderReviewDialog() {
        DialogFactory.showCommonDialog(this, "您的资料修改已经提交成功。待审核通过后，将在页面显示", "", "", "", Constant.COMMON_DIALOG_STYLE_2);
    }

    @Override
    public void commonDialogBtnOkListener() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ActivityConstant.UPDATE_USER_CHECK_INFO));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String editWord = data.getStringExtra("editWord");
            if (requestCode == 1) {
                mTeachingExperienceContentTv.setText(editWord);
                updatePersonalInfo(null, null, null, editWord, null, null);
            } else if (requestCode == 2) {
                mTeachingStyleContentTv.setText(editWord);
                updatePersonalInfo(null, null, null, null, editWord, null);
            } else if (requestCode == 3) {
                mTeachingPhilosophyContentTv.setText(editWord);
                updatePersonalInfo(null, null, null, null, null, editWord);
            }
        }
    }

    /**
     * 更新个人资料
     */
    private void updatePersonalInfo(String name, String cover, String label, String experience, String style, String idea) {
        UploadPersonalInfoRequest request = new UploadPersonalInfoRequest();
        request.token = mUser.token;
        if (name != null) {
            request.name = name;
        }
        if (cover != null) {
            request.cover = cover;
        }
        if (label != null) {
            request.label = label;
        }
        if (experience != null) {
            request.experience = experience;
        }
        if (style != null) {
            request.style = style;
        }
        if (idea != null) {
            request.idea = idea;
        }
        LogUtils.e("request:" + new Gson().toJson(request));
        mPresenter.updatePersonalInfo(request);
    }

    /**
     * 更新个人信息成功
     */
    @Override
    public void getUpdatePersonalInfoSuccess() {
        showToast("修改成功，请等待审核");
    }


    @Override
    public void getUploadPersonalCardInfoSuccess() {
    }

    @Override
    public void takePhotoBtnListener() {
        //拍照进行裁剪
        takePhoto.onPickFromCaptureWithCrop(uri, cropOptions);
    }

    @Override
    public void albumBtnListener() {
        //从相册中选取进行裁剪
        takePhoto.onPickFromGalleryWithCrop(uri, cropOptions);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void takeFail(TResult result, String msg) {
    }

    @Override
    public void takeCancel() {
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    /**
     * 获取TakePhoto实例
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        //设置压缩规则，最大500kb
        takePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(500 * 1024).setMaxPixel(800).create(), true);
        return takePhoto;
    }

    private boolean verify() {
        if (TextUtils.isEmpty(mUsernameTv.getText())) {
            showToast("姓名不能为空");
            return false;
        }
        if (TextUtils.isEmpty(mLabel1Tv.getText())) {
            showToast("请填写两个标签");
            return false;
        }
        if (TextUtils.isEmpty(mLabel2Tv.getText())) {
            showToast("请填写两个标签");
            return false;
        }
        if (TextUtils.isEmpty(avatarUrl)) {
            showToast("照片不能为空");
            return false;
        }
        if (TextUtils.isEmpty(mTeachingExperienceContentTv.getText())) {
            showToast("教学经历不能为空");
            return false;
        }
        if (TextUtils.isEmpty(mTeachingStyleContentTv.getText())) {
            showToast("教学风格不能为空");
            return false;
        }
        if (TextUtils.isEmpty(mTeachingPhilosophyContentTv.getText())) {
            showToast("教育理念不能为空");
            return false;
        }
        return true;
    }

    private void initInjectData() {
        DaggerPersonalInfoComponent.builder().appComponent(getAppComponent())
                .personalInfoModule(new PersonalInfoModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
