package com.shushan.thomework101.di.modules;


import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.ui.fragment.student.FeedbackFragmentControl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class FeedbackFragmentModule {

    private FeedbackFragmentControl.FeedbackFragmentView mFeedbackFragmentView;

    public FeedbackFragmentModule(LoadDataView view) {
        if (view instanceof FeedbackFragmentControl.FeedbackFragmentView) {
            mFeedbackFragmentView = (FeedbackFragmentControl.FeedbackFragmentView) view;
        }
    }

    /**
     * 与 FourFragment    @Inject
     FourFragmentControl.FourFragmentPresenter mPresenter; mineTeacherFragmentPresenter
     对应起来
     */
    @Provides
    @PerActivity
    FeedbackFragmentControl.FeedbackFragmentView feedbackFragmentView() {
        return this.mFeedbackFragmentView;
    }


}
