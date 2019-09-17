package com.shushan.thomework101.di.modules;



import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.ui.fragment.mine.MineFragmentControl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class MineFragmentModule {

    private MineFragmentControl.MineView mMineFragmentView;

    public MineFragmentModule(LoadDataView view) {
        if (view instanceof MineFragmentControl.MineView) {
            mMineFragmentView = (MineFragmentControl.MineView) view;
        }
    }

    /**
     * 与 FourFragment    @Inject
     * FourFragmentControl.FourFragmentPresenter mPresenter; homeFragmentPresenter
     * 对应起来
     */
    @Provides
    @PerActivity
    MineFragmentControl.MineView mineFragmentView() {
        return this.mMineFragmentView;
    }


}
