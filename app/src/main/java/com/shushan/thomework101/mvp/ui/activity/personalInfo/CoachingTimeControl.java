package com.shushan.thomework101.mvp.ui.activity.personalInfo;


import com.shushan.thomework101.entity.request.SetCounsellingTimeRequest;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 */

public class CoachingTimeControl {
    public interface CoachingTimeView extends LoadDataView {
        void getCounsellingTimeSuccess();
    }

    public interface PresenterCoachingTime extends Presenter<CoachingTimeView> {

        /**
         * 设置辅导时间
         */
        void setCounsellingTime(SetCounsellingTimeRequest setCounsellingTimeRequest);
    }

}
