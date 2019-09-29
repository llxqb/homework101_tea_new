package com.shushan.thomework101.mvp.ui.activity.personalInfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerPersonalInfoComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.PersonalInfoModule;
import com.shushan.thomework101.entity.constants.Constant;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.ui.dialog.AvatarPopupWindow;
import com.shushan.thomework101.mvp.utils.LogUtils;
import com.shushan.thomework101.mvp.utils.PicUtils;

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

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 上传证件
 */
public class UploadCardActivity extends BaseActivity implements PersonalInfoControl.PersonalInfoView, AvatarPopupWindow.PopupWindowListener, TakePhoto.TakeResultListener,
        InvokeListener {

    @BindView(R.id.upload_card_layout)
    LinearLayout mUploadCardLayout;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.common_right_iv)
    ImageView mCommonRightIv;
    @BindView(R.id.id_card_front_iv)
    ImageView mIdCardFrontIv;
    @BindView(R.id.id_card_back_iv)
    ImageView mIdCardBackIv;
    @BindView(R.id.teacher_certification_iv)
    ImageView mTeacherCertificationIv;
    @BindView(R.id.teacher_title_certificate_iv)
    ImageView mTeacherTitleCertificateIv;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private Uri uri;
    //裁剪使用
    private CropOptions cropOptions;
    //成功取得照片
    Bitmap bitmap;
    /**
     * 照片类型
     */
    private int photoType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_upload_card);
        initInjectData();
    }

    @Override
    public void initView() {
        mCommonRightIv.setVisibility(View.VISIBLE);
        mCommonRightIv.setImageResource(R.mipmap.tutor_service);
        File file = new File(getExternalCacheDir(), System.currentTimeMillis() + ".png");
        uri = Uri.fromFile(file);
        int size = Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        cropOptions = new CropOptions.Builder().setOutputX(size).setOutputX(size).setWithOwnCrop(false).create();
    }

    @Override
    public void initData() {
    }


    @OnClick({R.id.common_left_iv, R.id.common_right_iv, R.id.id_card_front_iv, R.id.id_card_back_iv, R.id.teacher_certification_iv, R.id.teacher_title_certificate_iv, R.id.sure_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.common_right_iv:
                //联系客服
                break;
            case R.id.id_card_front_iv://上传身份证正面
                photoType = Constant.IDCARDF_RONT;
                new AvatarPopupWindow(this, this).initPopWindow(mUploadCardLayout);
                break;
            case R.id.id_card_back_iv://上传身份证背面
                photoType = Constant.IDCARDF_BACK;
                new AvatarPopupWindow(this, this).initPopWindow(mUploadCardLayout);
                break;
            case R.id.teacher_certification_iv://上传教师资格证
                photoType = Constant.TEACHER_CERTIFICATION;
                new AvatarPopupWindow(this, this).initPopWindow(mUploadCardLayout);
                break;
            case R.id.teacher_title_certificate_iv://上传教师职称（可选）
                photoType = Constant.TEACHER_TITLE_CERTIFICATE;
                new AvatarPopupWindow(this, this).initPopWindow(mUploadCardLayout);
                break;
            case R.id.sure_tv:
                break;
        }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void takeSuccess(TResult result) {
        bitmap = BitmapFactory.decodeFile(result.getImage().getCompressPath());
        String path = PicUtils.convertIconToString(PicUtils.ImageCompressL(bitmap));
        LogUtils.d("path:" + result.getImage().getCompressPath());
        if (photoType == Constant.IDCARDF_RONT) {
            mImageLoaderHelper.displayCircularImage(this, bitmap, mIdCardFrontIv,0);
        } else if (photoType == Constant.IDCARDF_BACK) {
            mImageLoaderHelper.displayCircularImage(this, bitmap, mIdCardBackIv,0);
        } else if (photoType == Constant.TEACHER_CERTIFICATION) {
            mImageLoaderHelper.displayCircularImage(this, bitmap, mTeacherCertificationIv,0);
        } else if (photoType == Constant.TEACHER_TITLE_CERTIFICATE) {
            mImageLoaderHelper.displayImage(this, "https://hiphotos.baidu.com/image/pic/item/d62a6059252dd42a2d5c75d70f3b5bb5c9eab870.jpg", mTeacherTitleCertificateIv);
        }
        //上传图片
//        uploadImage(path);
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


    private void initInjectData() {
        DaggerPersonalInfoComponent.builder().appComponent(getAppComponent())
                .personalInfoModule(new PersonalInfoModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}