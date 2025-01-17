package com.shushan.thomework101.mvp.ui.activity.guide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.gson.Gson;
import com.shushan.thomework101.R;
import com.shushan.thomework101.di.components.DaggerUploadGradeComponent;
import com.shushan.thomework101.di.modules.ActivityModule;
import com.shushan.thomework101.di.modules.UploadGradeModule;
import com.shushan.thomework101.entity.request.UploadPersonalInfoRequest;
import com.shushan.thomework101.entity.response.SelectTextResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.activity.personalInfo.UploadGradeControl;
import com.shushan.thomework101.mvp.ui.adapter.SelectTextAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.utils.UserUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 年级选择
 */
public class GradeSelectActivity extends BaseActivity implements UploadGradeControl.UpdateGradeView {
    @BindView(R.id.primary_school_tv)
    TextView mPrimarySchoolTv;
    @BindView(R.id.primary_school_recycler_view)
    RecyclerView mPrimarySchoolRecyclerView;
    @BindView(R.id.junior_high_school_tv)
    TextView mJuniorHighSchoolTv;
    @BindView(R.id.junior_high_school_recycler_view)
    RecyclerView mJuniorHighSchoolRecyclerView;
    private List<SelectTextResponse> gradeResponseList = new ArrayList<>();
    private List<SelectTextResponse> gradeHighSchoolResponseList = new ArrayList<>();
    private SelectTextAdapter mGradeAdapter;
    private SelectTextAdapter mGradeHighSchoolAdapter;
    private User mUser;
    private List<Integer> selectGradeList = new ArrayList<>();//HashSet去重复值
    /**
     * 上一页面选择的科目
     */
    private int selectSubject;
    @Inject
    UploadGradeControl.PresenterUpdateGrade mPresenter;

