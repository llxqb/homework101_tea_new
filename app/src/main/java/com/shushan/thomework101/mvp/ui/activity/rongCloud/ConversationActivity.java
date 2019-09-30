package com.shushan.thomework101.mvp.ui.activity.rongCloud;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerConversationComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.ConversationModule;
import com.shushan.thomework101.mvp.ui.activity.student.StudentDetailActivity;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.ui.dialog.ChatMorePopupWindow;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imlib.model.Conversation;

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
    @BindView(R.id.start_counselling_layout_rl)
    RelativeLayout mStartCounsellingLayoutRl;
    @BindView(R.id.start_counselling)
    TextView mStartCounselling;
    @BindView(R.id.start_counselling_message_tv)
    TextView mStartCounsellingMessageTv;
    /**
     * 会话类型
     */
    private Conversation.ConversationType mConversationType;
    String mTargetId;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_conversation);
        initInjectData();
    }

    @Override
    public void initView() {
        mCommonTitleTv.setText("张三");
        mCommonRightIv.setVisibility(View.VISIBLE);
        mCommonRightIv.setImageResource(R.mipmap.tutor_chat_more);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.common_left_iv, R.id.common_right_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.common_right_iv:
                new ChatMorePopupWindow(this, this).initPopWindow(mConversationLayout);
                break;
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
