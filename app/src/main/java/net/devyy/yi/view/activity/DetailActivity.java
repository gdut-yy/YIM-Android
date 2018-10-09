package net.devyy.yi.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import net.devyy.yi.test.ContactTest;
import net.devyy.yi.view.DetailFragment;

public class DetailActivity extends SingleFragmentActivity {

    private static final String CONTACT = "contact";

    public static Intent newIntent(Context context, ContactTest contact) {
        Intent i = new Intent(context, DetailActivity.class);
        i.putExtra(CONTACT, contact);
        return i;
    }

    @Override
    protected Fragment createFragment( ) {
        return DetailFragment.newInstance((ContactTest) getIntent().getSerializableExtra(CONTACT));
    }

}
