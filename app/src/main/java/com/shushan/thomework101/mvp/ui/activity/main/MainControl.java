package com.shushan.thomework101.mvp.ui.activity.main;


import com.shushan.thomework101.entity.request.DeviceInfoRequest;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class MainControl {
    public interface MainView extends LoadDataView {

        // void homeUserInfoSuccess(HomeUserInfoResponse homeUserInfoResponse);
//        void messageIdSuccess(MessageIdResponse messageIdResponse);
    }

    public interface PresenterMain extends Presenter<MainView> {


        /**
         * 上传设备信息
         */
        void uploadDeviceInfo(DeviceInfoRequest deviceInfoRequest);

//        /**
//         * 根据融云第三方id获取用户头像和昵称
//         */
//        UserInfo onRequestUserInfoByRid(UserInfoByRidRequest userInfoByRidRequest);
//
//        /**
//         * 查看用户嗨豆查看私密照片message_id
//         */
//        void onRequestMessageId(TokenRequest tokenRequest);
    }

}
