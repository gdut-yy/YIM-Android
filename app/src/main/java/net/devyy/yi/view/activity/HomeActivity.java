package net.devyy.yi.view.activity;

import android.support.v4.app.Fragment;

import net.devyy.yi.view.HomeFragment;

public class HomeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment( ) {
        return HomeFragment.newInstance();
    }

}
