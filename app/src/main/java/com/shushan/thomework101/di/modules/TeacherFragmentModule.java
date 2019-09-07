package com.shushan.thomework101.di.modules;



import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.ui.fragment.TeacherFragmentControl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class TeacherFragmentModule {

    private TeacherFragmentControl.TeacherView mTeacherFragmentView;

    public TeacherFragmentModule(LoadDataView view) {
        if (view instanceof TeacherFragmentControl.TeacherView) {
            mTeacherFragmentView = (TeacherFragmentControl.TeacherView) view;
        }
    }

    /**
     * 与 FourFragment    @Inject
     * FourFragmentControl.FourFragmentPresenter mPresenter; homeFragmentPresenter
     * 对应起来
     */
    @Provides
    @PerActivity
    TeacherFragmentControl.TeacherView teacherFragmentView() {
        return this.mTeacherFragmentView;
    }


}
