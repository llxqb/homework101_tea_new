package com.shushan.thomework101.mvp.ui.activity.mine;


import com.shushan.thomework101.entity.request.VersionUpdateRequest;
import com.shushan.thomework101.entity.response.VersionUpdateResponse;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class SettingControl {
    public interface SettingView extends LoadDataView {
        void getVersionUpdateSuccess(VersionUpdateResponse versionUpdateResponse);
    }

    public interface PresenterSetting extends Presenter<SettingView> {
        /**
         * 检查版本更新
         */
        void onRequestVersionUpdate(VersionUpdateRequest versionUpdateRequest);
    }

}
