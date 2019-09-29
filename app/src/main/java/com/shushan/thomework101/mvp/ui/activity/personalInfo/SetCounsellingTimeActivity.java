package com.shushan.thomework101.mvp.ui.activity.personalInfo;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.DateResponse;
import com.shushan.thomework101.mvp.ui.activity.mine.CustomerServiceActivity;
import com.shushan.thomework101.mvp.ui.adapter.DateAdapter;
import com.shushan.thomework101.mvp.ui.base.BaseActivity;
import com.shushan.thomework101.mvp.utils.DateUtil;
import com.shushan.thomework101.mvp.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc:设置辅导时间
 */
public class SetCounsellingTimeActivity extends BaseActivity {

    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.common_right_iv)
    ImageView mCommonRightIv;
    @BindView(R.id.time_requirements)
    TextView mTimeRequirements;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.saturday_check_iv)
    ImageView mSaturdayCheckIv;
    @BindView(R.id.sunday_check_iv)
    ImageView mSundayCheckIv;
    @BindView(R.id.working_day_start_time_tv)
    TextView mWorkingDayStartTimeTv;
    @BindView(R.id.working_day_end_time_tv)
    TextView mWorkingDayEndTimeTv;
    @BindView(R.id.off_day_start_time_tv)
    TextView mOffDayStartTimeTv;
    @BindView(R.id.off_day_end_time_tv)
    TextView mOffDayEndTimeTv;

    DateAdapter mDateAdapter;
    List<DateResponse> dateResponseList = new ArrayList<>();


    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_set_counselling_time);
    }

    @Override
    public void initView() {
        mCommonTitleTv.setText("设置辅导时间");
        mCommonRightIv.setImageResource(R.mipmap.tutor_service);
        mDateAdapter = new DateAdapter(dateResponseList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mDateAdapter);
        mDateAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            DateResponse dateResponse = (DateResponse) adapter.getItem(position);
            assert dateResponse != null;
            dateResponse.check = !dateResponse.check;
            mDateAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void initData() {
        mCommonRightIv.setVisibility(View.VISIBLE);
        String timeRequirementsValue = "辅导时间要求一个星期至少有" + "<font color = '#6941FF'>" + "6天" + "</font>" + "，每天不得少于" + "<font color = '#6941FF'>" + "4小时" + "</font>" + "。周一至周五的时间设置范围在16时-24时。请按规范设置，否则拒绝通过审核。";
        mTimeRequirements.setText(Html.fromHtml(timeRequirementsValue));
        String[] workingDayValue = getResources().getStringArray(R.array.working_day_name);
        for (String workingDay : workingDayValue) {
            DateResponse dateResponse = new DateResponse();
            dateResponse.name = workingDay;
            dateResponse.check = false;
            dateResponseList.add(dateResponse);
        }
    }


    @OnClick({R.id.common_left_iv, R.id.common_right_iv, R.id.working_day_date_rl, R.id.working_day_time_rl, R.id.saturday_rl, R.id.sunday_rl, R.id.off_day_date_rl, R.id.off_day_time_rl, R.id.sure_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_iv:
                finish();
                break;
            case R.id.common_right_iv:
                startActivitys(CustomerServiceActivity.class);
                break;
            case R.id.working_day_date_rl:
                //显示选择时间弹框
                showDateDialog(mWorkingDayStartTimeTv);
                break;
            case R.id.working_day_time_rl:
                showDateDialog(mWorkingDayEndTimeTv);
                break;
            case R.id.saturday_rl:
                if (mSaturdayCheckIv.getVisibility() == View.VISIBLE) {
                    mSaturdayCheckIv.setVisibility(View.INVISIBLE);
                } else {
                    mSaturdayCheckIv.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.sunday_rl:
                if (mSundayCheckIv.getVisibility() == View.VISIBLE) {
                    mSundayCheckIv.setVisibility(View.INVISIBLE);
                } else {
                    mSundayCheckIv.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.off_day_date_rl:
                showDateDialog(mOffDayStartTimeTv);
                break;
            case R.id.off_day_time_rl:
                showDateDialog(mOffDayEndTimeTv);
                break;
            case R.id.sure_tv:
                if (valid()) {
                    //保存更改
                } else {
                    showToast("一周至少选择六天辅导");
                }
                break;
        }
    }


    private boolean valid() {
        int isCheckNum = 0;//记录一周选中天数数量
        if (mSaturdayCheckIv.getVisibility() == View.VISIBLE) {
            isCheckNum++;
        }
        if (mSundayCheckIv.getVisibility() == View.VISIBLE) {
            isCheckNum++;
        }
        for (DateResponse dateResponse : mDateAdapter.getData()) {
            if (dateResponse.check) {
                isCheckNum++;
            }
        }
        LogUtils.e("isCheckNum:" + isCheckNum);
        return isCheckNum >= 6;
    }

    /**
     * 日期选择器
     * 选择生日弹框
     * mWorkingDayStartTimeTv
     */
    public void showDateDialog(TextView textView) {
        TimePickerView pvTime = new TimePickerBuilder(this, (date, v) -> {//选中事件回调
            textView.setText(DateUtil.dateTranString(date, "HH:mm"));
        })
                .setType(new boolean[]{false, false, false, true, true, false})// 默认全部显示
                .setTitleText("选择时间")//标题文字
                .setTitleColor(getResources().getColor(R.color.color999))//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.color_blue_btn))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.first_text_color))//取消按钮文字颜色
                .build();
        pvTime.show();
    }

}
