package com.shushan.thomework101.di.components;


import android.support.v7.app.AppCompatActivity;

import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.ExpectedIncomeModule;
import com.shushan.thomework101.di.scopes.PerActivity;
import com.shushan.thomework101.mvp.ui.activity.mine.ExpectedCommissionIncomeActivity;
import com.shushan.thomework101.mvp.ui.activity.mine.ExpectedTotalIncomeActivity;
import com.shushan.thomework101.mvp.ui.activity.mine.RevenueIncomeActivity;

import dagger.Component;

/**
 * LoginComponent继承了ActivityComponent，假如ActivityComponent中定义了创建类实例方法，则MainComponent中必须提供@Inject或@Provides对应的
 * 初始化类实例的方法
 * Created by li.liu on 18/1/19.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {ExpectedIncomeModule.class, ActivityModule.class})
public interface ExpectedIncomeComponent extends ActivityComponent {
    void inject(ExpectedTotalIncomeActivity activity);

    void inject(ExpectedCommissionIncomeActivity activity);
    void inject(RevenueIncomeActivity activity);

    AppCompatActivity activity();


}
