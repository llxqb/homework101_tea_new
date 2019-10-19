package com.shushan.thomework101.mvp.ui.activity.main;


import com.shushan.thomework101.entity.request.SystemMsgRequest;
import com.shushan.thomework101.entity.request.TokenRequest;
import com.shushan.thomework101.entity.response.SystemMsgResponse;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2019/09/17.
 */

public class SystemMsgControl {
    public interface SystemMsgView extends LoadDataView {
        void getSystemMsgSuccess(SystemMsgResponse systemMsgResponse);

        void getDeleteMsgSuccess();
    }

    public interface PresenterSystemMsg extends Presenter<SystemMsgView> {

        /**
         * 请求系统消息
         */
        void onRequestSystemMsg(SystemMsgRequest SystemMsgRequest);

        /**
         * 清空消息列表
         */
        void onRequestDeleteMsg(TokenRequest tokenRequest);
    }

}
