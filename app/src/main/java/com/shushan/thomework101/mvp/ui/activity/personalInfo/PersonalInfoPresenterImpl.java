package com.shushan.thomework101.mvp.ui.activity.personalInfo;

import android.content.Context;

import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.request.UploadImage;
import com.shushan.thomework101.entity.request.UploadPersonalInfoRequest;
import com.shushan.thomework101.help.RetryWithDelay;
import com.shushan.thomework101.mvp.model.MineModel;
import com.shushan.thomework101.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2019/09/17.
 */

public class PersonalInfoPresenterImpl implements PersonalInfoControl.PresenterPersonalInfo {

    private PersonalInfoControl.PersonalInfoView mPersonalInfoView;
    private final MineModel mMineModel;
    private final Context mContext;

    @Inject
    public PersonalInfoPresenterImpl(Context context, MineModel model, PersonalInfoControl.PersonalInfoView PersonalInfoView) {
        mContext = context;
        mMineModel = model;
        mPersonalInfoView = PersonalInfoView;
    }

    /**
     * 上传图片
     */
    @Override
    public void uploadImageRequest(UploadImage uploadImage) {
        mPersonalInfoView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.uploadImageRequest(uploadImage).compose(mPersonalInfoView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestUploadImageSuccess, throwable -> mPersonalInfoView.showErrMessage(throwable),
                        () -> mPersonalInfoView.dismissLoading());
        mPersonalInfoView.addSubscription(disposable);
    }


    private void requestUploadImageSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            mPersonalInfoView.getUploadImageSuccess(responseData.result);
        } else {
            mPersonalInfoView.showToast(responseData.errorMsg);
        }
    }


    /**
     * 设置老师辅导年级和科目
     */
    @Override
    public void uploadPersonalGradeInfo(UploadPersonalInfoRequest uploadPersonalInfoRequest) {
        mPersonalInfoView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.uploadPersonalGradeInfo(uploadPersonalInfoRequest).compose(mPersonalInfoView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::uploadPersonalInfoSuccess, throwable -> mPersonalInfoView.showErrMessage(throwable),
                        () -> mPersonalInfoView.dismissLoading());
        mPersonalInfoView.addSubscription(disposable);
    }

    /**
     * 设置老师辅导年级和科目成功
     */
    private void uploadPersonalInfoSuccess(ResponseData responseData) {
        mPersonalInfoView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            mPersonalInfoView.getUploadPersonalGradeInfoSuccess();
        } else {
            mPersonalInfoView.showToast(responseData.errorMsg);
        }
    }

    /**
     * 上传老师证书
     */
    @Override
    public void uploadPersonalCardInfo(UploadPersonalInfoRequest uploadPersonalInfoRequest) {
        mPersonalInfoView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.uploadPersonalCardInfo(uploadPersonalInfoRequest).compose(mPersonalInfoView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::uploadPersonalCardInfoSuccess, throwable -> mPersonalInfoView.showErrMessage(throwable),
                        () -> mPersonalInfoView.dismissLoading());
        mPersonalInfoView.addSubscription(disposable);
    }

    /**
     * 上传老师证书成功
     */
    private void uploadPersonalCardInfoSuccess(ResponseData responseData) {
        mPersonalInfoView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            mPersonalInfoView.getUploadPersonalCardInfoSuccess();
        } else {
            mPersonalInfoView.showToast(responseData.errorMsg);
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
