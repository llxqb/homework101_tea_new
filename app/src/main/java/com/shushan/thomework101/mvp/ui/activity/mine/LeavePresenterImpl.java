package com.shushan.thomework101.mvp.ui.activity.mine;

import android.content.Context;

import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.LeaveRequest;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.MineModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2019/09/17.
 */

public class LeavePresenterImpl implements LeaveControl.PresenterLeave {

    private LeaveControl.LeaveView mLeaveView;
    private final MineModel mMineModel;
    private final Context mContext;

    @Inject
    public LeavePresenterImpl(Context context, MineModel model, LeaveControl.LeaveView LeaveView) {
        mContext = context;
        mMineModel = model;
        mLeaveView = LeaveView;
    }


    /**
     * 请假
     */
    @Override
    public void onRequestLeave(LeaveRequest leaveRequest) {
        mLeaveView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.onRequestLeave(leaveRequest).compose(mLeaveView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestLeaveSuccess, throwable -> mLeaveView.showErrMessage(throwable),
                        () -> mLeaveView.dismissLoading());
        mLeaveView.addSubscription(disposable);
    }


    private void requestLeaveSuccess(ResponseData responseData) {
        mLeaveView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            mLeaveView.getLeaveSuccess();
        } else {
            mLeaveView.showToast(responseData.errorMsg);
        }
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
