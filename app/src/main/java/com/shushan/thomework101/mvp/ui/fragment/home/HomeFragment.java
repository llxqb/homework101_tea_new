package com.shushan.thomework101.mvp.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerHomeFragmentComponent;
import com.shushan.thomework101.di.modules.HomeFragmentModule;
import com.shushan.thomework101.di.modules.MainModule;
import com.shushan.thomework101.entity.response.HomeIncomeResponse;
import com.shushan.thomework101.entity.response.UnSuccessfulStudentResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.activity.personalInfo.UploadCardActivity;
import com.shushan.thomework101.mvp.ui.activity.personalInfo.UploadVideoActivity;
import com.shushan.thomework101.mvp.ui.adapter.HomeIncomeAdapter;
import com.shushan.thomework101.mvp.ui.adapter.HomeUnsuccessfulStudentAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MessageFragment
 * 消息
 */

public class HomeFragment extends BaseFragment implements HomeFragmentControl.HomeView {

    //认证流程 未认证
    @BindView(R.id.not_certified_layout)
    LinearLayout mNotCertifiedLayout;
    @BindView(R.id.circle_iv)
    ImageView mCircleIv;
    @BindView(R.id.verify_state_tv)
    TextView mVerifyStateTv;
    @BindView(R.id.go_complete_tv)
    TextView mGoCompleteTv;
    @BindView(R.id.upload_information_rl)
    RelativeLayout mUploadInformationRl;
    @BindView(R.id.pre_job_training_rl)
    RelativeLayout mPreJobTrainingRl;
    @BindView(R.id.complete_material_rl)
    RelativeLayout mCompleteMaterialRl;
    @BindView(R.id.registration_complete_rl)
    RelativeLayout mRegistrationCompleteRl;
    //认证流程 已认证
    @BindView(R.id.verified_layout)
    ConstraintLayout mVerifiedLayout;
    @BindView(R.id.teacher_subject_name)
    TextView mTeacherSubjectName;
    @BindView(R.id.teacher_avatar_iv)
    ImageView mTeacherAvatarIv;
    @BindView(R.id.teacher_name_tv)
    TextView mTeacherNameTv;
    @BindView(R.id.teacher_state_tv)
    TextView mTeacherStateTv;
    @BindView(R.id.teacher_counselling_grade_tv)
    TextView mTeacherCounsellingGradeTv;
    @BindView(R.id.teacher_counseling_time_tv)
    TextView mTeacherCounselingTimeTv;
    //recyclerView
    @BindView(R.id.mine_income_recycler_view)
    RecyclerView mMineIncomeRecyclerView;
    @BindView(R.id.mime_student_recycler_view)
    RecyclerView mMimeStudentRecyclerView;
    @BindView(R.id.unsuccessful_student_recycler_view)
    RecyclerView mUnsuccessfulStudentRecyclerView;
    Unbinder unbinder;

    HomeIncomeAdapter mHomeIncomeAdapter;
    HomeIncomeAdapter mHomeStudentAdapter;
    HomeUnsuccessfulStudentAdapter mHomeUnsuccessfulStudentAdapter;
    List<HomeIncomeResponse> homeIncomeResponseList = new ArrayList<>();
    List<HomeIncomeResponse> homeStudentResponseList = new ArrayList<>();
    List<UnSuccessfulStudentResponse> unSuccessfulStudentResponseList = new ArrayList<>();
    String[] homeIncomeTitle = {"今日提成", "今日课时费", "今日收益"};
    Integer[] homeIncomeBgIcon = {R.mipmap.home_profit_royalty, R.mipmap.home_profit_class_hour, R.mipmap.home_profit_today};
    String[] homeStudentTitle = {"我的学生", "已付费学生", "今日付费学生"};
    Integer[] homeStudentBgIcon = {R.mipmap.home_profit_student, R.mipmap.home_profit_paying_students, R.mipmap.home_profit_paya_today};

    private View mEmptyView;
    User mUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initializeInjector();
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }


    @Override
    public void initView() {
        mUser = mBuProcessor.getUser();
        mEmptyView = LayoutInflater.from(getActivity()).inflate(R.layout.unsuccessful_student_enpty_layout, (ViewGroup) mUnsuccessfulStudentRecyclerView.getParent(), false);
        mHomeIncomeAdapter = new HomeIncomeAdapter(homeIncomeResponseList);
        mMineIncomeRecyclerView.setAdapter(mHomeIncomeAdapter);
        mMineIncomeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mHomeIncomeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showToast("" + position);
            }
        });
        //我的学生
        mHomeStudentAdapter = new HomeIncomeAdapter(homeStudentResponseList);
        mMimeStudentRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mMimeStudentRecyclerView.setAdapter(mHomeStudentAdapter);
        mHomeStudentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showToast("" + position);
            }
        });
        //未成单学生
        mHomeUnsuccessfulStudentAdapter = new HomeUnsuccessfulStudentAdapter(unSuccessfulStudentResponseList);
        mUnsuccessfulStudentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mUnsuccessfulStudentRecyclerView.setAdapter(mHomeUnsuccessfulStudentAdapter);
