package com.shushan.thomework101.mvp.ui.activity.student;

import android.content.Context;

import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.SaveStudentInfoRequest;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.ResponseData;
import com.shushan.thomework101.mvp.model.StudentModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2019/09/17.
 */

public class StudentDetailPresenterImpl implements StudentDetailControl.PresenterStudentDetail {

    private StudentDetailControl.StudentDetailView mStudentDetailView;
    private final StudentModel mStudentModel;
    private final Context mContext;

    @Inject
    public StudentDetailPresenterImpl(Context context, StudentModel model, StudentDetailControl.StudentDetailView studentDetailView) {
        mContext = context;
        mStudentModel = model;
        mStudentDetailView = studentDetailView;
    }

    /**
     * 保存学生信息
     */
    @Override
    public void saveStudentInfo(SaveStudentInfoRequest saveStudentInfoRequest) {
        mStudentDetailView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mStudentModel.saveStudentInfo(saveStudentInfoRequest).compose(mStudentDetailView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::saveStudentInfoSuccess, throwable -> mStudentDetailView.showErrMessage(throwable),
                        () -> mStudentDetailView.dismissLoading());
        mStudentDetailView.addSubscription(disposable);
    }


    private void saveStudentInfoSuccess(ResponseData responseData) {
        mStudentDetailView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            mStudentDetailView.saveStudentInfoSuccess();
        } else {
            mStudentDetailView.showToast(responseData.errorMsg);
        }
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
