package com.shushan.thomework101.mvp.ui.fragment.mine;


import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class MineFragmentControl {
    public interface MineView extends LoadDataView {
//        void getMineStudentInfoSuccess(MineStudentResponse response);

    }

    public interface MineFragmentPresenter extends Presenter<MineView> {
//        /**
//         * 请求我的学生列表
//         */
//        void onRequestMineStudentInfo(MineStudentListRequest mineStudentListRequest);

    }


}
