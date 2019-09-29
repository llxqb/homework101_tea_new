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
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 学生页面 -- 我的学生fragment
 */

public class MineStudentFragment extends BaseFragment implements MineStudentFragmentControl.MineStudentFragmentView {

    @BindView(R.id.all_tv)
    TextView mAllTv;
    @BindView(R.id.paid_tv)
    TextView mPaidTv;
    @BindView(R.id.unpaid_tv)
    TextView mUnpaidTv;
    @BindView(R.id.mine_student_recycler_view)
    RecyclerView mMineStudentRecyclerView;
    Unbinder unbinder;

    MineStudentAdapter mMineStudentAdapter;
    List<MineStudentResponse> mineStudentResponseList = new ArrayList<>();

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
        mMineStudentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.student_avatar_iv:
                        //跳到学生详情
                        startActivitys(StudentDetailActivity.class);
                        break;
                    case R.id.item_mine_student_layout:
                        //跳到聊天界面
                        startActivitys(ConversationActivity.class);
                        break;
                }
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
                mPaidTv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.student_title_check_color));
                break;
            case R.id.unpaid_layout:
                mUnpaidTv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.student_title_check_color));
                break;
        }
    }

    private void initTitleColor() {
        mAllTv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.student_title_un_check_color));
        mPaidTv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.student_title_un_check_color));
        mUnpaidTv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.student_title_un_check_color));
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
