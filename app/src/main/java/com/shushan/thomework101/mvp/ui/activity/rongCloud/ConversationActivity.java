package com.shushan.thomework101.mvp.ui.activity.rongCloud;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.shushan.thomework101.entity.request.UserInfoByRidRequest;
import com.shushan.thomework101.entity.response.UserInfoByRidResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.activity.student.StudentDetailActivity;
import com.shushan.thomework101.mvp.ui.activity.student.SubmitFeedbackContentActivity;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.ui.dialog.ChatMorePopupWindow;
import com.shushan.thomework101.mvp.utils.ConversationUtil;
import com.shushan.thomework101.mvp.utils.LogUtils;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * 融云聊天activity
 */
public class ConversationActivity extends BaseActivity implements ConversationControl.ConversationView, ChatMorePopupWindow.PopupWindowListener {

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
     * 会话对方id
     */
    String mTargetId;

    @Inject
    ConversationControl.PresenterConversation mPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_conversation);
        initInjectData();
        mUser = mBuProcessor.getUser();
    }

    @Override
    public void onReceivePro(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(ActivityConstant.SHOW_NOTIFICATION_MESSAGE)) {
            String extra = intent.getStringExtra("extra");
            LogUtils.e("extra:" + extra);
            if (extra.equals("start_coach")) {
                counsellingEndState = false;
                mEndCounsellingLayoutRl.setVisibility(View.VISIBLE);
            } else if (extra.equals("end_coach")) {
                mEndCounsellingLayoutRl.setVisibility(View.VISIBLE);
                mEndCounsellingMessageTv.setText("辅导已完成");
                mEndCounselling.setText("去反馈");
                counsellingEndState = true;
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
        mCommonRightIv.setVisibility(View.VISIBLE);
        mCommonRightIv.setImageResource(R.mipmap.tutor_chat_more);
        if (getIntent() != null) {
            mTargetId = Objects.requireNonNull(getIntent().getData()).getQueryParameter("targetId");
            String username = getIntent().getData().getQueryParameter("title");
            mCommonTitleTv.setText(username);
            mConversationType = Conversation.ConversationType.valueOf("PRIVATE");
            onRequestUserReleate();
        }
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.common_left_iv, R.id.end_counselling, R.id.common_right_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.end_counselling:
                if (counsellingEndState) {
                    //去反馈
                    startActivitys(SubmitFeedbackContentActivity.class);
                } else {
                    //结束辅导  通知学生端调用结束辅导接口
                    ConversationUtil.SendInformationNotificationMessage(mTargetId, mConversationType, "老师结束辅导", "end_coach");
                    mEndCounsellingLayoutRl.setVisibility(View.VISIBLE);
                    mEndCounsellingMessageTv.setText("辅导已完成");
                    mEndCounselling.setText("去反馈");
                    counsellingEndState = true;
                }
                break;
            case R.id.common_right_iv:
                new ChatMorePopupWindow(this, this).initPopWindow(mConversationLayout);
                break;
        }
    }

    private void onRequestUserReleate() {
        UserInfoByRidRequest userInfoByRidRequest = new UserInfoByRidRequest();
        userInfoByRidRequest.token = mUser.token;
        userInfoByRidRequest.third_id = mTargetId;
        mPresenter.onRequestUserInfoByRid(userInfoByRidRequest);
    }

    @Override
    public void getUserInfoByRidSuccess(UserInfoByRidResponse userInfoByRidResponse) {
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

    /**
     * 发送付款邀请
     */
    @Override
    public void paymentInvitationBtnListener() {
        showToast("发送付款邀请");
    }

    /**
     * 添加备注
     */
    @Override
    public void addNotesBtnListener() {
        showToast("添加备注");
    }

    /**
     * 设置版本
     */
    @Override
    public void setVersionBtnListener() {
        showToast("设置版本");
    }

    /**
     * 学生详情
     */
    @Override
    public void studentDetailBtnListener() {
        startActivitys(StudentDetailActivity.class);
    }

    private void initInjectData() {
        DaggerConversationComponent.builder().appComponent(getAppComponent())
                .conversationModule(new ConversationModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
