package com.shushan.thomework101.mvp.ui.activity.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerSystemMsgComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.SystemMsgModule;
import com.shushan.thomework101.entity.constants.Constant;
import com.shushan.thomework101.entity.response.SystemMsgResponse;
import com.shushan.thomework101.help.DialogFactory;
import com.shushan.thomework101.mvp.ui.adapter.SystemMsgAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.ui.dialog.CommonDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 系统消息
 */
public class SystemMsgActivity extends BaseActivity implements SystemMsgControl.SystemMsgView, CommonDialog.CommonDialogListener {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.common_right_tv)
    TextView mCommonRightTv;
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
        mCommonRightTv.setText("清空");
        mCommonRightTv.setVisibility(View.VISIBLE);
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

    @OnClick({R.id.common_left_iv, R.id.common_right_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.common_right_tv:
                DialogFactory.showCommonDialog(this, "你确定清空消息？", "", "取消", "确定", Constant.COMMON_DIALOG_STYLE_1);
                break;
        }
    }

    @Override
    public void commonDialogBtnOkListener() {
        showToast("已清空");
    }


    private void initInjectData() {
        DaggerSystemMsgComponent.builder().appComponent(getAppComponent())
                .systemMsgModule(new SystemMsgModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }

}
