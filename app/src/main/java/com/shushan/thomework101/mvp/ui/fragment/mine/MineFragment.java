package com.shushan.thomework101.mvp.ui.fragment.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerMineFragmentComponent;
import com.shushan.thomework101.di.modules.MainModule;
import com.shushan.thomework101.di.modules.MineFragmentModule;
import com.shushan.thomework101.entity.response.MineFunctionResponse;
import com.shushan.thomework101.mvp.ui.activity.bank.WalletActivity;
import com.shushan.thomework101.mvp.ui.activity.mine.CustomerServiceActivity;
import com.shushan.thomework101.mvp.ui.activity.mine.LeaveActivity;
import com.shushan.thomework101.mvp.ui.activity.mine.MineFeedbackActivity;
import com.shushan.thomework101.mvp.ui.activity.mine.SettingActivity;
import com.shushan.thomework101.mvp.ui.activity.mine.StudentReplacementDetailActivity;
import com.shushan.thomework101.mvp.ui.activity.personalInfo.EditPersonalInfoActivity;
import com.shushan.thomework101.mvp.ui.adapter.MineFunctionAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MimeFragment
 * 我的
 */

public class MineFragment extends BaseFragment implements MineFragmentControl.MineView {

    @BindView(R.id.avatar_iv)
    ImageView mAvatarIv;
    @BindView(R.id.username_tv)
    TextView mUsernameTv;
    @BindView(R.id.counselling_grade_tv)
    TextView mCounsellingGradeTv;
    @BindView(R.id.teacher_state_tv)
    TextView mTeacherStateTv;
    @BindView(R.id.working_day_tv)
    TextView mWorkingDayTv;
    @BindView(R.id.off_day_tv)
    TextView mOffDayTv;
    @BindView(R.id.earned_money_tv)
    TextView mEarnedMoneyTv;
    @BindView(R.id.estimated_money_tv)
    TextView mEstimatedMoneyTv;
    @BindView(R.id.mine_recycler_view)
    RecyclerView mMineRecyclerView;
    Unbinder unbinder;
    MineFunctionAdapter mMineFunctionAdapter;
    List<MineFunctionResponse> mineFunctionResponseList = new ArrayList<>();
    Integer[] mineFunctionIcon = {R.mipmap.my_tutor_feedback, R.mipmap.my_student_changes, R.mipmap.my_ervice_center, R.mipmap.my_rules, R.mipmap.my_operation};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mime, container, false);
        unbinder = ButterKnife.bind(this, view);
        initializeInjector();
        initView();
        initData();
        return view;
    }


    @Override
    public void initView() {
        mMineFunctionAdapter = new MineFunctionAdapter(mineFunctionResponseList);
        mMineRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMineRecyclerView.setAdapter(mMineFunctionAdapter);

        mMineRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0://辅导反馈
                        startActivitys(MineFeedbackActivity.class);
                        break;
                    case 1://我的学生变动
                        startActivitys(StudentReplacementDetailActivity.class);
                        break;
                    case 2://客服中心
                        startActivitys(CustomerServiceActivity.class);
                        break;
                    case 3://规章制度
                        break;
                    case 4://操作介绍
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        String[] functionName = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.mine_function_text);
        for (int i = 0; i < functionName.length; i++) {
            MineFunctionResponse mineFunctionResponse = new MineFunctionResponse();
            mineFunctionResponse.icon = mineFunctionIcon[i];
            mineFunctionResponse.name = functionName[i];
            mineFunctionResponseList.add(mineFunctionResponse);
        }
    }

    @OnClick({R.id.setting_icon_iv,R.id.avatar_iv,R.id.teacher_state_tv, R.id.wallet_tv, R.id.earned_income_layout, R.id.estimated_income_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_icon_iv:
                startActivitys(SettingActivity.class);
                break;
            case R.id.avatar_iv://去我的资料
                EditPersonalInfoActivity.start(getActivity(),2);
                break;
            case R.id.teacher_state_tv://去请假
                startActivitys(LeaveActivity.class);
                break;
            case R.id.wallet_tv:
                startActivitys(WalletActivity.class);
                break;
            case R.id.earned_income_layout:
                break;
            case R.id.estimated_income_layout:
                break;
        }
    }


    private void initializeInjector() {
        DaggerMineFragmentComponent.builder().appComponent(((HomeworkApplication) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
                .mainModule(new MainModule((AppCompatActivity) getActivity()))
                .mineFragmentModule(new MineFragmentModule(this))
                .build().inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
