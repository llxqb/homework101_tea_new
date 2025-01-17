package com.shushan.thomework101.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.shushan.thomework101.BuildConfig;
import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.model.BankModel;
import com.shushan.thomework101.mvp.model.ModelTransform;
import com.shushan.thomework101.mvp.ui.activity.bank.WithdrawControl;
import com.shushan.thomework101.mvp.ui.activity.bank.WithdrawPresenterImpl;
import com.shushan.thomework101.network.RetrofitUtil;
import com.shushan.thomework101.network.networkapi.BankApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class WithdrawModule {
    private final AppCompatActivity activity;
    private WithdrawControl.WithdrawView view;

    public WithdrawModule(AppCompatActivity activity, WithdrawControl.WithdrawView view) {
        this.activity = activity;
        this.view = view;
    }

    public WithdrawModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    WithdrawControl.WithdrawView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    WithdrawControl.PresenterWithdraw providePresenterWithdraw(WithdrawPresenterImpl withdrawPresenter) {
        return withdrawPresenter;
    }

    @Provides
    @PerActivity
    BankModel provideBankModel(Gson gson, ModelTransform modelTransform) {
        return new BankModel(new RetrofitUtil.Builder()
                .context(activity)
                .baseUrl(BuildConfig.WORK_TEA_BASE_URL)
                .isHttps(!BuildConfig.DEBUG)
//                .key(BuildConfig.STORE_NAME,BuildConfig.STORE_PASSWORD)
                .isToJson(false)
                .builder()
                .create(BankApi.class), gson, modelTransform);
    }




}
