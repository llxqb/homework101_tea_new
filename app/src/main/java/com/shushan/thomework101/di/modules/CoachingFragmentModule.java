package com.shushan.thomework101.di.modules;


import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.ui.fragment.student.CoachingFragmentControl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class CoachingFragmentModule {

    private CoachingFragmentControl.CoachingFragmentView mCoachingFragmentView;

    public CoachingFragmentModule(LoadDataView view) {
        if (view instanceof CoachingFragmentControl.CoachingFragmentView) {
            mCoachingFragmentView = (CoachingFragmentControl.CoachingFragmentView) view;
        }
    }

    /**
     * 与 FourFragment    @Inject
     FourFragmentControl.FourFragmentPresenter mPresenter; mineTeacherFragmentPresenter
     对应起来
     */
    @Provides
    @PerActivity
    CoachingFragmentControl.CoachingFragmentView coachingFragmentView() {
        return this.mCoachingFragmentView;
    }


}
