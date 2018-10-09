package net.devyy.yi.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

import net.devyy.yi.view.WebViewFragment;

public class WebViewActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context, Uri photoPageUri) {
        Intent i = new Intent(context, WebViewActivity.class);
        i.setData(photoPageUri);
        return i;
    }

    @Override
    protected Fragment createFragment( ) {
        return WebViewFragment.newInstance(getIntent().getData());
    }
}
