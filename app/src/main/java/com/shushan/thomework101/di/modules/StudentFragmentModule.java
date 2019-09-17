package com.shushan.thomework101.di.modules;



import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.ui.fragment.student.StudentFragmentControl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class StudentFragmentModule {

    private StudentFragmentControl.StudentView mStudentFragmentView;

    public StudentFragmentModule(LoadDataView view) {
        if (view instanceof StudentFragmentControl.StudentView) {
            mStudentFragmentView = (StudentFragmentControl.StudentView) view;
        }
    }

    /**
     * 与 FourFragment    @Inject
     * FourFragmentControl.FourFragmentPresenter mPresenter; homeFragmentPresenter
     * 对应起来
     */
    @Provides
    @PerActivity
    StudentFragmentControl.StudentView studentFragmentView() {
        return this.mStudentFragmentView;
    }


}
