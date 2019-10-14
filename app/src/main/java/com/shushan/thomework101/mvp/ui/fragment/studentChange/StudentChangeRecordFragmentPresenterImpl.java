package com.shushan.thomework101.mvp.ui.fragment.studentChange;

import android.content.Context;

import com.google.gson.Gson;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.StudentChangeRequest;
import com.shushan.thomework101.entity.response.StudentChangeRecordResponse;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.MineModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by li.liu on 2019/5/28.
 * HomePresenterImpl
 */

public class StudentChangeRecordFragmentPresenterImpl implements StudentChangeRecordControl.StudentChangeRecordFragmentPresenter {

    private StudentChangeRecordControl.StudentChangeRecordView mStudentChangeRecordView;
    private final MineModel mMineModel;
    private final Context mContext;

    @Inject
    public StudentChangeRecordFragmentPresenterImpl(Context context, MineModel model, StudentChangeRecordControl.StudentChangeRecordView studentChangeRecordView) {
        mContext = context;
        mMineModel = model;
        mStudentChangeRecordView = studentChangeRecordView;
    }

    /**
     * 学生变动
     */
    @Override
    public void onRequestStudentChange(StudentChangeRequest request) {
        mStudentChangeRecordView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.onRequestStudentChange(request).compose(mStudentChangeRecordView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestStudentChangeSuccess, throwable -> mStudentChangeRecordView.showErrMessage(throwable),
                        () -> mStudentChangeRecordView.dismissLoading());
        mStudentChangeRecordView.addSubscription(disposable);
    }

    private void requestStudentChangeSuccess(ResponseData responseData) {
        mStudentChangeRecordView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            StudentChangeRecordResponse response = new Gson().fromJson(responseData.mJsonObject.toString(), StudentChangeRecordResponse.class);
            mStudentChangeRecordView.getStudentChangeSuccess(response);
        } else {
            mStudentChangeRecordView.showToast(responseData.errorMsg);
        }
    }
    
    
    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        mStudentChangeRecordView = null;
    }


}
