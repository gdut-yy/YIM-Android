package net.devyy.yi;

import android.app.Application;
import android.content.Context;

/**
 * YIMApplication，初始化 环信SDK
 */
public class YIMApplication extends Application {

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
    }
}
