package com.shushan.thomework101.mvp.ui.activity.main;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerSystemMsgComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.SystemMsgModule;
import com.shushan.thomework101.entity.constants.Constant;
import com.shushan.thomework101.entity.request.SystemMsgRequest;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.response.SystemMsgResponse;
import com.shushan.thomework101.help.DialogFactory;
import com.shushan.thomework101.mvp.ui.adapter.SystemMsgAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.ui.dialog.CommonDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 系统消息
 */
public class SystemMsgActivity extends BaseActivity implements SystemMsgControl.SystemMsgView, CommonDialog.CommonDialogListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.common_right_tv)
    TextView mCommonRightTv;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    SystemMsgAdapter mSystemMsgAdapter;
    List<SystemMsgResponse.DataBean> systemMsgResponseList = new ArrayList<>();
    private View mEmptyView;
    private int page = 1;
    private int pageSize = 10;
    @Inject
    SystemMsgControl.PresenterSystemMsg mPresenter;


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_system_msg);
        initInjectData();
    }

    @Override
    public void initView() {
        initEmptyView();
        mCommonTitleTv.setText("系统消息");
        mCommonRightTv.setText("清空");
        mCommonRightTv.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSystemMsgAdapter = new SystemMsgAdapter(systemMsgResponseList);
        mSystemMsgAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mSystemMsgAdapter);
    }

    @Override
    public void initData() {
        onRequestSystemMsg();
    }


    @OnClick({R.id.common_left_iv, R.id.common_right_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                onBackPressed();
                break;
            case R.id.common_right_tv:
                clearMessageDialog();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        onExitMsgActivity();
        super.onBackPressed();
    }

    /**
     * 退出更新首页未读消息接口
     */
    private void onExitMsgActivity() {
        Intent i = new Intent();
        setResult(RESULT_OK, i);
    }


    private void onRequestSystemMsg() {
        SystemMsgRequest systemMsgRequest = new SystemMsgRequest();
        systemMsgRequest.token = mBuProcessor.getToken();
        systemMsgRequest.page = String.valueOf(page);
        systemMsgRequest.pagesize = String.valueOf(pageSize);
        mPresenter.onRequestSystemMsg(systemMsgRequest);
    }


    @Override
    public void getSystemMsgSuccess(SystemMsgResponse systemMsgResponse) {
        systemMsgResponseList = systemMsgResponse.getData();
        if (!systemMsgResponse.getData().isEmpty()) {
            mSystemMsgAdapter.addData(systemMsgResponse.getData());
        } else {
            mSystemMsgAdapter.setEmptyView(mEmptyView);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        if (!systemMsgResponseList.isEmpty()) {
            if (page == 1 && systemMsgResponseList.size() < pageSize) {
                mSystemMsgAdapter.loadMoreEnd(true);
            } else {
                if (systemMsgResponseList.size() < pageSize) {
                    mSystemMsgAdapter.loadMoreEnd();
                } else {
                    //等于10条
                    page++;
                    mSystemMsgAdapter.loadMoreComplete();
                    onRequestSystemMsg();
                }
            }
        } else {
            mSystemMsgAdapter.loadMoreEnd();
        }
    }

    private void clearMessageDialog() {
        DialogFactory.showCommonDialog(this, "你确定要删除消息吗？", "", "取消", "确定", Constant.COMMON_DIALOG_STYLE_1);
    }

    @Override
    public void commonDialogBtnOkListener() {
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.token = mBuProcessor.getToken();
        mPresenter.onRequestDeleteMsg(tokenRequest);
    }

    @Override
    public void getDeleteMsgSuccess() {
        showToast("删除成功");
        mSystemMsgAdapter.setNewData(null);
        mSystemMsgAdapter.setEmptyView(mEmptyView);
    }


    private void initEmptyView() {
        mEmptyView = LayoutInflater.from(this).inflate(R.layout.empty_layout, (ViewGroup) mRecyclerView.getParent(), false);
        ImageView emptyIv = mEmptyView.findViewById(R.id.empty_iv);
        TextView emptyTv = mEmptyView.findViewById(R.id.empty_tv);
        emptyIv.setImageResource(R.mipmap.empty_message);
        emptyTv.setText(getResources().getString(R.string.empty_message));
    }

    private void initInjectData() {
        DaggerSystemMsgComponent.builder().appComponent(getAppComponent())
                .systemMsgModule(new SystemMsgModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
