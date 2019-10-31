package com.shushan.thomework101.mvp.ui.activity.rongCloud;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerConversationComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.ConversationModule;
import com.shushan.thomework101.entity.constants.ActivityConstant;
import com.shushan.thomework101.entity.constants.Constant;
import com.shushan.thomework101.entity.request.FeedbackIdRequest;
import com.shushan.thomework101.entity.request.UserInfoByRidRequest;
import com.shushan.thomework101.entity.response.FeedbackIdResponse;
import com.shushan.thomework101.entity.response.UserInfoByRidResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.help.DialogFactory;
import com.shushan.thomework101.mvp.ui.activity.student.StudentDetailActivity;
import com.shushan.thomework101.mvp.ui.activity.student.SubmitFeedbackContentActivity;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.ui.dialog.ChatMorePopupWindow;
import com.shushan.thomework101.mvp.ui.dialog.CommonDialog;
import com.shushan.thomework101.mvp.utils.ConversationUtil;
import com.shushan.thomework101.mvp.utils.LogUtils;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongExtension;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * 融云聊天activity
 */
public class ConversationActivity extends BaseActivity implements ConversationControl.ConversationView, ChatMorePopupWindow.PopupWindowListener, CommonDialog.CommonDialogListener {

    @BindView(R.id.conversation_layout)
    LinearLayout mConversationLayout;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.common_right_iv)
    ImageView mCommonRightIv;
    @BindView(R.id.end_counselling_layout_rl)
    RelativeLayout mEndCounsellingLayoutRl;
    @BindView(R.id.end_counselling)
    TextView mEndCounselling;
    @BindView(R.id.end_counselling_message_tv)
    TextView mEndCounsellingMessageTv;
    @BindView(R.id.rc_extension)
    RongExtension mRcExtension;
    private User mUser;
    /**
     * true:辅导结束
     */
    private boolean counsellingEndState = false;
    /**
     * 会话类型
     */
    private Conversation.ConversationType mConversationType;
    /**
     * 会话对方融云id
     */
    String mTargetId;
    /**
     * 根据融云id 查询出学生信息
     */
    private UserInfoByRidResponse mUserInfoByRidResponse;
    /**
     * 辅导结束 辅导id
     */
    String mFeedbackId = null;
    /**
     * 聊天类型 1 客服
     */
    private int chatType;

    @Inject
    ConversationControl.PresenterConversation mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_conversation);
        initInjectData();
        mUser = mBuProcessor.getUser();
        chatType = mSharePreferenceUtil.getIntData("chatType");//在线客服
    }

    @Override
    public void onReceivePro(Context context, Intent intent) {
        if (chatType != 1 && intent.getAction() != null && intent.getAction().equals(ActivityConstant.SHOW_NOTIFICATION_MESSAGE)) {
            String extra = intent.getStringExtra("extra");
            LogUtils.e("extra:" + extra);
            if (extra.equals("start_coach")) {
                counsellingEndState = false;
                mEndCounsellingMessageTv.setText("辅导结束后，请主动点击结束辅导。\n结束辅导后，将上传辅导记录。");
                mEndCounselling.setText("结束辅导");
                mEndCounsellingLayoutRl.setVisibility(View.VISIBLE);
            } else if (extra.equals("end_coach")) {
                counsellingEndState = true;
                mEndCounsellingLayoutRl.setVisibility(View.VISIBLE);
                mEndCounsellingMessageTv.setText("辅导已完成");
                mEndCounselling.setText("去反馈");
                //更新今日反馈
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ActivityConstant.UPDATE_FEEDBACK_LIST));
                onRequestFeedBackId();
            }
        }
        super.onReceivePro(context, intent);
    }

    @Override
    public void addFilter() {
        super.addFilter();
        mFilter.addAction(ActivityConstant.SHOW_NOTIFICATION_MESSAGE);
    }


    @Override
    public void initView() {
        if (getIntent() != null) {
            mTargetId = Objects.requireNonNull(getIntent().getData()).getQueryParameter("targetId");
            String username = getIntent().getData().getQueryParameter("title");
            mCommonTitleTv.setText(username);
            mConversationType = Conversation.ConversationType.valueOf("PRIVATE");
            if (chatType != 1) {//不是与客服聊天
                mCommonRightIv.setVisibility(View.VISIBLE);
                mCommonRightIv.setImageResource(R.mipmap.tutor_chat_more);
                onRequestUserReleate();
            } else {
                mCommonRightIv.setVisibility(View.GONE);
                mEndCounsellingLayoutRl.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void initData() {
//        mRcExtension.setExtensionClickListener(new IExtensionClickListenerHelper());
        //融云消息已读
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ActivityConstant.RC_READ_MSG));
    }

    @OnClick({R.id.common_left_iv, R.id.end_counselling, R.id.common_right_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.end_counselling:
                if (counsellingEndState) { //去反馈
                    if (mUserInfoByRidResponse != null && mFeedbackId != null) {
                        SubmitFeedbackContentActivity.start(this, mFeedbackId, mUserInfoByRidResponse.getName(), 0);
                        mEndCounsellingLayoutRl.setVisibility(View.GONE);//隐藏辅导反馈入口
                    }
                } else {
                    DialogFactory.showCommonDialog(ConversationActivity.this, "结束辅导", "你确定要结束辅导~", "继续辅导", "确定结束", Constant.COMMON_DIALOG_STYLE_1);
                }
                break;
            case R.id.common_right_iv:
                new ChatMorePopupWindow(this, this).initPopWindow(mConversationLayout);
                break;
        }
    }

    @Override
    public void commonDialogBtnOkListener() {
        //结束辅导  通知学生端调用结束辅导接口
        ConversationUtil.SendInformationNotificationMessage(mTargetId, mConversationType, "老师结束辅导", "end_coach");
//        mEndCounsellingLayoutRl.setVisibility(View.VISIBLE);
//        mEndCounsellingMessageTv.setText("辅导已完成");
//        mEndCounselling.setText("去反馈");
//        counsellingEndState = true;
    }

    /**
     * 根据融云第三方id获取用户头像和昵称
     */
    private void onRequestUserReleate() {
        UserInfoByRidRequest userInfoByRidRequest = new UserInfoByRidRequest();
        userInfoByRidRequest.token = mUser.token;
        userInfoByRidRequest.third_id = mTargetId;
        mPresenter.onRequestUserInfoByRid(userInfoByRidRequest);
    }


    @Override
    public void getUserInfoByRidSuccess(UserInfoByRidResponse userInfoByRidResponse) {
        mUserInfoByRidResponse = userInfoByRidResponse;
        UserInfo userInfo = new UserInfo(mTargetId, userInfoByRidResponse.getName(), Uri.parse(userInfoByRidResponse.getCover()));
        //刷新用户信息  可以刷新会话列表
        RongIM.getInstance().refreshUserInfoCache(userInfo);
        if (userInfoByRidResponse.getGuide_status() == 1) {
            //辅导中
            mEndCounsellingLayoutRl.setVisibility(View.VISIBLE);
        } else {
            mEndCounsellingLayoutRl.setVisibility(View.GONE);
        }
    }

    private void onRequestFeedBackId() {
        FeedbackIdRequest feedbackIdRequest = new FeedbackIdRequest();
        feedbackIdRequest.token = mUser.token;
        if (mUserInfoByRidResponse != null) {
            feedbackIdRequest.s_id = String.valueOf(mUserInfoByRidResponse.getS_id());
        }
        mPresenter.onRequestFeedBackId(feedbackIdRequest);
    }


    @Override
    public void getFeedBackIdSuccess(FeedbackIdResponse feedbackIdResponse) {
        mFeedbackId = String.valueOf(feedbackIdResponse.getId());
    }

    /**
     * 发送付款邀请
     */
    @Override
    public void paymentInvitationBtnListener() {
        //老师邀请学生购买
        ConversationUtil.SendInformationNotificationMessage(mTargetId, mConversationType, "老师发送购买邀请", "msg_invite_buy");
    }

    /**
     * 添加备注
     */
    @Override
    public void addNotesBtnListener() {
        StudentDetailActivity.start(this, String.valueOf(mUserInfoByRidResponse.getS_id()));
    }

    /**
     * 设置版本
     */
    @Override
    public void setVersionBtnListener() {
        StudentDetailActivity.start(this, String.valueOf(mUserInfoByRidResponse.getS_id()));
    }

    /**
     * 学生详情
     */
    @Override
    public void studentDetailBtnListener() {
        StudentDetailActivity.start(this, String.valueOf(mUserInfoByRidResponse.getS_id()));
    }

    private void initInjectData() {
        DaggerConversationComponent.builder().appComponent(getAppComponent())
                .conversationModule(new ConversationModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSharePreferenceUtil.setData("chatType", 0);//设为默认聊天类型
    }
}
