package net.devyy.yi.view.activity;

import android.support.v4.app.Fragment;

import net.devyy.yi.view.SettingFragment;

public class SettingActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment( ) {
        return SettingFragment.newInstance();
    }

}
