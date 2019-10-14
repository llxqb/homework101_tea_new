package com.shushan.thomework101.mvp.ui.activity.mine;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerStudentReplaceDetailComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.StudentReplaceDetailModule;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.ui.fragment.studentChange.StudentChangeAllRecordFragment;
import com.shushan.thomework101.mvp.ui.fragment.studentChange.StudentChangeInRecordFragment;
import com.shushan.thomework101.mvp.ui.fragment.studentChange.StudentChangeOutRecordFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的学生变动
 */
public class StudentReplacementDetailActivity extends BaseActivity implements StudentReplaceDetailControl.StudentReplaceDetailView {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    String[] titles = {"全部", "转入", "转出"};

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_student_replacement_detail);
        initInjectData();
    }

    @Override
    public void initView() {
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void initData() {
        mCommonTitleTv.setText("我的学生变动");
    }

    private void onRequestStudentChange() {

    }

    @OnClick(R.id.common_left_iv)
    public void onViewClicked() {
        finish();
    }


    private class MyPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<Fragment>();

        MyPageAdapter(FragmentManager fm) {
            super(fm);
            StudentChangeAllRecordFragment studentChangeAllRecordFragment = new StudentChangeAllRecordFragment();
            StudentChangeInRecordFragment studentChangeInRecordFragment = new StudentChangeInRecordFragment();
            StudentChangeOutRecordFragment studentChangeOutRecordFragment = new StudentChangeOutRecordFragment();
            fragments.add(studentChangeAllRecordFragment);
            fragments.add(studentChangeInRecordFragment);
            fragments.add(studentChangeOutRecordFragment);
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


    private void initInjectData() {
        DaggerStudentReplaceDetailComponent.builder().appComponent(getAppComponent())
                .studentReplaceDetailModule(new StudentReplaceDetailModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }
}
