package com.shushan.thomework101.mvp.ui.fragment.student;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidkun.xtablayout.XTabLayout;
import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerStudentFragmentComponent;
import com.shushan.thomework101.di.modules.MainModule;
import com.shushan.thomework101.di.modules.StudentFragmentModule;
import com.shushan.thomework101.entity.constants.Constant;
import com.shushan.thomework101.entity.constants.ServerConstant;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.activity.rongCloud.ConversationListFragment;
import com.shushan.thomework101.mvp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.CSCustomServiceInfo;

/**
 * TeacherFragment
 * 学生
 */

public class StudentFragment extends BaseFragment implements StudentFragmentControl.StudentView {

    @BindView(R.id.xTabLayout)
    XTabLayout mXTabLayout;
    @BindView(R.id.customer_service_iv)
    ImageView mCustomerServiceIv;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    Unbinder unbinder;
    private User mUser;
    private String[] titles = new String[]{"辅导", "我的学生", "今日反馈"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, container, false);
        initializeInjector();
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }


    @Override
    public void initView() {
        mUser = mBuProcessor.getUser();
        mViewpager.setOffscreenPageLimit(3);
        mViewpager.setAdapter(new MyPageAdapter(getChildFragmentManager()));
        mXTabLayout.setupWithViewPager(mViewpager);
    }

    @Override
    public void initData() {
    }

    @OnClick(R.id.customer_service_iv)
    public void onViewClicked() {
        contactCustomer();
    }

    /**
     * 联系客服
     */
    private void contactCustomer() {
        //进入客服
        //首先需要构造使用客服者的用户信息
        CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
        CSCustomServiceInfo csInfo = csBuilder.nickName(mUser.name)
                .name(mUser.name)
                .referrer(Constant.CUSTOMER_NUM)
                .build();
        RongIM.getInstance().startCustomerServiceChat(getActivity(), ServerConstant.RY_CUSTOMER_ID, "在线客服", csInfo);
        mSharePreferenceUtil.setData("chatType", 1);//在线客服
    }

    private class MyPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<Fragment>();

        MyPageAdapter(FragmentManager fm) {
            super(fm);
            ConversationListFragment conversationListFragment = new ConversationListFragment();
            MineStudentFragment mineTeacherFragment = new MineStudentFragment();
            FeedbackFragment feedbackFragment = new FeedbackFragment();
            fragments.add(conversationListFragment);
            fragments.add(mineTeacherFragment);
            fragments.add(feedbackFragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    private void initializeInjector() {
        DaggerStudentFragmentComponent.builder().appComponent(((HomeworkApplication) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
                .mainModule(new MainModule((AppCompatActivity) getActivity()))
                .studentFragmentModule(new StudentFragmentModule(this))
                .build().inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
