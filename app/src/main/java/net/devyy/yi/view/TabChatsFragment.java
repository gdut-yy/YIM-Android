package net.devyy.yi.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.devyy.yi.R;
import net.devyy.yi.test.ChatTest;
import net.devyy.yi.test.SingletonData;
import net.devyy.yi.view.activity.ChatActivity;

import java.util.List;

/**
 * "YIM" 页面
 */
public class TabChatsFragment extends Fragment {

    private final String TAG = "TabChatsFragment";

    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
//    private List<Conversation> conversationList = new LinkedList<>();
//    private ConversationAdapter adapter;
//    private ListView listView;

//    private ConversationPresenter presenter;
//    private FriendshipManagerPresenter friendshipManagerPresenter;
//    private GroupManagerPresenter groupManagerPresenter;
//    private List<String> groupList;
//    private FriendshipConversation friendshipConversation;
//    private GroupManageConversation groupManageConversation;

    public TabChatsFragment( ) {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_fragment_chats, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.chat_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        updateUI();
//
        return view;
    }

    private void updateUI( ) {
        SingletonData chatLab = SingletonData.get(getActivity());
        List<ChatTest> chatList = chatLab.getChats();

        chatAdapter = new ChatAdapter(chatList);
        recyclerView.setAdapter(chatAdapter);
    }

    // ViewHolder 内部类
    private class ChatHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //        private CircleImageView civAvatar;
        private TextView tvName;
        private TextView tvLastMessage;
        private TextView tvMessageTime;

        private ChatTest mChat;

        public ChatHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.chats_recycler_item, parent, false));

//            civAvatar = (CircleImageView) view.findViewById(R.id.avatar);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvLastMessage = (TextView) itemView.findViewById(R.id.tv_last_message);
            tvMessageTime = (TextView) itemView.findViewById(R.id.tv_message_time);

            itemView.setOnClickListener(this);
        }

        public void bind(ChatTest chat) {
            mChat = chat;

            tvName.setText(mChat.getUserName());
            tvLastMessage.setText(mChat.getLastMessage());
            tvMessageTime.setText(mChat.getTime());
        }

        @Override
        public void onClick(View view) {
            Log.i(TAG, mChat.getUserName() + " onClick()");
            Intent i = ChatActivity.newIntent(getActivity(), mChat);
            startActivity(i);
        }
    }

    // Adapter 内部类
    private class ChatAdapter extends RecyclerView.Adapter<ChatHolder> {

        private List<ChatTest> mChats;

        public ChatAdapter(List<ChatTest> chats) {
            mChats = chats;
        }

        @Override
        public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ChatHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ChatHolder holder, int position) {
            ChatTest chat = mChats.get(position);
            holder.bind(chat);
        }

        @Override
        public int getItemCount( ) {
            return mChats.size();
        }
    }

}

