package net.devyy.yi.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import net.devyy.yi.test.ChatTest;
import net.devyy.yi.view.ChatFragment;

public class ChatActivity extends SingleFragmentActivity {

    private static final String CHAT = "chat";

    public static Intent newIntent(Context context, ChatTest chat) {
        Intent i = new Intent(context, ChatActivity.class);
        i.putExtra(CHAT, chat);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
    }

    @Override
    protected Fragment createFragment( ) {
        return ChatFragment.newInstance((ChatTest) getIntent().getSerializableExtra(CHAT));
    }

}
