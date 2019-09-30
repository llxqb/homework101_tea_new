package com.shushan.thomework101.di.modules;


import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.ui.fragment.studentChange.StudentChangeRecordControl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class StudentChangeRecordFragmentModule {

    private StudentChangeRecordControl.StudentChangeRecordView mStudentChangeRecordFragmentView;

    public StudentChangeRecordFragmentModule(LoadDataView view) {
        if (view instanceof StudentChangeRecordControl.StudentChangeRecordView) {
            mStudentChangeRecordFragmentView = (StudentChangeRecordControl.StudentChangeRecordView) view;
        }
    }

    /**
     * 与 FourFragment    @Inject
     * FourFragmentControl.FourFragmentPresenter mPresenter; homeFragmentPresenter
     * 对应起来
     */
    @Provides
    @PerActivity
    StudentChangeRecordControl.StudentChangeRecordView studentChangeRecordFragmentView() {
        return this.mStudentChangeRecordFragmentView;
    }


}
