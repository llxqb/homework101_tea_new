package com.shushan.thomework101.mvp.ui.activity.student;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerFeedbackComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.FeedbackModule;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

/**
 * 提交反馈内容
 */
public class SubmitFeedbackContentActivity extends BaseActivity implements FeedbackControl.FeedbackView {

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_submit_feedback_content);
        initInjectData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    private void initInjectData() {
        DaggerFeedbackComponent.builder().appComponent(getAppComponent())
                .feedbackModule(new FeedbackModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
