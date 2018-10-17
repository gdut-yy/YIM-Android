package net.devyy.yi.emoticon;

import android.view.View;

/**
 * 表情管理按钮点击事件
 */
public interface IEmotionExtClickListener {
    /**
     * "add" -> "表情商店"
     * @param view
     */
    void onEmotionAddClick(View view);

    /**
     * "setting" -> "我的表情"
     * @param view
     */
    void onEmotionSettingClick(View view);
}
