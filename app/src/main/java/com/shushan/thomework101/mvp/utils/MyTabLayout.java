package com.shushan.thomework101.mvp.utils;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.shushan.thomework101.R;

import java.util.List;
import java.util.Objects;

/**
 * 首页自定义tabLayout字体 、下划线
 */
public class MyTabLayout {
    private ViewHolder holder;

    public void initTabView(Context context, List<String> tabs, TabLayout mTabLayout, ViewPager mViewPager) {
        holder = null;
        for (int i = 0; i < tabs.size(); i++) {
            //获取tab
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            //给tab设置自定义布局
            assert tab != null;
            tab.setCustomView(R.layout.tab_home_layout);
            holder = new ViewHolder(Objects.requireNonNull(tab.getCustomView()));
            //填充数据
            holder.mTabItemName.setText(tabs.get(i));
            //默认选择第一项
            if (i == 0) {
                holder.mTabItemName.setSelected(true);
                holder.mTabItemName.setTextSize(TypedValue.COMPLEX_UNIT_SP,23);
                holder.mTabItemName.setTextColor(context.getResources().getColor(R.color.black));
                holder.mTabLine.setVisibility(View.VISIBLE);
            }
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                holder = new ViewHolder(Objects.requireNonNull(tab.getCustomView()));
                holder.mTabItemName.setSelected(true);
                //设置选中后的字体大小
                holder.mTabItemName.setTextSize(TypedValue.COMPLEX_UNIT_SP,23);
                holder.mTabItemName.setTextColor(context.getResources().getColor(R.color.black));
                holder.mTabLine.setVisibility(View.VISIBLE);
                //关联Viewpager
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                holder = new ViewHolder(Objects.requireNonNull(tab.getCustomView()));
                holder.mTabItemName.setSelected(false);
                //恢复默认字体大小
                holder.mTabItemName.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
                holder.mTabItemName.setTextColor(context.getResources().getColor(R.color.color999));
                holder.mTabLine.setVisibility(View.GONE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    class ViewHolder {
        TextView mTabItemName;
        View mTabLine;

        ViewHolder(View tabView) {
            mTabItemName = tabView.findViewById(R.id.tab_name);
            mTabLine = tabView.findViewById(R.id.tab_line);
        }
    }
}
