package com.shushan.thomework101.mvp.ui.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.shushan.thomework101.R;
import com.shushan.thomework101.mvp.ui.adapter.PopupWindowTextAdapter;

import java.util.Arrays;
import java.util.List;


/**
 * 聊天更多 PopupWindow
 */
public class ChatMorePopupWindow {
    private Context mContext;
    private PopupWindowListener mPopupWindowListener;
    private CustomPopWindow mCustomPopWindow;

    public ChatMorePopupWindow(Context context, PopupWindowListener popupWindowListener) {
        mContext = context;
        mPopupWindowListener = popupWindowListener;
    }

    public void initPopWindow(View view) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.popup_show_chat_more, null);
        //处理popWindow 显示内容
        handlePopListView(contentView);
        //创建并显示popWindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(mContext)
                .setView(contentView)
                .enableBackgroundDark(true)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create();
        mCustomPopWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void handlePopListView(View contentView) {
        RecyclerView popupWindowRecyclerView = contentView.findViewById(R.id.popup_window_recycler_view);
        TextView cancelTv = contentView.findViewById(R.id.cancel_tv);
        //
        List<String> textList = Arrays.asList(mContext.getResources().getStringArray(R.array.chat_more_text));
        PopupWindowTextAdapter popupWindowTextAdapter = new PopupWindowTextAdapter(textList);
        popupWindowRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        popupWindowRecyclerView.setAdapter(popupWindowTextAdapter);

        popupWindowRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0://发送付款邀请
                        if (mPopupWindowListener != null) {
                            mPopupWindowListener.paymentInvitationBtnListener();
                        }
                        break;
                    case 1://添加备注
                        if (mPopupWindowListener != null) {
                            mPopupWindowListener.addNotesBtnListener();
                        }
                        break;
                    case 2://设置版本
                        if (mPopupWindowListener != null) {
                            mPopupWindowListener.setVersionBtnListener();
                        }
                        break;
                    case 3://学生详情
                        if (mPopupWindowListener != null) {
                            mPopupWindowListener.studentDetailBtnListener();
                        }
                        break;
                }
            }
        });
        cancelTv.setOnClickListener(v -> mCustomPopWindow.dissmiss());
    }

    public interface PopupWindowListener {
        void paymentInvitationBtnListener();

        void addNotesBtnListener();

        void setVersionBtnListener();

        void studentDetailBtnListener();

    }
}
