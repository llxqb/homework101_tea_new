package com.shushan.thomework101.mvp.ui.fragment;


import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class HomeFragmentControl {
    public interface HomeView extends LoadDataView {
//        void getInfoSuccess(HomeFragmentResponse response);

    }

    public interface homeFragmentPresenter extends Presenter<HomeView> {
        /**
         * 请求homeFragment list 数据
         */
//        void onRequestInfo(HomeFragmentRequest homeFragmentRequest);

    }


}
