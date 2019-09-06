package com.shushan.thomework101.mvp.utils;

import android.content.Context;


import com.shushan.thomework101.help.Sharedprefence;

import javax.inject.Inject;

/**
 * SharePreference常量类
 * 使用：SharedPreferences mySharedprefence = getSharedPreferences("setting", Activity.MODE_PRIVATE);
 * SharedPreferences.Editor editor = mySharedprefence.edit();
 * editor.putString("ll","");
 * editor.apply();//commit();
 */
public class SharePreferenceUtil {
    private static final String projectKey = "kencanme_cache";
    private Sharedprefence mSharedprefence;

    @Inject
    public SharePreferenceUtil(Context context, Sharedprefence sharedprefence) {
        mSharedprefence = sharedprefence;
    }

    /**
     * sp 存数据
     *
     * @param key
     * @param value
     */
    public void setData(String key, Object value) {
        mSharedprefence.WriteSharedPreferences(projectKey, key, value);
    }

    /**
     * sp 取出本地数据
     *
     * @param key
     */
    public String getData(String key) {
        return mSharedprefence.getValueByName(projectKey, key, Sharedprefence.STRING).toString();
    }

    /**
     * sp 取出本地数据
     *
     * @param key
     */
    public int getIntData(String key) {
        return mSharedprefence.getValueByName(projectKey, key, Sharedprefence.INT);
    }

    public boolean getBooleanData(String key) {
        return mSharedprefence.getValueByName(projectKey, key, Sharedprefence.BOOLEAN);
    }

    /**
     * 赋默认值
     */
    public boolean getBooleanData(String key, boolean defValue) {
        return mSharedprefence.getValueByName(projectKey, key, Sharedprefence.BOOLEAN, defValue);
    }

    /**
     * 写入对象
     *
     * @param key
     * @param o
     */
    public void saveObjData(String key, Object o) {
        mSharedprefence.saveObject(projectKey, key, o);
    }

    /**
     * 读取对象
     *
     * @param key
     */
    public Object readObjData(String key) {
        return mSharedprefence.readObject(projectKey, key);
    }

    /**
     * 清除sp
     */
    public void clearData() {
        mSharedprefence.ClearSharedPreferences(projectKey);
    }

}
