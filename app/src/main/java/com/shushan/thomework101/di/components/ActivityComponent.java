package com.shushan.thomework101.di.components;

import android.app.Activity;


import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.scopes.PerActivity;

import dagger.Component;

/**
 *
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    Activity getActivity();
}
