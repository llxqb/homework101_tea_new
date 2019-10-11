package com.shushan.thomework101.mvp.ui.activity.personalInfo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerUploadVideoComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.UploadVideoModule;
import com.shushan.thomework101.entity.constants.ActivityConstant;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.request.UploadPersonalInfoRequest;
import com.shushan.thomework101.entity.response.TopicResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.activity.mine.CustomerServiceActivity;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.utils.LogUtils;
import com.shushan.thomework101.mvp.utils.PicUtils;
import com.shushan.thomework101.mvp.utils.UserUtil;
import com.shushan.thomework101.mvp.views.NoFullScreenJzvdStd;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JzvdStd;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 上传试讲视频
 */
public class UploadVideoActivity extends BaseActivity implements UploadVideoControl.UploadVideoView {

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
    private User mUser;
    /**
     * 上传视频成功后路径
     */
    private String mVideoUrl;
    @Inject
    UploadVideoControl.PresenterUploadVideo mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_upload_video);
        initInjectData();
    }

    @Override
    public void initView() {
        mUser = mBuProcessor.getUser();
        mCommonTitleTv.setText("上传试讲视频");
        String title = "您选择了教授" + UserUtil.gradeArrayToString(mUser.grades) + UserUtil.subjectIntToString(mUser.subject) + "，请按要求讲解以下试题，请抄题后，把视频对着纸上进行讲解录制。";
        mUploadVideoTitle.setText(title);
        mCommonRightIv.setVisibility(View.VISIBLE);
        mCommonRightIv.setImageResource(R.mipmap.tutor_service);
    }

    @Override
    public void initData() {
        onRequestTopicInfo();
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
                onRequestUploadVideo(mVideoUrl);
                break;
        }
    }


    /**
     * 获取是讲题目
     */
    private void onRequestTopicInfo() {
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.token = mUser.token;
        mPresenter.onRequestTopicInfo(tokenRequest);
    }


    @Override
    public void getTopicInfoSuccess(TopicResponse topicResponse) {
        LogUtils.e("topicResponse:" + new Gson().toJson(topicResponse));
        String gradeValue = UserUtil.gradeIntToString(topicResponse.getGrade()) + "试讲题";
        mQuestionGradeTv.setText(gradeValue);
        mQuestionContentTv.setText(topicResponse.getQuestion());
        mQuestionDescTv.setText(topicResponse.getExplain());
    }

    @Override
    public void getUploadVideoSuccess(String videoUrl) {
        LogUtils.e("videoUrl:" + videoUrl);
        mVideoUrl = videoUrl;
        mJzVideo.setUp(videoUrl, "");
        mJzVideo.setVisibility(View.VISIBLE);
        mAddVideoIconLayout.setVisibility(View.GONE);
        mUploadVideoTv.setVisibility(View.VISIBLE);
        PicUtils.loadVideoScreenshot(this, videoUrl, mJzVideo.thumbImageView, 0, true);
    }

    /**
     * 上传视频信息
     */
    private void onRequestUploadVideo(String videoUrl) {
        UploadPersonalInfoRequest request = new UploadPersonalInfoRequest();
        request.token = mUser.token;
        request.video = videoUrl;
        mPresenter.uploadPersonalVideoInfo(request);
    }


    @Override
    public void getUploadPersonalVideoInfoSuccess() {
        showToast("上传成功，请等待审核完成");
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ActivityConstant.UPDATE_USER_CHECK_INFO));
        finish();
    }

    @Override
    public void onPause() {
        super.onPause();
        JzvdStd.goOnPlayOnPause();
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
            //上传视频文件
            File file = new File(videoPath);
            RequestBody photoRequestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            MultipartBody.Part photo = MultipartBody.Part.createFormData("video", file.getName(), photoRequestBody);
            mPresenter.uploadVideoRequest(photo);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initInjectData() {
        DaggerUploadVideoComponent.builder().appComponent(getAppComponent())
                .uploadVideoModule(new UploadVideoModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
