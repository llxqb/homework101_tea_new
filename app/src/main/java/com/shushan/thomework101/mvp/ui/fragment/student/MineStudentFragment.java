package com.shushan.thomework101.mvp.ui.fragment.student;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerMineStudentFragmentComponent;
import com.shushan.thomework101.di.modules.MainModule;
import com.shushan.thomework101.di.modules.MineStudentFragmentModule;
import com.shushan.thomework101.entity.constants.ActivityConstant;
import com.shushan.thomework101.entity.request.MineStudentListRequest;
import com.shushan.thomework101.entity.response.MineStudentResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.activity.student.StudentDetailActivity;
import com.shushan.thomework101.mvp.ui.adapter.MineStudentAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseFragment;
import com.shushan.thomework101.mvp.ui.dialog.StudentTypeMorePopupWindow;
import com.shushan.thomework101.mvp.utils.LogUtils;
import com.shushan.thomework101.mvp.utils.StudentUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;

/**
 * 学生页面 -- 我的学生fragment
 */

public class MineStudentFragment extends BaseFragment implements MineStudentFragmentControl.MineStudentFragmentView, StudentTypeMorePopupWindow.PopupWindowListener {
    @BindView(R.id.all_tv)
    TextView mAllTv;
    @BindView(R.id.paid_tv)
    TextView mPaidTv;
    @BindView(R.id.unpaid_tv)
    TextView mUnpaidTv;
    @BindView(R.id.paid_layout)
    LinearLayout mPaidLayout;
    @BindView(R.id.unpaid_layout)
    LinearLayout mUnPaidLayout;
    @BindView(R.id.mine_student_recycler_view)
    RecyclerView mMineStudentRecyclerView;
    Unbinder unbinder;
    MineStudentAdapter mMineStudentAdapter;
    List<MineStudentResponse.DataBean> mineStudentResponseList = new ArrayList<>();
    /**
     * 学生付费类型
     * 1：已付费
     * 2：未付费
     */
    private int paidType;
    private View mEmptyView;
    private User mUser;
    /**
     * 标签类型
     */
    private String mLabel;

    @Inject
    MineStudentFragmentControl.MineStudentFragmentPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_student, container, false);
        initializeInjector();
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    @Override
    public void onReceivePro(Context context, Intent intent) {
        if (intent.getAction() != null) {
            if (intent.getAction().equals(ActivityConstant.CHANGE_STUDENT_LIST)) {
                onRequestMineStudentInfo();
            } else if (intent.getAction().equals(ActivityConstant.UM_PUSH_CHECK_PASS)) {
//                onRequestMineStudentInfo();
                mUser = mBuProcessor.getUser();
                emptyTv.setText("暂无学生信息");
            }
        }
        super.onReceivePro(context, intent);
    }

    @Override
    public void addFilter() {
        super.addFilter();
        mFilter.addAction(ActivityConstant.CHANGE_STUDENT_LIST);
        mFilter.addAction(ActivityConstant.UM_PUSH_CHECK_PASS);
    }

    @Override
    public void initView() {
        mUser = mBuProcessor.getUser();
        initEmptyView();
        mMineStudentAdapter = new MineStudentAdapter(mineStudentResponseList, mImageLoaderHelper);
        mMineStudentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMineStudentRecyclerView.setAdapter(mMineStudentAdapter);
        mMineStudentAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            MineStudentResponse.DataBean dataBean = (MineStudentResponse.DataBean) adapter.getItem(position);
            if (view.getId() == R.id.student_avatar_iv) {//跳到学生详情
                if (dataBean != null) {
                    StudentDetailActivity.start(getActivity(), String.valueOf(dataBean.getS_id()));
                }
            } else if (view.getId() == R.id.item_mine_student_layout) {
                if (dataBean != null) {
                    //启动单聊页面
                    RongIM.getInstance().startPrivateChat(Objects.requireNonNull(getActivity()), dataBean.getThird_id(), dataBean.getName());
                }
            }
        });
    }

    TextView emptyTv;

    private void initEmptyView() {
        mEmptyView = LayoutInflater.from(getActivity()).inflate(R.layout.empty_layout, (ViewGroup) mMineStudentRecyclerView.getParent(), false);
        ImageView emptyIv = mEmptyView.findViewById(R.id.empty_iv);
        emptyTv = mEmptyView.findViewById(R.id.empty_tv);
        emptyIv.setImageResource(R.mipmap.empty_student);
        if (!mUser.checkPass) {
            emptyTv.setText(getResources().getString(R.string.empty_student));
        } else {
            emptyTv.setText("暂无学生信息");
        }
    }

    @Override
    public void initData() {
        onRequestMineStudentInfo();
    }

    @OnClick({R.id.all_tv, R.id.paid_layout, R.id.unpaid_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all_tv:
                mLabel = "0";
                initTitleColor();
                mAllTv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.student_title_check_color));
                onRequestMineStudentInfo();
                break;
            case R.id.paid_layout:
                paidType = 1;
                List<String> paidTextList = Arrays.asList(Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.student_paid));
                new StudentTypeMorePopupWindow(getActivity(), this).initPopWindow(mPaidLayout, paidTextList);
                break;
            case R.id.unpaid_layout:
                paidType = 2;
                List<String> unPaidTextList = Arrays.asList(Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.student_unpaid));
                new StudentTypeMorePopupWindow(getActivity(), this).initPopWindow(mUnPaidLayout, unPaidTextList);
                break;
        }
    }

    private void initTitleColor() {
        mAllTv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.student_title_un_check_color));
        mPaidTv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.student_title_un_check_color));
        mUnpaidTv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.student_title_un_check_color));
    }

    @Override
    public void studentTypeBtnListener(int type) {
        initTitleColor();
        String studentType = StudentUtil.studentTypeIntToString(paidType, type);
        mLabel = String.valueOf(StudentUtil.labelStringToInt(studentType));
        if (paidType == 1) {
            mPaidTv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.student_title_check_color));
            mPaidTv.setText(studentType);
        } else {
            mUnpaidTv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.student_title_check_color));
            mUnpaidTv.setText(studentType);
        }
        onRequestMineStudentInfo();
    }


    /**
     * 请求我的学生列表
     */
    private void onRequestMineStudentInfo() {
        MineStudentListRequest request = new MineStudentListRequest();
        request.token = mUser.token;
        if (!TextUtils.isEmpty(mLabel)) {
            request.label = mLabel;
        }
        mPresenter.onRequestMineStudentInfo(request);
    }

    @Override
    public void getMineStudentInfoSuccess(MineStudentResponse response) {
        if (!response.getData().isEmpty()) {
            mMineStudentAdapter.setNewData(response.getData());
        } else {
            mMineStudentAdapter.setNewData(null);
            mMineStudentAdapter.setEmptyView(mEmptyView);
        }
    }

    private void initializeInjector() {
        DaggerMineStudentFragmentComponent.builder().appComponent(((HomeworkApplication) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
                .mainModule(new MainModule((AppCompatActivity) getActivity()))
                .mineStudentFragmentModule(new MineStudentFragmentModule(this))
                .build().inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
