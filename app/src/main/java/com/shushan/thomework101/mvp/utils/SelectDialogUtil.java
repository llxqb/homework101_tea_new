package com.shushan.thomework101.mvp.utils;

import android.content.Context;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shushan.thomework101.R;

import java.util.Date;
import java.util.List;

/**
 * 选择框util
 */
public class SelectDialogUtil {

    private Context mContext;
    private SelectPickerListener mSelectPickerListener;

    public SelectDialogUtil(Context context, SelectPickerListener selectPickerListener) {
        mContext = context;
        mSelectPickerListener = selectPickerListener;
    }

    /**
     * 条件选择器,选择文字
     * 选择科目
     */
    public void selectSubject(String title, List<String> stringList) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, (options1, option2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            String tx = stringList.get(options1);
            if (mSelectPickerListener != null) {
                mSelectPickerListener.getSelectText(tx);
            }
        })
                .setTitleText(title)//标题文字
                .setTitleColor(mContext.getResources().getColor(R.color.color999))//标题文字颜色
                .setSubmitColor(mContext.getResources().getColor(R.color.color_blue_btn))//确定按钮文字颜色
                .setCancelColor(mContext.getResources().getColor(R.color.first_text_color))//取消按钮文字颜色
                .build();
        pvOptions.setPicker(stringList);
        pvOptions.show();
    }


    /**
     * 日期选择器
     * 选择生日弹框
     */
    public void showBirthdayDialog() {
        TimePickerView pvTime = new TimePickerBuilder(mContext, (date, v) -> {//选中事件回调
            if (mSelectPickerListener != null) {
                mSelectPickerListener.getSelectDate(date);
            }
//            mSelectDateTv.setText(DateUtil.dateTranString(date, "yyyy年MM月dd日"));
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setTitleText("选择日期")//标题文字
                .setTitleColor(mContext.getResources().getColor(R.color.color999))//标题文字颜色
                .setSubmitColor(mContext.getResources().getColor(R.color.color_blue_btn))//确定按钮文字颜色
                .setCancelColor(mContext.getResources().getColor(R.color.first_text_color))//取消按钮文字颜色
                .build();
        pvTime.show();
    }


    public interface SelectPickerListener {
        void getSelectText(String text);

        void getSelectDate(Date date);
    }
}
