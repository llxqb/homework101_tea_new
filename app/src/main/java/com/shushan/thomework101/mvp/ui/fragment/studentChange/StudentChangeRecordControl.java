package com.shushan.thomework101.mvp.ui.fragment.studentChange;


import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/05/28.
 */

public class StudentChangeRecordControl {
    public interface StudentChangeRecordView extends LoadDataView {
//        void getInfoSuccess(HomeFragmentResponse response);

    }

    public interface StudentChangeRecordFragmentPresenter extends Presenter<StudentChangeRecordView> {
        /**
         * 请求homeFragment list 数据
         */
//        void onRequestInfo(HomeFragmentRequest homeFragmentRequest);

    }


}
