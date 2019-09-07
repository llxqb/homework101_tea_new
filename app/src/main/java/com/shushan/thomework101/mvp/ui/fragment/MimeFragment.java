package com.shushan.thomework101.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.shushan.thomework101.HomeworkApplication;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerMimeFragmentComponent;
import com.shushan.thomework101.di.modules.MainModule;
import com.shushan.thomework101.di.modules.MimeFragmentModule;
import com.shushan.thomework101.mvp.ui.base.BaseFragment;
import com.shushan.thomework101.mvp.utils.StatusBarUtil;

import java.util.Objects;

/**
 * MimeFragment
 * 我的
 */

public class MimeFragment extends BaseFragment implements MimeFragmentControl.MimeView {

    public static MimeFragment newInstance() {
        return new MimeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mime, container, false);
        initializeInjector();
        StatusBarUtil.setTransparentForImageView(getActivity(), null);
        initView();
        initData();
        return view;
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {
    }


    private void initializeInjector() {
        DaggerMimeFragmentComponent.builder().appComponent(((HomeworkApplication) Objects.requireNonNull(getActivity()).getApplication()).getAppComponent())
                .mainModule(new MainModule((AppCompatActivity) getActivity()))
                .mimeFragmentModule(new MimeFragmentModule(this))
                .build().inject(this);
    }

}
