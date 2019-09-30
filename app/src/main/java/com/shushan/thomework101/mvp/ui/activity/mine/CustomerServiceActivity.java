package com.shushan.thomework101.mvp.ui.activity.mine;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.CustomerResponse;
import com.shushan.thomework101.mvp.ui.adapter.CustomerAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 客服
 */
public class CustomerServiceActivity extends BaseActivity {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    CustomerAdapter mCustomerAdapter;
    List<CustomerResponse> customerResponseList = new ArrayList<>();
    private int[] icon = {R.mipmap.qq, R.mipmap.wechat, R.mipmap.telephone};
    private String[] name = {"客服QQ", "客服微信", "客服热线"};
    private String[] contactNumber = {"5037334", "zuoye1001", "15675858101"};

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_customer_service);
        ((HomeworkApplication) getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void initView() {
        mCommonTitleTv.setText("客服中心");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCustomerAdapter = new CustomerAdapter(customerResponseList);
        mRecyclerView.setAdapter(mCustomerAdapter);
        mCustomerAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CustomerResponse customerResponse = (CustomerResponse) adapter.getItem(position);
                assert customerResponse != null;
                if (position == 0) {
                    //联系QQ
                    joinQQ(customerResponse.contactNumber);
                } else if (position == 1) {
                    //客服微信
                    toWechat(customerResponse.contactNumber);
                } else {
                    //客服热线
                    checkPermissions(customerResponse.contactNumber);
                }
            }
        });
    }

    @Override
    public void initData() {
        for (int i = 0; i < icon.length; i++) {
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.icon = icon[i];
            customerResponse.name = name[i];
            customerResponse.contactNumber = contactNumber[i];
            customerResponseList.add(customerResponse);
        }
    }


    @OnClick(R.id.common_left_iv)
    public void onViewClicked() {
        finish();
    }


    /**
     * 跳转QQ聊天界面
     */
    public void joinQQ(String qq) {
        try {
            //第二种方式：可以跳转到添加好友，如果qq号是好友了，直接聊天
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + qq;//uin是发送过去的qq号码
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            e.printStackTrace();
            showToast("请检查是否安装QQ");
        }
    }

    /**
     * 跳转到微信
     */
    private void toWechat(String wxNum) {
        try {
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            // 将文本内容放到系统剪贴板里。
            cm.setText(wxNum);
            showToast("账号已复制");
            //跳转到微信
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            showToast("检查到您手机没有安装微信，请安装后使用该功能");
        }
    }

    /**
     * 检查app 权限
     */
    @SuppressLint("CheckResult")
    private void checkPermissions(String phoneNum) {
        RxPermissions mRxPermissions = new RxPermissions(this);
        mRxPermissions.request(
                Manifest.permission.CALL_PHONE
        ).subscribe(permission -> {
            if (permission) {
                callPhone(phoneNum);
            } else {
                showToast("请打开通话权限");
            }
        });
    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
}
