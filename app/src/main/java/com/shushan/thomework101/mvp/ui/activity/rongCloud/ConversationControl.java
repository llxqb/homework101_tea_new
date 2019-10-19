package com.shushan.thomework101.mvp.ui.activity.rongCloud;


import com.shushan.thomework101.entity.request.FeedbackIdRequest;
import com.shushan.thomework101.entity.request.UserInfoByRidRequest;
import com.shushan.thomework101.entity.response.FeedbackIdResponse;
import com.shushan.thomework101.entity.response.UserInfoByRidResponse;
import com.shushan.thomework101.mvp.presenter.LoadDataView;
import com.shushan.thomework101.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class ConversationControl {
    public interface ConversationView extends LoadDataView {
        void getUserInfoByRidSuccess(UserInfoByRidResponse userInfoByRidResponse);
        void getFeedBackIdSuccess(FeedbackIdResponse feedbackIdResponse);
    }

    public interface PresenterConversation extends Presenter<ConversationView> {
        /**
         * 根据融云第三方id获取用户头像和昵称
         */
        void onRequestUserInfoByRid(UserInfoByRidRequest userInfoByRidRequest);
        /**
         * 学生结束辅导，老师去反馈，查询反馈id
         * 根据学生id查询最后一次辅导记录id
         */
        void onRequestFeedBackId(FeedbackIdRequest feedbackIdRequest);
    }

}
