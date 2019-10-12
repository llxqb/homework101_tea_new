package com.shushan.thomework101.mvp.ui.activity.mine;


import com.shushan.thomework101.entity.request.LeaveRequest;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 */

public class LeaveControl {
    public interface LeaveView extends LoadDataView {
        void getLeaveSuccess();
    }

    public interface PresenterLeave extends Presenter<LeaveView> {

        /**
         * 请假
         */
        void onRequestLeave(LeaveRequest leaveRequest);
    }

}
