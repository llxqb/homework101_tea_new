package com.shushan.thomework101.di.modules;



import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.ui.fragment.MimeFragmentControl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class MimeFragmentModule {

    private MimeFragmentControl.MimeView mMimeFragmentView;

    public MimeFragmentModule(LoadDataView view) {
        if (view instanceof MimeFragmentControl.MimeView) {
            mMimeFragmentView = (MimeFragmentControl.MimeView) view;
        }
    }

    /**
     * 与 FourFragment    @Inject
     * FourFragmentControl.FourFragmentPresenter mPresenter; homeFragmentPresenter
     * 对应起来
     */
    @Provides
    @PerActivity
    MimeFragmentControl.MimeView mimeFragmentView() {
        return this.mMimeFragmentView;
    }


}