    public static void start(Context context, int selectSubject) {
        Intent intent = new Intent(context, GradeSelectActivity.class);
        intent.putExtra("subject", selectSubject);
        context.startActivity(intent);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_grade_select);
        initInjectData();
    }

    @Override
    public void initView() {
        mUser = mBuProcessor.getUser();
        if (getIntent() != null) {
            selectSubject = getIntent().getIntExtra("subject", 0);
        }
        if (!UserUtil.isJuniorHighSchoolSubject(selectSubject)) {
            //是初中科目，只显示初中年级 ，否则显示全部年级
            mPrimarySchoolTv.setVisibility(View.GONE);
            mPrimarySchoolRecyclerView.setVisibility(View.GONE);
        }
        //小学
        mGradeAdapter = new SelectTextAdapter(gradeResponseList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mPrimarySchoolRecyclerView.setLayoutManager(gridLayoutManager);
        mPrimarySchoolRecyclerView.setAdapter(mGradeAdapter);
        //初中
        mGradeHighSchoolAdapter = new SelectTextAdapter(gradeHighSchoolResponseList);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 3);
        mJuniorHighSchoolRecyclerView.setLayoutManager(gridLayoutManager2);
        mJuniorHighSchoolRecyclerView.setAdapter(mGradeHighSchoolAdapter);
        initRecyclerViewData();
    }

    @Override
    public void initData() {
        String[] primarySchoolValue = getResources().getStringArray(R.array.primary_school);
        for (int i = 0; i < primarySchoolValue.length; i++) {
            SelectTextResponse gradeResponse = new SelectTextResponse();
            gradeResponse.name = primarySchoolValue[i];
            gradeResponse.check = false;
            gradeResponse.num = i + 1;
            gradeResponseList.add(gradeResponse);
        }

        String[] juniorHighSchoolValue = getResources().getStringArray(R.array.junior_high_school);
        for (int i = 0; i < juniorHighSchoolValue.length; i++) {
            SelectTextResponse gradeResponse = new SelectTextResponse();
            gradeResponse.name = juniorHighSchoolValue[i];
            gradeResponse.num = i + 7;
            gradeResponse.check = false;
            gradeHighSchoolResponseList.add(gradeResponse);
        }
    }

    @OnClick({R.id.common_left_iv, R.id.sure_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.sure_tv:
                if (selectGradeList.isEmpty()) {
                    showToast("请选择年级");
                    return;
                }
                onRequestUploadPersonalInfo();
                break;
        }
    }

    UploadPersonalInfoRequest request;

    /**
     * 更新个人信息
     * 设置年级科目
     */
    private void onRequestUploadPersonalInfo() {
        request = new UploadPersonalInfoRequest();
        request.token = mUser.token;
        request.subject = String.valueOf(selectSubject);
        request.grades = new Gson().toJson(selectGradeList);
        mPresenter.uploadPersonalGradeInfo(request);
    }

    @Override
    public void getUploadPersonalGradeInfoSuccess() {
        mUser.subject = Integer.parseInt(request.subject);
        mUser.grades = request.grades;
        mBuProcessor.setLoginUser(mUser);
        toMainActivity();
    }

    /**
     * 跳到首页，finish掉其它页面
     */
    public void toMainActivity() {
        Intent intent = new Intent(this, SubjectSelectActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//表示 不创建新的实例activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//表示 移除该activity上面的activity
        intent.putExtra("toMainActivity", true);
        startActivity(intent);
        finish();
    }

    private void initRecyclerViewData() {
        mPrimarySchoolRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SelectTextResponse gradeResponse = (SelectTextResponse) adapter.getItem(position);
                if (view.getId() == R.id.item_grade_layout) {
                    if (!selectGradeList.contains(position + 1)) {
                        selectGradeList.add(position + 1);
                        if (selectGradeList.size() > 3) {
                            //集合超过三个移除掉第一个值
                            selectGradeList.remove(0);
                        }
                        for (SelectTextResponse gradeResponse2 : gradeHighSchoolResponseList) {
                            if (gradeResponse2.check) {
                                gradeResponse2.check = false;
                            }
                            for (Integer integer : selectGradeList) {
                                if (integer.equals(gradeResponse2.num)) {
                                    gradeResponse2.check = true;
                                }
                            }
                        }
                        mGradeHighSchoolAdapter.notifyDataSetChanged();

                        for (SelectTextResponse gradeResponse1 : gradeResponseList) {
                            if (gradeResponse1.check) {
                                gradeResponse1.check = false;
                            }
                            for (Integer integer : selectGradeList) {
                                if (integer.equals(gradeResponse1.num)) {
                                    gradeResponse1.check = true;
                                }
                            }
                        }
                        assert gradeResponse != null;
                        mGradeAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        mJuniorHighSchoolRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SelectTextResponse gradeResponse = (SelectTextResponse) adapter.getItem(position);
                if (!selectGradeList.contains(position + 7)) {
                    selectGradeList.add(position + 7);
                    if (selectGradeList.size() > 3) {
                        selectGradeList.remove(0);
                    }
                    for (SelectTextResponse gradeResponse2 : gradeHighSchoolResponseList) {
                        if (gradeResponse2.check) {
                            gradeResponse2.check = false;
                        }
                        for (Integer integer : selectGradeList) {
                            if (integer.equals(gradeResponse2.num)) {
                                gradeResponse2.check = true;
                            }
                        }
                    }
                    mGradeHighSchoolAdapter.notifyDataSetChanged();

                    for (SelectTextResponse gradeResponse1 : gradeResponseList) {
                        if (gradeResponse1.check) {
                            gradeResponse1.check = false;
                        }
                        for (Integer integer : selectGradeList) {
                            if (integer.equals(gradeResponse1.num)) {
                                gradeResponse1.check = true;
                            }
                        }
                    }
                    assert gradeResponse != null;
                    mGradeAdapter.notifyDataSetChanged();
                }
            }
        });
    }




    private void initInjectData() {
        DaggerUploadGradeComponent.builder().appComponent(getAppComponent())
                .uploadGradeModule(new UploadGradeModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }

}
