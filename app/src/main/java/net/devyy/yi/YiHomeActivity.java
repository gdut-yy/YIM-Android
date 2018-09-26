package net.devyy.yi;

import android.support.v4.app.Fragment;

public class YiHomeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment( ) {
        return new YIHomeFragment();
    }

}
