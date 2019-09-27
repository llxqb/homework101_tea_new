package com.shushan.thomework101.mvp.ui.activity.guide;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.shushan.thomework101.HomeworkApplication;
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
 * 辅导科目选择
 */
public class SubjectSelectActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    SelectTextAdapter mSelectSubjectAdapter;
    List<SelectTextResponse> selectTextResponseList = new ArrayList<>();
    @BindView(R.id.go_iv)
    ImageView mGoIv;
    /**
     * 选择的科目
     */
    private String selectSubject;
    private User mUser;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_subject_select);
        ((HomeworkApplication) getApplication()).getAppComponent().inject(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getBooleanExtra("toMainActivity", false)) {
            //跳到首页main
            startActivitys(MainActivity.class);
            finish();
        }
    }

    @Override
    public void initView() {
        mUser = mBuProcessor.getUser();
        mSelectSubjectAdapter = new SelectTextAdapter(selectTextResponseList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mSelectSubjectAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SelectTextResponse selectTextResponse = (SelectTextResponse) adapter.getItem(position);
                if (view.getId() == R.id.item_grade_layout) {
                    assert selectTextResponse != null;
                    for (SelectTextResponse selectTextResponse1 : selectTextResponseList) {
                        if (selectTextResponse1.check) {
                            selectTextResponse1.check = false;
                        }
                    }
                    selectTextResponse.check = true;
                    selectSubject = selectTextResponse.name;
                    mGoIv.setImageResource(R.mipmap.landing_nextstep_2);
                    mSelectSubjectAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void initData() {
        String[] subjectValue = getResources().getStringArray(R.array.subject);
        for (String s : subjectValue) {
            SelectTextResponse gradeResponse = new SelectTextResponse();
            gradeResponse.name = s;
            gradeResponse.check = false;
            selectTextResponseList.add(gradeResponse);
        }
    }


    @OnClick(R.id.go_iv)
    public void onViewClicked() {
        if (TextUtils.isEmpty(selectSubject)) {
            showToast("请选择科目");
            return;
        }
        mUser.subject = selectSubject;
        mBuProcessor.setLoginUser(mUser);
        startActivitys(GradeSelectActivity.class);
    }
}
