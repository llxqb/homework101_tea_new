package com.shushan.thomework101.mvp.ui.activity.personalInfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerPersonalInfoComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.PersonalInfoModule;
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
    ConstraintLayout mUploadCardLayout;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.card_front_tv)
    TextView mCardFrontTv;
    @BindView(R.id.card_front_iv)
    ImageView mCardFrontIv;

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private Uri uri;
    //裁剪使用
    private CropOptions cropOptions;
    //成功取得照片
    Bitmap bitmap;

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
        File file = new File(getExternalCacheDir(), System.currentTimeMillis() + ".png");
        uri = Uri.fromFile(file);
        int size = Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        cropOptions = new CropOptions.Builder().setOutputX(size).setOutputX(size).setWithOwnCrop(false).create();
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.common_left_iv, R.id.card_front_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.card_front_iv:
                //上传身份证正面
                new AvatarPopupWindow(this, this).initPopWindow(mUploadCardLayout);
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
        String path = PicUtils.convertIconToString(bitmap);
        LogUtils.d("path:" + result.getImage().getCompressPath());
        mImageLoaderHelper.displayImage(this, bitmap, mCardFrontIv);
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
