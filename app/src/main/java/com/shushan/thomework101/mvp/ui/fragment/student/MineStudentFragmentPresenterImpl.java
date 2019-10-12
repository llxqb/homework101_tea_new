package com.shushan.thomework101.mvp.ui.fragment.student;

import android.content.Context;

import com.google.gson.Gson;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.MineStudentListRequest;
import com.shushan.thomework101.entity.response.MineStudentResponse;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.MainModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class MineStudentFragmentPresenterImpl implements MineStudentFragmentControl.MineStudentFragmentPresenter {

    private MineStudentFragmentControl.MineStudentFragmentView mMineStudentFragmentView;
    private final MainModel mMainModel;
    private final Context mContext;

    @Inject
    public MineStudentFragmentPresenterImpl(Context context, MainModel model, MineStudentFragmentControl.MineStudentFragmentView MineStudentFragmentView) {
        mContext = context;
        mMainModel = model;
        mMineStudentFragmentView = MineStudentFragmentView;
    }

    /**
     * 请求我的学生列表
     */
    @Override
    public void onRequestMineStudentInfo(MineStudentListRequest mineStudentListRequest) {
        mMineStudentFragmentView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMainModel.onRequestMineStudentInfo(mineStudentListRequest).compose(mMineStudentFragmentView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestMineStudentInfoSuccess, throwable -> mMineStudentFragmentView.showErrMessage(throwable),
                        () -> mMineStudentFragmentView.dismissLoading());
        mMineStudentFragmentView.addSubscription(disposable);
    }

    /**
     * 请求我的学生列表 数据成功
     */
    private void requestMineStudentInfoSuccess(ResponseData responseData) {
        mMineStudentFragmentView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            MineStudentResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), MineStudentResponse.class);
            mMineStudentFragmentView.getMineStudentInfoSuccess(response);
        } else {
            mMineStudentFragmentView.showToast(responseData.errorMsg);
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mMineStudentFragmentView = null;
    }


}
