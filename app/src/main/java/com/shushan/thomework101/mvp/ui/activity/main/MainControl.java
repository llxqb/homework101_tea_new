package com.shushan.thomework101.mvp.ui.activity.main;


import com.shushan.thomework101.entity.request.DeviceInfoRequest;
import com.shushan.thomework101.entity.request.VersionUpdateRequest;
import com.shushan.thomework101.entity.response.VersionUpdateResponse;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class MainControl {
    public interface MainView extends LoadDataView {
        void getVersionUpdateSuccess(VersionUpdateResponse versionUpdateResponse);
    }

    public interface PresenterMain extends Presenter<MainView> {
        /**
         * 检查版本更新
         */
        void onRequestVersionUpdate(VersionUpdateRequest versionUpdateRequest);

        /**
         * 上传设备信息
         */
        void uploadDeviceInfo(DeviceInfoRequest deviceInfoRequest);


    }

}
