package com.shushan.thomework101.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.shushan.thomework101.BuildConfig;
import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.model.BankModel;
import com.shushan.thomework101.mvp.model.ModelTransform;
import com.shushan.thomework101.mvp.ui.activity.bank.AddBankCardControl;
import com.shushan.thomework101.mvp.ui.activity.bank.AddBankPresenterImpl;
import com.shushan.thomework101.network.RetrofitUtil;
import com.shushan.thomework101.network.networkapi.BankApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by li.liu on 16/3/20.
 */
@Module
public class AddBankCardModule {
    private final AppCompatActivity activity;
    private AddBankCardControl.AddBankCardView view;

    public AddBankCardModule(AppCompatActivity activity, AddBankCardControl.AddBankCardView view) {
        this.activity = activity;
        this.view = view;
    }

    public AddBankCardModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    AddBankCardControl.AddBankCardView view() {
        return this.view;
    }

    @Provides
    @PerActivity
    AddBankCardControl.PresenterAddBankCard providePresenterAddBankCard(AddBankPresenterImpl addBankPresenter) {
        return addBankPresenter;
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
