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

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerUploadVideoComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.UploadVideoModule;
import com.shushan.thomework101.entity.constants.ActivityConstant;
import com.shushan.thomework101.entity.constants.Constant;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.request.UploadPersonalInfoRequest;
import com.shushan.thomework101.entity.response.TopicResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.silicompressorr.CompressVideoUtils;
import com.shushan.thomework101.mvp.ui.activity.mine.CustomerServiceActivity;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.utils.PicUtils;
import com.shushan.thomework101.mvp.utils.UserUtil;
import com.shushan.thomework101.mvp.views.NoFullScreenJzvdStd;
import com.shushan.thomework101.mvp.views.ResizableImageView;

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
    @BindView(R.id.question_desc_tv)
    TextView mQuestionDescTv;
    @BindView(R.id.question_content_iv)
    ResizableImageView mQuestionContentIv;
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
//        String gradeValue = UserUtil.gradeIntToString(topicResponse.getGrade()) + "试讲题";
//        mQuestionGradeTv.setText(gradeValue);
        mImageLoaderHelper.displayImage(this, topicResponse.getQuestion(), mQuestionContentIv);
    }

    @Override
    public void getUploadVideoSuccess(String videoUrl) {
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

//            //上传视频文件
//            File file = new File(videoPath);
//            RequestBody photoRequestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
//            MultipartBody.Part photo = MultipartBody.Part.createFormData("video", file.getName(), photoRequestBody);
//            mPresenter.uploadVideoRequest(photo);
            String filePath = Constant.FILE_PATH;
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
//            String destPath = filePath + "new_upload_video" + ".mp4";//压缩后新地址
//            LogUtils.e("destPath:" + destPath);
            // 大于20m自动压缩
            CompressVideoUtils.getCompressVideoTask(this, 450, 20, 1000, 400, new CompressVideoUtils.OnCompressListener() {
                @Override
                public void onFinishCompress(String path, boolean isCompressed) {
                    //上传视频文件
                    File file = new File(path);
                    RequestBody photoRequestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
                    MultipartBody.Part photo = MultipartBody.Part.createFormData("video", file.getName(), photoRequestBody);
                    mPresenter.uploadVideoRequest(photo);
                }
            }).execute(videoPath  // 源文件文件夹
                    , filePath);//目标文件夹
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*Dialog mProgressPressDialog;// 创建自定义样式dialog
    KbWithWordsCircleProgressBar mCircleProgress;

    private void showProgressLoading() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.progress_loading, (ViewGroup) (this).getWindow().getDecorView(), false);// 得到加载view
        mCircleProgress = v.findViewById(R.id.circle_progress);
        TextView loadingTvText = v.findViewById(R.id.loading_tv);
        loadingTvText.setText("压缩中...");
        mProgressPressDialog = new Dialog(this, R.style.loading_dialog);
        mProgressPressDialog.setCancelable(true);
        mProgressPressDialog.setContentView(v);
        mProgressPressDialog.show();
    }

    public void dismissProgressLoading() {
        if (mProgressPressDialog != null && mProgressPressDialog.isShowing()) {
            mProgressPressDialog.dismiss();
        }
        mProgressPressDialog = null;
    }*/

    private void initInjectData() {
        DaggerUploadVideoComponent.builder().appComponent(getAppComponent())
                .uploadVideoModule(new UploadVideoModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
