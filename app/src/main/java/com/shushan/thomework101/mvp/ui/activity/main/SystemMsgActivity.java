package com.shushan.thomework101.mvp.ui.activity.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerSystemMsgComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.SystemMsgModule;
import com.shushan.thomework101.entity.response.SystemMsgResponse;
import com.shushan.thomework101.mvp.ui.adapter.SystemMsgAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 系统消息
 */
public class SystemMsgActivity extends BaseActivity implements SystemMsgControl.SystemMsgView {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    SystemMsgAdapter mSystemMsgAdapter;
    List<SystemMsgResponse> systemMsgResponseList = new ArrayList<>();

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_system_msg);
        initInjectData();
    }

    @Override
    public void initView() {
        mCommonTitleTv.setText("系统消息");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSystemMsgAdapter = new SystemMsgAdapter(systemMsgResponseList);
        mRecyclerView.setAdapter(mSystemMsgAdapter);

    }

    @Override
    public void initData() {
        for (int i = 0; i < 10; i++) {
            SystemMsgResponse systemMsgResponse = new SystemMsgResponse();
            systemMsgResponseList.add(systemMsgResponse);
        }
    }


    @OnClick(R.id.common_left_iv)
    public void onViewClicked() {
        finish();
    }

    private void initInjectData() {
        DaggerSystemMsgComponent.builder().appComponent(getAppComponent())
                .systemMsgModule(new SystemMsgModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