//        mHomeUnsuccessfulStudentAdapter.setNewData(null);
//        mHomeUnsuccessfulStudentAdapter.setEmptyView(mEmptyView);//如果没有审核通过和没有分配学生显示emptyView
        mHomeUnsuccessfulStudentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showToast("" + position);
            }
        });
    }

    @Override
    public void initData() {
        //判断是否认证 认证流程状态
        mUser.registerState = 1;
        initRegisterState();
        if (mUser.registerState == 1) {
            mUploadInformationRl.setVisibility(View.VISIBLE);
            mCircleIv.setImageResource(R.mipmap.circle1);
        } else if (mUser.registerState == 2) {
            mPreJobTrainingRl.setVisibility(View.VISIBLE);
            mCircleIv.setImageResource(R.mipmap.circle2);
        } else if (mUser.registerState == 3) {
            mCompleteMaterialRl.setVisibility(View.VISIBLE);
            mCircleIv.setImageResource(R.mipmap.circle3);
        } else if (mUser.registerState == 4) {
            mRegistrationCompleteRl.setVisibility(View.VISIBLE);
            mCircleIv.setImageResource(R.mipmap.circle4);
        } else {
            mNotCertifiedLayout.setVisibility(View.GONE);
            mVerifiedLayout.setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < homeIncomeTitle.length; i++) {
            HomeIncomeResponse homeIncomeResponse = new HomeIncomeResponse();
            homeIncomeResponse.title = homeIncomeTitle[i];
            homeIncomeResponse.money = i + 10;
            homeIncomeResponse.bgIcon = homeIncomeBgIcon[i];
            homeIncomeResponseList.add(homeIncomeResponse);
        }
        for (int i = 0; i < homeStudentTitle.length; i++) {
            HomeIncomeResponse homeIncomeResponse = new HomeIncomeResponse();
            homeIncomeResponse.title = homeStudentTitle[i];
            homeIncomeResponse.money = i + 10;
            homeIncomeResponse.bgIcon = homeStudentBgIcon[i];
            homeStudentResponseList.add(homeIncomeResponse);
        }
        for (int i = 0; i < 5; i++) {
            UnSuccessfulStudentResponse unSuccessfulStudentResponse = new UnSuccessfulStudentResponse();
            unSuccessfulStudentResponseList.add(unSuccessfulStudentResponse);
        }
    }


    /**
     * 未注册完成：初始化注册状态
     */
    private void initRegisterState() {
        mVerifiedLayout.setVisibility(View.GONE);
        mNotCertifiedLayout.setVisibility(View.VISIBLE);
        mUploadInformationRl.setVisibility(View.GONE);
        mPreJobTrainingRl.setVisibility(View.GONE);
        mCompleteMaterialRl.setVisibility(View.GONE);
        mRegistrationCompleteRl.setVisibility(View.GONE);
    }

    @OnClick({R.id.system_msg_iv, R.id.customer_service_iv, R.id.verify_state_tv, R.id.go_complete_tv, R.id.pre_job_training_state_tv, R.id.complete_material_tv, R.id.set_coaching_time_tv, R.id.registration_complete_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.system_msg_iv:
                showToast("系统消息");
                break;
            case R.id.customer_service_iv:
                showToast("客服");
                break;
            case R.id.verify_state_tv: //上传身份证、教师资格证
                startActivitys(UploadCardActivity.class);
                break;
            case R.id.go_complete_tv://上传试讲视频
                startActivitys(UploadVideoActivity.class);
                break;
            case R.id.pre_job_training_state_tv:

                break;
            case R.id.complete_material_tv:
                break;
            case R.id.set_coaching_time_tv:
                break;
            case R.id.registration_complete_tv:
                showToast("完成");
                break;
        }
    }

    private void initializeInjector() {
        DaggerHomeFragmentComponent.builder().appComponent(((HomeworkApplication) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
                .mainModule(new MainModule((AppCompatActivity) getActivity()))
                .homeFragmentModule(new HomeFragmentModule(this))
                .build().inject(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
