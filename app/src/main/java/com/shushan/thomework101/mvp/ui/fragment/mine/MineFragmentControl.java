package com.shushan.thomework101.mvp.ui.fragment.mine;


import com.shushan.thomework101.entity.request.HomeRequest;
import com.shushan.thomework101.entity.response.HomeResponse;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class MineFragmentControl {
    public interface MineView extends LoadDataView {
        void getHomeInfoSuccess(HomeResponse homeResponse);

    }

    public interface MineFragmentPresenter extends Presenter<MineView> {

        /**
         * 请求homeFragment list 数据
         * 我的数据也用home 接口
         */
        void onRequestHomeInfo(HomeRequest homeRequest);

    }


}
