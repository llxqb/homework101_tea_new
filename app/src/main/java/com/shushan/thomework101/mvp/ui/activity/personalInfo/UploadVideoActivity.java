package com.shushan.thomework101.mvp.ui.activity.personalInfo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerPersonalInfoComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.PersonalInfoModule;
import com.shushan.thomework101.mvp.ui.activity.mine.CustomerServiceActivity;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.utils.PicUtils;
import com.shushan.thomework101.mvp.views.NoFullScreenJzvdStd;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 上传试讲视频
 */
public class UploadVideoActivity extends BaseActivity implements PersonalInfoControl.PersonalInfoView {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.common_right_iv)
    ImageView mCommonRightIv;
    @BindView(R.id.upload_video_title)
    TextView mUploadVideoTitle;
    @BindView(R.id.question_grade_tv)
    TextView mQuestionGradeTv;
    @BindView(R.id.question_content_tv)
    TextView mQuestionContentTv;
    @BindView(R.id.question_desc_tv)
    TextView mQuestionDescTv;
    @BindView(R.id.upload_video_layout)
    RelativeLayout mUploadVideoLayout;
    @BindView(R.id.jz_video)
    NoFullScreenJzvdStd mJzVideo;
    @BindView(R.id.add_video_icon_layout)
    LinearLayout mAddVideoIconLayout;
    @BindView(R.id.upload_video_tv)
    TextView mUploadVideoTv;
    @BindView(R.id.sure_tv)
    TextView mSureTv;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_upload_video);
        initInjectData();
    }

    @Override
    public void initView() {
        mCommonRightIv.setVisibility(View.VISIBLE);
        mCommonRightIv.setImageResource(R.mipmap.tutor_service);
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.common_left_iv, R.id.common_right_iv, R.id.upload_video_layout, R.id.upload_video_tv, R.id.sure_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.common_right_iv:
                startActivitys(CustomerServiceActivity.class);
                break;
            case R.id.upload_video_layout:
                //打开视频
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI), 100);
                break;
            case R.id.upload_video_tv:
                //打开视频
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI), 100);
                break;
            case R.id.sure_tv:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && data != null) {
            Uri selectedVideo = data.getData();
            String[] filePathColumn = {MediaStore.Video.Media.DATA};
            assert selectedVideo != null;
            Cursor cursor = getContentResolver().query(selectedVideo,
                    filePathColumn, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String videoPath = cursor.getString(columnIndex);
            cursor.close();
            //上传视频
            File file = new File(videoPath);
            RequestBody photoRequestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            MultipartBody.Part photo = MultipartBody.Part.createFormData("video", file.getName(), photoRequestBody);
//            mPresenter.uploadVideo(photo);


            //上传成功
            String url = "http://jzvd.nathen.cn/b201be3093814908bf987320361c5a73/2f6d913ea25941ffa78cc53a59025383-5287d2089db37e62345123a1be272f8b.mp4";
            mJzVideo.setUp(url, "");
            mJzVideo.setVisibility(View.VISIBLE);
            mAddVideoIconLayout.setVisibility(View.GONE);
            mUploadVideoTv.setVisibility(View.VISIBLE);
            PicUtils.loadVideoScreenshot(this, url, mJzVideo.thumbImageView, 0, true);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void initInjectData() {
        DaggerPersonalInfoComponent.builder().appComponent(getAppComponent())
                .personalInfoModule(new PersonalInfoModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
