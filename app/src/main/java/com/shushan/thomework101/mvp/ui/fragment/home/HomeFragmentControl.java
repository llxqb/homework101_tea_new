package com.shushan.thomework101.mvp.ui.fragment.home;


import com.shushan.thomework101.entity.request.HomeRequest;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.response.HomeResponse;
import com.shushan.thomework101.entity.response.UnReadNewsResponse;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class HomeFragmentControl {
    public interface HomeView extends LoadDataView {
        void getHomeInfoSuccess(HomeResponse homeResponse);

        void getUnReadInfoSuccess(UnReadNewsResponse unReadNewsResponse);

        void getHomeInfoFail();

    }

    public interface homeFragmentPresenter extends Presenter<HomeView> {
        /**
         * 请求homeFragment list 数据
         */
        void onRequestHomeInfo(HomeRequest homeRequest);

        /**
         * 查看是否有未读消息
         */
        void onRequestUnReadInfo(TokenRequest tokenRequest);
    }


}
