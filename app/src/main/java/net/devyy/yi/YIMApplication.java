package net.devyy.yi;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import net.devyy.yi.emoticon.EmotionKit;

/**
 * YIMApplication，初始化 环信SDK
 */
public class    YIMApplication extends Application {

    public static Context applicationContext;
    private static YIMApplication instance;

    public static YIMApplication getInstance( ) {
        return instance;
    }

    @Override
    public void onCreate( ) {
        super.onCreate();

        applicationContext = this;
        instance = this;

        // 初始化 环信SDK
        YIMHelper.getInstance().init(applicationContext);

        // 初始化 EmotionKit
        EmotionKit.init(this, (context, path, imageView) -> Glide.with(context).load(path).centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView));
    }
}
