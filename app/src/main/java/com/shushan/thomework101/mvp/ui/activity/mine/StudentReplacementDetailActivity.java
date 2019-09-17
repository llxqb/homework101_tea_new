package com.shushan.thomework101.mvp.ui.activity.mine;

import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerStudentReplaceDetailComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.StudentReplaceDetailModule;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

/**
 * 学生更换明细
 */
public class StudentReplacementDetailActivity extends BaseActivity implements StudentReplaceDetailControl.StudentReplaceDetailView{

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_student_replacement_detail);
        initInjectData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    private void initInjectData() {
        DaggerStudentReplaceDetailComponent.builder().appComponent(getAppComponent())
                .studentReplaceDetailModule(new StudentReplaceDetailModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }

}
