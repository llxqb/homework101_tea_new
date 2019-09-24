package com.shushan.thomework101.mvp.ui.activity.guide.register;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.SelectTextResponse;
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

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    SelectTextAdapter mSelectSubjectAdapter;
    List<SelectTextResponse> selectTextResponseList =new ArrayList<>();

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_subject_select);
    }

    @Override
    public void initView() {
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
                    mSelectSubjectAdapter.notifyDataSetChanged();
                    startActivitys(GradeSelectActivity.class);
                    finish();
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


    @OnClick(R.id.common_left_iv)
    public void onViewClicked() {
        finish();
    }
}
