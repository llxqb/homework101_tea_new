package com.shushan.thomework101.mvp.ui.fragment.student;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerMineStudentFragmentComponent;
import com.shushan.thomework101.di.modules.MainModule;
import com.shushan.thomework101.di.modules.MineStudentFragmentModule;
import com.shushan.thomework101.entity.response.MineStudentResponse;
import com.shushan.thomework101.mvp.ui.activity.rongCloud.ConversationActivity;
import com.shushan.thomework101.mvp.ui.activity.student.StudentDetailActivity;
import com.shushan.thomework101.mvp.ui.adapter.MineStudentAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseFragment;
import com.shushan.thomework101.mvp.ui.dialog.StudentTypeMorePopupWindow;
import com.shushan.thomework101.mvp.utils.LogUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
    List<MineStudentResponse> mineStudentResponseList = new ArrayList<>();
    /**
     * 学生付费类型
     * 1：已付费
     * 2：未付费
     */
    private int paidType;

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
    public void initView() {
        mMineStudentAdapter = new MineStudentAdapter(mineStudentResponseList);
        mMineStudentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMineStudentRecyclerView.setAdapter(mMineStudentAdapter);
        mMineStudentAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.student_avatar_iv:
                    //跳到学生详情
                    startActivitys(StudentDetailActivity.class);
                    break;
                case R.id.item_mine_student_layout:
                    //跳到聊天界面
                    startActivitys(ConversationActivity.class);
                    break;
            }
        });
    }

    @Override
    public void initData() {
        for (int i = 0; i < 10; i++) {
            MineStudentResponse mineStudentResponse = new MineStudentResponse();
            mineStudentResponseList.add(mineStudentResponse);
        }
    }

    @OnClick({R.id.all_tv, R.id.paid_layout, R.id.unpaid_layout})
    public void onViewClicked(View view) {
        initTitleColor();
        switch (view.getId()) {
            case R.id.all_tv:
                mAllTv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.student_title_check_color));
                break;
            case R.id.paid_layout:
                paidType = 1;
                mPaidTv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.student_title_check_color));
                List<String> paidTextList = Arrays.asList(getActivity().getResources().getStringArray(R.array.student_paid));
                new StudentTypeMorePopupWindow(getActivity(), this).initPopWindow(mPaidLayout, paidTextList);
                break;
            case R.id.unpaid_layout:
                paidType = 2;
                mUnpaidTv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.student_title_check_color));
                List<String> unPaidTextList = Arrays.asList(getActivity().getResources().getStringArray(R.array.student_unpaid));
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
        LogUtils.e("type:" + type);
        if (paidType == 1) {
            switch (type) {
                case 0:
                    showToast("已付费");
                    mPaidTv.setText("已付费");
                    break;
                case 1:
                    showToast("全部");
                    mPaidTv.setText("全部");
                    break;
                case 2:
                    showToast("月辅导");
                    mPaidTv.setText("月辅导");
                    break;
                case 3:
                    showToast("季辅导");
                    mPaidTv.setText("季辅导");
                    break;
                case 4:
                    showToast("年辅导");
                    mPaidTv.setText("年辅导");
                    break;
            }
        } else if (paidType == 2) {
            switch (type) {
                case 0:
                    showToast("未付费");
                    mUnpaidTv.setText("未付费");
                    break;
                case 1:
                    showToast("全部");
                    mUnpaidTv.setText("全部");
                    break;
                case 2:
                    showToast("今日新增");
                    mUnpaidTv.setText("今日新增");
                    break;
                case 3:
                    showToast("三日新增");
                    mUnpaidTv.setText("三日新增");
                    break;
                case 4:
                    showToast("七日新增");
                    mUnpaidTv.setText("七日新增");
                    break;
            }
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
