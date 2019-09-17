package com.shushan.thomework101.di.modules;


import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.ui.fragment.student.MineStudentFragmentControl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class MineStudentFragmentModule {

    private MineStudentFragmentControl.MineStudentFragmentView mMineStudentFragmentView;

    public MineStudentFragmentModule(LoadDataView view) {
        if (view instanceof MineStudentFragmentControl.MineStudentFragmentView) {
            mMineStudentFragmentView = (MineStudentFragmentControl.MineStudentFragmentView) view;
        }
    }

    /**
     * 与 FourFragment    @Inject
     FourFragmentControl.FourFragmentPresenter mPresenter; mineTeacherFragmentPresenter
     对应起来
     */
    @Provides
    @PerActivity
    MineStudentFragmentControl.MineStudentFragmentView MineStudentFragmentView() {
        return this.mMineStudentFragmentView;
    }


}
