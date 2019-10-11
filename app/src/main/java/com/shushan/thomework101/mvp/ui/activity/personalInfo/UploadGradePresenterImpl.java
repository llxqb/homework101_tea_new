package com.shushan.thomework101.mvp.ui.activity.personalInfo;

import android.content.Context;

import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.UploadPersonalInfoRequest;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.MineModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2019/09/17.
 */

public class UploadGradePresenterImpl implements UploadGradeControl.PresenterUpdateGrade {

    private UploadGradeControl.UpdateGradeView mUpdateGradeView;
    private final MineModel mMineModel;
    private final Context mContext;

    @Inject
    public UploadGradePresenterImpl(Context context, MineModel model, UploadGradeControl.UpdateGradeView UpdateGradeView) {
        mContext = context;
        mMineModel = model;
        mUpdateGradeView = UpdateGradeView;
    }


    /**
     * 设置老师辅导年级和科目
     */
    @Override
    public void uploadPersonalGradeInfo(UploadPersonalInfoRequest uploadPersonalInfoRequest) {
        mUpdateGradeView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.uploadPersonalGradeInfo(uploadPersonalInfoRequest).compose(mUpdateGradeView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::uploadPersonalGradeInfoSuccess, throwable -> mUpdateGradeView.showErrMessage(throwable),
                        () -> mUpdateGradeView.dismissLoading());
        mUpdateGradeView.addSubscription(disposable);
    }

    /**
     * 设置老师辅导年级和科目成功
     */
    private void uploadPersonalGradeInfoSuccess(ResponseData responseData) {
        mUpdateGradeView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            mUpdateGradeView.getUploadPersonalGradeInfoSuccess();
        } else {
            mUpdateGradeView.showToast(responseData.errorMsg);
        }
    }

    
    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
