package com.shushan.thomework101.mvp.ui.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.shushan.thomework101.R;
import com.shushan.thomework101.mvp.ui.adapter.PopupWindowText2Adapter;

import java.util.List;


/**
 * 我的学生-选择学生类型 PopupWindow
 */
public class StudentTypeMorePopupWindow {
    private Context mContext;
    private PopupWindowListener mPopupWindowListener;
    private CustomPopWindow mCustomPopWindow;

    public StudentTypeMorePopupWindow(Context context, PopupWindowListener popupWindowListener) {
        mContext = context;
        mPopupWindowListener = popupWindowListener;
    }

    public void initPopWindow(View view,List<String> textList) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.popup_show_student_type_more, null);
        //处理popWindow 显示内容
        handlePopListView(contentView,textList);
        //创建并显示popWindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(mContext)
                .setView(contentView)
                .enableBackgroundDark(true)
                .size(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create();
        mCustomPopWindow.showAsDropDown(view,0, 0);
    }

    private void handlePopListView(View contentView, List<String> textList) {
        RecyclerView popupWindowRecyclerView = contentView.findViewById(R.id.popup_window_recycler_view);
        PopupWindowText2Adapter popupWindowTextAdapter = new PopupWindowText2Adapter(textList);
        popupWindowRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        popupWindowRecyclerView.setAdapter(popupWindowTextAdapter);
        popupWindowRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (mPopupWindowListener != null) {
                    mPopupWindowListener.studentTypeBtnListener(position);
                }
                mCustomPopWindow.dissmiss();
            }
        });
    }

    public interface PopupWindowListener {
        void studentTypeBtnListener(int type);
    }
}
