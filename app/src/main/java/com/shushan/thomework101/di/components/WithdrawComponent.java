package com.shushan.thomework101.di.components;


import android.support.v7.app.AppCompatActivity;

import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.WithdrawModule;
import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.ui.activity.bank.WalletActivity;
import com.shushan.thomework101.mvp.ui.activity.bank.WithdrawActivity;

import dagger.Component;

/**
 * LoginComponent继承了ActivityComponent，假如ActivityComponent中定义了创建类实例方法，则MainComponent中必须提供@Inject或@Provides对应的
 * 初始化类实例的方法
 * Created by li.liu on 18/1/19.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {WithdrawModule.class, ActivityModule.class})
public interface WithdrawComponent extends ActivityComponent {
    void inject(WalletActivity activity);//我的钱包
    void inject(WithdrawActivity activity);//提现

    AppCompatActivity activity();


}
