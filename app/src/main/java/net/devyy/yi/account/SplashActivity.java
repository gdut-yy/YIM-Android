package net.devyy.yi.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hyphenate.chat.EMClient;

import net.devyy.yi.R;
import net.devyy.yi.YIMHelper;
import net.devyy.yi.view.activity.HomeActivity;

/**
 * 应用启动页
 */
public class SplashActivity extends Activity {

    private static final int sleepTime = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.em_activity_splash);

        YIMHelper.getInstance().initHandler(this.getMainLooper());
    }

    @Override
    protected void onStart( ) {
        super.onStart();

        new Thread(new Runnable() {
            public void run( ) {
                // 如果已经登录
                if (YIMHelper.getInstance().isLoggedIn()) {
                    // auto login mode, make sure all group and conversation is loaed before enter the main screen
                    long start = System.currentTimeMillis();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    EMClient.getInstance().groupManager().loadAllGroups();
                    long costTime = System.currentTimeMillis() - start;
                    //wait
                    if (sleepTime - costTime > 0) {
                        try {
                            Thread.sleep(sleepTime - costTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

//                    String topActivityName = EasyUtils.getTopActivityName(EMClient.getInstance().getContext());
//                    if (topActivityName != null && (topActivityName.equals(VideoCallActivity.class.getName()) || topActivityName.equals(VoiceCallActivity.class.getName()) || topActivityName.equals(ConferenceActivity.class.getName()))) {
//                        // nop
//                        // avoid main screen overlap Calling Activity
//                    } else {
//                        // enter main screen
//                        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//                    }

                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));

                    finish();
                } else {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }).start();
    }
}
