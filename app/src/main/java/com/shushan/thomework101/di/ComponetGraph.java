package com.shushan.thomework101.di;


import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.mvp.ui.activity.guide.SubjectSelectActivity;
import com.shushan.thomework101.mvp.ui.activity.logout.LogoutSuccessActivity;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.ui.base.BaseFragment;

/**
 * Created by wxl on 16/3/30.
 */
public interface ComponetGraph {

    void inject(HomeworkApplication application);

    void inject(BaseActivity baseActivity);

    void inject(BaseFragment baseFragment);

    void inject(SubjectSelectActivity subjectSelectActivity);//选择科目

    void inject(LogoutSuccessActivity logoutSuccessActivity);//注销账号成功

}
