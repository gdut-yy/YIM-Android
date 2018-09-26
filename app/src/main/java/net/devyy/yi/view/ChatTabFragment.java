package net.devyy.yi.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.devyy.yi.R;


public class ChatTabFragment extends Fragment {

    private final String TAG = "ConversationFragment";

//    private RecyclerView recyclerView;
//    private ChatAdapter chatAdapter;
//    private List<Conversation> conversationList = new LinkedList<>();
//    private ConversationAdapter adapter;
//    private ListView listView;

//    private ConversationPresenter presenter;
//    private FriendshipManagerPresenter friendshipManagerPresenter;
//    private GroupManagerPresenter groupManagerPresenter;
//    private List<String> groupList;
//    private FriendshipConversation friendshipConversation;
//    private GroupManageConversation groupManageConversation;

    public ChatTabFragment( ) {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.module_fragment_contact_tab, container, false);
//        recyclerView = (RecyclerView) view.findViewById(R.id.chat_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        updateUI();
//
        return view;
    }

//    private void updateUI( ) {
//        ChatLab chatLab = ChatLab.get(getActivity());
//        List<Chat> chatList = chatLab.getChats();
//
//        chatAdapter = new ChatAdapter(chatList);
//        recyclerView.setAdapter(chatAdapter);
//    }
//
//    // ViewHolder 内部类
//    private class ChatHolder extends RecyclerView.ViewHolder {
//
//        //        private CircleImageView civAvatar;
//        private TextView tvName;
//        private TextView tvLastMessage;
//        private TextView tvMessageTime;
//        private TextView tvUnreadNum;
//
//        private Chat mChat;
//
//        public ChatHolder(LayoutInflater inflater, ViewGroup parent) {
//            super(inflater.inflate(R.layout.module_recycler_item_chat, parent, false));
//
////            civAvatar = (CircleImageView) view.findViewById(R.id.avatar);
//            tvName = (TextView) itemView.findViewById(R.id.name);
//            tvLastMessage = (TextView) itemView.findViewById(R.id.last_message);
//            tvMessageTime = (TextView) itemView.findViewById(R.id.message_time);
//            tvUnreadNum = (TextView) itemView.findViewById(R.id.unread_num);
//        }
//
//        public void bind(Chat chat) {
//            mChat = chat;
//
//            tvName.setText(mChat.getName());
//            tvLastMessage.setText(mChat.getLastMessage());
//            tvMessageTime.setText(mChat.getTime());
//            tvUnreadNum.setText(mChat.getUnreadNum());
//        }
//    }
//
//    // Adapter 内部类
//    private class ChatAdapter extends RecyclerView.Adapter<ChatHolder> {
//
//        private List<Chat> mChats;
//
//        public ChatAdapter(List<Chat> chats) {
//            mChats = chats;
//        }
//
//        @Override
//        public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
//
//            return new ChatHolder(layoutInflater, parent);
//        }
//
//        @Override
//        public void onBindViewHolder(ChatHolder holder, int position) {
//            Chat chat = mChats.get(position);
//            holder.bind(chat);
//        }
//
//        @Override
//        public int getItemCount( ) {
//            return mChats.size();
//        }
//    }

}

