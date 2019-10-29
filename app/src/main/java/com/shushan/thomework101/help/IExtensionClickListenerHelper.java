package com.shushan.thomework101.help;

import android.net.Uri;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.shushan.thomework101.mvp.utils.LogUtils;

import java.util.LinkedHashMap;

import io.rong.imkit.IExtensionClickListener;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.plugin.IPluginModule;

/**
 * @ClassName: IExtensionClickListenerHelper
 * @date: 2019-10-28
 */
public class IExtensionClickListenerHelper extends ConversationFragment implements IExtensionClickListener {

    /**
     * 点击 “发送”
     * @param view    “发送” view 实例
     * @param s 输入框内容
     */
    @Override
    public void onSendToggleClick(View view, String s) {
        LogUtils.e("s:" + s);
    }

    @Override
    public void onImageResult(LinkedHashMap<String, Integer> linkedHashMap, boolean b) {

    }

    @Override
    public void onLocationResult(double v, double v1, String s, Uri uri) {

    }

    @Override
    public void onSwitchToggleClick(View view, ViewGroup viewGroup) {

    }

    @Override
    public void onVoiceInputToggleTouch(View view, MotionEvent motionEvent) {

    }

    @Override
    public void onEmoticonToggleClick(View view, ViewGroup viewGroup) {

    }

    /**
     * 点击 “+” 号区域, 回调中携带 ViewGroup
     *
     * @param view      “+” 号 view 实例
     * @param viewGroup 用于展示 plugin 的 ViewGroup
     */
    @Override
    public void onPluginToggleClick(View view, ViewGroup viewGroup) {
        LogUtils.e("onPluginToggleClick()");
    }

    /**
     * Plugin 点击回调。
     *
     * @param iPluginModule 被点击的Plugin。
     * @param position      被点击Plugin所在的位置。
     */
    @Override
    public void onPluginClicked(IPluginModule iPluginModule, int position) {
        LogUtils.e("position:" + position);
        Log.e("ddd", "position:" + position);
    }

    @Override
    public void onMenuClick(int i, int i1) {

    }

    @Override
    public void onEditTextClick(EditText editText) {

    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public void onExtensionCollapsed() {

    }

    @Override
    public void onExtensionExpanded(int i) {

    }


    @Override
    public void onPhrasesClicked(String s, int i) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
