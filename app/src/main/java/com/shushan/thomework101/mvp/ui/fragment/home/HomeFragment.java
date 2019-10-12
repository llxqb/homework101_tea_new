package com.shushan.thomework101.mvp.ui.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerHomeFragmentComponent;
import com.shushan.thomework101.di.modules.HomeFragmentModule;
import com.shushan.thomework101.di.modules.MainModule;
import com.shushan.thomework101.entity.constants.ActivityConstant;
import com.shushan.thomework101.entity.constants.Constant;
import com.shushan.thomework101.entity.request.HomeRequest;
import com.shushan.thomework101.entity.response.HomeIncomeResponse;
import com.shushan.thomework101.entity.response.HomeResponse;
import com.shushan.thomework101.entity.response.UnSuccessfulStudentResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.help.DialogFactory;
import com.shushan.thomework101.mvp.ui.activity.main.SystemMsgActivity;
import com.shushan.thomework101.mvp.ui.activity.mine.CustomerServiceActivity;
import com.shushan.thomework101.mvp.ui.activity.personalInfo.EditPersonalInfoActivity;
import com.shushan.thomework101.mvp.ui.activity.personalInfo.SetCounsellingTimeActivity;
import com.shushan.thomework101.mvp.ui.activity.personalInfo.UploadCardActivity;
import com.shushan.thomework101.mvp.ui.activity.personalInfo.UploadVideoActivity;
import com.shushan.thomework101.mvp.ui.adapter.HomeIncomeAdapter;
import com.shushan.thomework101.mvp.ui.adapter.HomeUnsuccessfulStudentAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseFragment;
import com.shushan.thomework101.mvp.ui.dialog.CommonDialog;
import com.shushan.thomework101.mvp.utils.HomeUtil;
import com.shushan.thomework101.mvp.utils.LoginUtils;
import com.shushan.thomework101.mvp.utils.UserUtil;
import com.shushan.thomework101.mvp.views.CircleImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MessageFragment
 * 消息
 */

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, HomeFragmentControl.HomeView, CommonDialog.CommonDialogListener {

    @BindView(R.id.swipe_ly)
    SwipeRefreshLayout mSwipeLy;
    //认证流程 未认证
    @BindView(R.id.not_certified_layout)
    LinearLayout mNotCertifiedLayout;
    @BindView(R.id.circle_iv)
    ImageView mCircleIv;
    @BindView(R.id.verify_state_tv)
    TextView mVerifyStateTv;
    @BindView(R.id.go_complete_tv)
    TextView mGoCompleteTv;
    @BindView(R.id.complete_material_tv)
    TextView mCompleteMaterialTv;
    @BindView(R.id.upload_video_hint_tv)
    TextView mUploadVideoHintTv;
    @BindView(R.id.set_coaching_time_tv)
    TextView mSetCoachingTimeTv;
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
    CircleImageView mTeacherAvatarIv;
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
    String[] homeIncomeTitle = {"昨日提成", "昨日课时费", "昨日收益"};
    Integer[] homeIncomeBgIcon = {R.mipmap.home_profit_royalty, R.mipmap.home_profit_class_hour, R.mipmap.home_profit_today};
    String[] homeStudentTitle = {"我的学生", "已付费学生", "今日付费学生"};
    Integer[] homeStudentBgIcon = {R.mipmap.home_profit_student, R.mipmap.home_profit_paying_students, R.mipmap.home_profit_paya_today};

    private View mEmptyView;
    User mUser;
    HomeResponse.UserBean userBean;
    @Inject
    HomeFragmentControl.homeFragmentPresenter mPresenter;

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
    public void onReceivePro(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(ActivityConstant.UPDATE_USER_CHECK_INFO)) {
            onRequestHomeInfo();
        }
        super.onReceivePro(context, intent);
    }

    @Override
    public void addFilter() {
        super.addFilter();
        mFilter.addAction(ActivityConstant.UPDATE_USER_CHECK_INFO);
    }


    @Override
    public void initView() {
        mUser = mBuProcessor.getUser();
        mSwipeLy.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeLy.setOnRefreshListener(this);
        mEmptyView = LayoutInflater.from(getActivity()).inflate(R.layout.unsuccessful_student_enpty_layout, (ViewGroup) mUnsuccessfulStudentRecyclerView.getParent(), false);
        mHomeIncomeAdapter = new HomeIncomeAdapter(homeIncomeResponseList);
        mMineIncomeRecyclerView.setAdapter(mHomeIncomeAdapter);
        mMineIncomeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        //我的学生
        mHomeStudentAdapter = new HomeIncomeAdapter(homeStudentResponseList);
        mMimeStudentRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mMimeStudentRecyclerView.setAdapter(mHomeStudentAdapter);
        //未成单学生
        mHomeUnsuccessfulStudentAdapter = new HomeUnsuccessfulStudentAdapter(unSuccessfulStudentResponseList);
        mUnsuccessfulStudentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mUnsuccessfulStudentRecyclerView.setAdapter(mHomeUnsuccessfulStudentAdapter);
//        mHomeUnsuccessfulStudentAdapter.setNewData(null);
//        mHomeUnsuccessfulStudentAdapter.setEmptyView(mEmptyView);//如果没有审核通过和没有分配学生显示emptyView
    }

    @Override
    public void initData() {
        onRequestHomeInfo();
        for (int i = 0; i < 5; i++) {
            UnSuccessfulStudentResponse unSuccessfulStudentResponse = new UnSuccessfulStudentResponse();
            unSuccessfulStudentResponseList.add(unSuccessfulStudentResponse);
        }
    }

    @OnClick({R.id.system_msg_iv, R.id.customer_service_iv, R.id.verify_state_tv, R.id.go_complete_tv, R.id.pre_job_training_state_tv, R.id.complete_material_tv, R.id.set_coaching_time_tv, R.id.registration_complete_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.system_msg_iv:
                startActivitys(SystemMsgActivity.class);
                break;
            case R.id.customer_service_iv:
                startActivitys(CustomerServiceActivity.class);
                break;
            case R.id.verify_state_tv: //上传身份证、教师资格证
                if (userBean.getCard_state() == 0 || userBean.getCard_state() == 3) {
                    startActivitys(UploadCardActivity.class);
                } else if (userBean.getCard_state() == 1) {
                    showUnderReviewDialog();
                }
                break;
            case R.id.go_complete_tv://上传试讲视频
                if (userBean.getVideo_state() == 0 || userBean.getVideo_state() == 3) {
                    startActivitys(UploadVideoActivity.class);
                } else if (userBean.getVideo_state() == 1) {
                    showUnderReviewDialog();
                }
                break;
            case R.id.pre_job_training_state_tv:
                //岗前培训
                HomeUtil.toWechat(getActivity(), this, Constant.CS_WECHAT);
                break;
            case R.id.complete_material_tv:
                //完善个人资料
                if (userBean.getState() == 0 || userBean.getState() == 3) {
                    EditPersonalInfoActivity.start(getActivity(), 1);
                } else if (userBean.getState() == 2) {
                    showUnderReviewDialog();
                }
                break;
            case R.id.set_coaching_time_tv:
                //设置辅导时间
                if (userBean.getGuide_state() == 0 || userBean.getGuide_state() == 3) {
                    startActivitys(SetCounsellingTimeActivity.class);
                } else if (userBean.getGuide_state() == 2) {
                    showUnderReviewDialog();
                }
                break;
            case R.id.registration_complete_tv:
                //已完成
                showToast("完成");
                break;
        }
    }

    /**
     * 请求首页数据
     */
    private void onRequestHomeInfo() {
        HomeRequest homeRequest = new HomeRequest();
        homeRequest.token = mUser.token;
        mPresenter.onRequestHomeInfo(homeRequest);
    }

    @Override
    public void onRefresh() {
        onRequestHomeInfo();
    }

    @Override
    public void getHomeInfoSuccess(HomeResponse homeResponse) {
        if (mSwipeLy.isRefreshing()) {
            mSwipeLy.setRefreshing(false);
        }
        userBean = homeResponse.getUser();
        //更新User
        mUser = LoginUtils.updateLoginUser(userBean, mUser, mBuProcessor);
        Log.e("ddd","mUser"+new Gson().toJson(mUser));
        setCheckProcess();
        setIncomeData(homeResponse.getIncome());
        setMineStudentData(homeResponse.getStudent());
    }

    /**
     * 设置审核流程
     */
    private void setCheckProcess() {
        String title = "上传" + UserUtil.gradeArrayToString(mUser.grades) + "试讲题视频";
        mUploadVideoHintTv.setText(title);
        int checkState = HomeUtil.checkState(userBean.getCard_state(), userBean.getVideo_state(), userBean.getTrain_state(), userBean.getGuide_state(), userBean.getState());
        switch (checkState) {
            case 0:
                //完成了注册全部流程
                showVerifiedState();
                break;
            case 1:
                //未完成了身份证审核和试讲视频
                initRegisterState();
                mUploadInformationRl.setVisibility(View.VISIBLE);
                mCircleIv.setImageResource(R.mipmap.circle1);
                mVerifyStateTv.setText(HomeUtil.stateName(userBean.getCard_state(), Constant.DEFAULT_CHECK_STATE, Constant.DEFAULT_CHECK_STATE, Constant.DEFAULT_CHECK_STATE, Constant.DEFAULT_CHECK_STATE));
                mGoCompleteTv.setText(HomeUtil.stateName(Constant.DEFAULT_CHECK_STATE, userBean.getVideo_state(), Constant.DEFAULT_CHECK_STATE, Constant.DEFAULT_CHECK_STATE, Constant.DEFAULT_CHECK_STATE));
                //0未上传  1待审核   2审核通过   3审核不通过
                if (userBean.getCard_state() == 0 || userBean.getCard_state() == 3) {
                    mVerifyStateTv.setBackgroundResource(R.drawable.gradient_red_bg_5);
                } else {
                    mVerifyStateTv.setBackgroundResource(R.drawable.gradient_round_gray2_bg_5);
                }
                if (userBean.getVideo_state() == 0 || userBean.getVideo_state() == 3) {
                    mGoCompleteTv.setBackgroundResource(R.drawable.gradient_red_bg_5);
                } else {
                    mGoCompleteTv.setBackgroundResource(R.drawable.gradient_round_gray2_bg_5);
                }
                break;
            case 2:
                //未完成了岗前培训
                initRegisterState();
                mPreJobTrainingRl.setVisibility(View.VISIBLE);
                mCircleIv.setImageResource(R.mipmap.circle2);
                break;
            case 3:
                //未完成了完善资料和设置辅导时间
                initRegisterState();
                mCompleteMaterialRl.setVisibility(View.VISIBLE);
                mCircleIv.setImageResource(R.mipmap.circle3);
                mSetCoachingTimeTv.setText(HomeUtil.stateName(Constant.DEFAULT_CHECK_STATE, Constant.DEFAULT_CHECK_STATE, Constant.DEFAULT_CHECK_STATE, userBean.getGuide_state(), Constant.DEFAULT_CHECK_STATE));
                mCompleteMaterialTv.setText(HomeUtil.stateName(Constant.DEFAULT_CHECK_STATE, Constant.DEFAULT_CHECK_STATE, Constant.DEFAULT_CHECK_STATE, Constant.DEFAULT_CHECK_STATE, userBean.getState()));
                if (userBean.getGuide_state() == 0 || userBean.getGuide_state() == 3) {
                    mSetCoachingTimeTv.setBackgroundResource(R.drawable.gradient_red_bg_5);
                } else {
                    mSetCoachingTimeTv.setBackgroundResource(R.drawable.gradient_round_gray2_bg_5);
                }
                if (userBean.getState() == 0 || userBean.getState() == 3) {
                    mCompleteMaterialTv.setBackgroundResource(R.drawable.gradient_red_bg_5);
                } else {
                    mCompleteMaterialTv.setBackgroundResource(R.drawable.gradient_round_gray2_bg_5);
                }
                break;
        }
    }

    /**
     * 设置我的收益数据
     */
    private void setIncomeData(HomeResponse.IncomeBean incomeBean) {
        homeIncomeResponseList.clear();
        for (int i = 0; i < homeIncomeTitle.length; i++) {
            HomeIncomeResponse homeIncomeResponse = new HomeIncomeResponse();
            homeIncomeResponse.title = homeIncomeTitle[i];
            if (i == 0) {
                homeIncomeResponse.money = incomeBean.getToday_push_money();
            } else if (i == 1) {
                homeIncomeResponse.money = incomeBean.getToday_class_fee();
            } else {
                homeIncomeResponse.money = incomeBean.getToday_income();
            }
            homeIncomeResponse.bgIcon = homeIncomeBgIcon[i];
            homeIncomeResponseList.add(homeIncomeResponse);
        }
        mHomeIncomeAdapter.setNewData(homeIncomeResponseList);
    }

    /**
     * 设置我的学生
     */
    private void setMineStudentData(HomeResponse.StudentBean studentBean) {
        homeStudentResponseList.clear();
        for (int i = 0; i < homeStudentTitle.length; i++) {
            HomeIncomeResponse homeIncomeResponse = new HomeIncomeResponse();
            homeIncomeResponse.title = homeStudentTitle[i];
            if (i == 0) {
                homeIncomeResponse.money = studentBean.getAll();
            } else if (i == 1) {
                homeIncomeResponse.money = studentBean.getPay();
            } else {
                homeIncomeResponse.money = studentBean.getToday_pay();
            }
            homeIncomeResponse.bgIcon = homeStudentBgIcon[i];
            homeStudentResponseList.add(homeIncomeResponse);
        }
        mHomeStudentAdapter.setNewData(homeStudentResponseList);
    }

    /**
     * 显示正在审核中dialog
     */
    private void showUnderReviewDialog() {
        DialogFactory.showCommonFragmentDialog(getActivity(), this, "正在等待审核，请审核通过后再操作", "", "", "", Constant.COMMON_DIALOG_STYLE_2);
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


    /**
     * 注册完成：显示已认证状态
     */
    private void showVerifiedState() {
        mVerifiedLayout.setVisibility(View.VISIBLE);
        mNotCertifiedLayout.setVisibility(View.GONE);
        //设置已认证数据
        mTeacherSubjectName.setText(userBean.getSubject());
        mImageLoaderHelper.displayImage(getActivity(), userBean.getCover(), mTeacherAvatarIv, Constant.LOADING_AVATOR);
        mTeacherNameTv.setText(userBean.getName());
        if (userBean.getLeave() == 1) {//1请假中0正常
            mTeacherStateTv.setVisibility(View.VISIBLE);
        } else {
            mTeacherStateTv.setVisibility(View.INVISIBLE);
        }
        String teacherCounsellingGradeValue = "辅导年级：" + UserUtil.gradeArrayToString(userBean.getGrade_id());
        mTeacherCounsellingGradeTv.setText(teacherCounsellingGradeValue);
        HomeResponse.UserBean.GuideTimeBean guideTimeBean = userBean.getGuide_time();
        String teacherCounselingTimeValue = UserUtil.dayArrayToString(guideTimeBean.getWorkday())+" "+guideTimeBean.getWork_time()+"\n"
                +UserUtil.dayArrayToString(guideTimeBean.getOff_day())+" "+guideTimeBean.getOff_time();
        mTeacherCounselingTimeTv.setText(teacherCounselingTimeValue);
    }

    @Override
    public void commonDialogBtnOkListener() {

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
