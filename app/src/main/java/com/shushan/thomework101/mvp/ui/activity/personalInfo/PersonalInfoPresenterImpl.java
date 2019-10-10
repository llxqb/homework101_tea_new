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
     * 更新用户个人信息
     */
    @Override
    public void onRequestUploadPersonalInfo(UploadPersonalInfoRequest uploadPersonalInfoRequest) {
        mPersonalInfoView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mMineModel.onRequestUploadPersonalInfo(uploadPersonalInfoRequest).compose(mPersonalInfoView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestUploadPersonalInfoSuccess, throwable -> mPersonalInfoView.showErrMessage(throwable),
                        () -> mPersonalInfoView.dismissLoading());
        mPersonalInfoView.addSubscription(disposable);
    }


    /**
     * 更新用户个人信息成功
     */
    private void requestUploadPersonalInfoSuccess(ResponseData responseData) {
        mPersonalInfoView.judgeToken(responseData.resultCode);
        if (responseData.resultCode == 0) {
            mPersonalInfoView.getUploadPersonalInfoSuccess();
//            responseData.parseData(UploadPersonalInfoResponse.class);
//            if (responseData.parsedData != null) {
//                UploadPersonalInfoResponse response = (UploadPersonalInfoResponse) responseData.parsedData;
//                mPersonalInfoView.getUploadPersonalInfoSuccess(response);
//            }
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
