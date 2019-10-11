package com.shushan.thomework101.mvp.ui.activity.personalInfo;

import android.content.Context;

import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.SetCounsellingTimeRequest;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.MineModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2019/09/17.
 */

public class CoachingTimePresenterImpl implements CoachingTimeControl.PresenterCoachingTime {

    private CoachingTimeControl.CoachingTimeView mCoachingTimeView;
    private final MineModel mMineModel;
    private final Context mContext;

    @Inject
    public CoachingTimePresenterImpl(Context context, MineModel model, CoachingTimeControl.CoachingTimeView CoachingTimeView) {
        mContext = context;
        mMineModel = model;
        mCoachingTimeView = CoachingTimeView;
    }


    /**
     * 设置辅导时间
     */
    @Override
    public void setCounsellingTime(SetCounsellingTimeRequest request) {
        mCoachingTimeView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.setCounsellingTime(request).compose(mCoachingTimeView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::setCounsellingTimeSuccess, throwable -> mCoachingTimeView.showErrMessage(throwable),
                        () -> mCoachingTimeView.dismissLoading());
        mCoachingTimeView.addSubscription(disposable);
    }

    /**
     * 设置辅导时间成功
     */
    private void setCounsellingTimeSuccess(ResponseData responseData) {
        mCoachingTimeView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            mCoachingTimeView.getCounsellingTimeSuccess();
        } else {
            mCoachingTimeView.showToast(responseData.errorMsg);
        }
    }

    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
