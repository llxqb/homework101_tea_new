package com.shushan.thomework101.mvp.ui.activity.guide.register;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.SelectTextResponse;
import com.shushan.thomework101.entity.user.User;
import com.shushan.thomework101.mvp.ui.activity.main.MainActivity;
import com.shushan.thomework101.mvp.ui.adapter.SelectTextAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 年级选择
 */
public class GradeSelectActivity extends BaseActivity {
    @BindView(R.id.primary_school_tv)
    TextView mPrimarySchoolTv;
    @BindView(R.id.primary_school_recycler_view)
    RecyclerView mPrimarySchoolRecyclerView;
    @BindView(R.id.junior_high_school_tv)
    TextView mJuniorHighSchoolTv;
    @BindView(R.id.junior_high_school_recycler_view)
    RecyclerView mJuniorHighSchoolRecyclerView;
    @BindView(R.id.go_iv)
    ImageView mGoIv;
    private List<SelectTextResponse> gradeResponseList = new ArrayList<>();
    private List<SelectTextResponse> gradeHighSchoolResponseList = new ArrayList<>();
    private SelectTextAdapter mGradeAdapter;
    private SelectTextAdapter mGradeHighSchoolAdapter;
    private User mUser;
    private int selectGrade;
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_grade_select);
    }

    @Override
    public void initView() {
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

        mPrimarySchoolRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SelectTextResponse gradeResponse = (SelectTextResponse) adapter.getItem(position);
                if (view.getId() == R.id.item_grade_layout) {
                    //把选择的初中信息全清空
                    for (SelectTextResponse gradeResponse2 : gradeHighSchoolResponseList) {
                        if (gradeResponse2.check) {
                            gradeResponse2.check = false;
                        }
                    }
                    mGradeHighSchoolAdapter.notifyDataSetChanged();
                    assert gradeResponse != null;
                    for (SelectTextResponse gradeResponse1 : gradeResponseList) {
                        if (gradeResponse1.check) {
                            gradeResponse1.check = false;
                        }
                    }
                    selectGrade = position + 1;
                    gradeResponse.check = true;
                    mGradeAdapter.notifyDataSetChanged();
                }

            }
        });
        mJuniorHighSchoolRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SelectTextResponse gradeResponse = (SelectTextResponse) adapter.getItem(position);
                if (view.getId() == R.id.item_grade_layout) {
                    //把选择的小学信息全清空
                    for (SelectTextResponse gradeResponse1 : gradeResponseList) {
                        if (gradeResponse1.check) {
                            gradeResponse1.check = false;
                        }
                    }
                    mGradeAdapter.notifyDataSetChanged();
                    assert gradeResponse != null;
                    for (SelectTextResponse gradeResponse2 : gradeHighSchoolResponseList) {
                        if (gradeResponse2.check) {
                            gradeResponse2.check = false;
                        }
                    }
                    selectGrade = position + 7;
                    gradeResponse.check = true;
                    mGradeHighSchoolAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void initData() {
        String[] primarySchoolValue = getResources().getStringArray(R.array.primary_school);
        for (String s : primarySchoolValue) {
            SelectTextResponse gradeResponse = new SelectTextResponse();
            gradeResponse.name = s;
            gradeResponse.check = false;
            gradeResponseList.add(gradeResponse);
        }

        String[] juniorHighSchoolValue = getResources().getStringArray(R.array.junior_high_school);
        for (String s : juniorHighSchoolValue) {
            SelectTextResponse gradeResponse = new SelectTextResponse();
            gradeResponse.name = s;
            gradeResponse.check = false;
            gradeHighSchoolResponseList.add(gradeResponse);
        }
    }

    @OnClick({R.id.common_left_iv, R.id.go_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.go_iv:
                if(selectGrade ==0){
                    showToast("请选择年级");
                    return;
                }
                startActivitys(MainActivity.class);
                finish();
//                mUser.grade = selectGrade;
//                mBuProcessor.setLoginUser(mUser);
//                startActivitys(AdvantageIntroductionActivity.class);
//                finish();
                break;
        }
    }
}
