package com.shushan.thomework101.mvp.ui.activity.student.rongCloud;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerConversationComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.ConversationModule;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import butterknife.BindView;
import io.rong.imlib.model.Conversation;

/**
 * 融云聊天activity
 */
public class ConversationActivity extends BaseActivity implements ConversationControl.ConversationView {

    String mTargetId;
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

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_conversation);
        initInjectData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    private void initInjectData() {
        DaggerConversationComponent.builder().appComponent(getAppComponent())
                .conversationModule(new ConversationModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }

}
