package net.devyy.yi.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import net.devyy.yi.R;
import net.devyy.yi.emoticon.EmoticonKeyBoard;
import net.devyy.yi.emoticon.EmoticonLayout;
import net.devyy.yi.emoticon.IEmotionExtClickListener;
import net.devyy.yi.emoticon.IEmotionSelectedListener;
import net.devyy.yi.test.ChatTest;

import java.util.LinkedList;
import java.util.List;

/**
 * "YIM" -> "item" 聊天页面
 */
public class ChatFragment extends Fragment {

    private static final String TAG = "ChatFragment";
    private static final String TO_USER_NAME = "kolzb002";
    private static final String CHAT_BEAN = "chat_bean";

    private RecyclerView mRecyclerView;
    private ChatAdapter mAdapter;
    Handler handler = new Handler();

    // 底部输入框
    private ImageView ivVoice;
    private EditText etInput;
    private ImageView ivEmoji;
    private ImageView ivMore;
    private Button btnSend;

    // 自定义 Emoticon 控件
    private EmoticonKeyBoard mEmotionKeyboard;
    private LinearLayout mLlContent;
    private FrameLayout mFlEmotionView;
    private EmoticonLayout mElEmotion;
    private ConstraintLayout mLlMore;

    private ChatTest mChat;
    private List<EMMessage> mMessageList = new LinkedList<>();

    public static ChatFragment newInstance(ChatTest chat) {
        Bundle args = new Bundle();
        args.putSerializable(CHAT_BEAN, chat);

        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChat = (ChatTest) getArguments().getSerializable(CHAT_BEAN);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.module_fragment_chat, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_chat_content);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        bindView(v);
        bindListener();

        EMClient.getInstance().chatManager().addMessageListener(msgListener);
        updateUI();
        initEmotionKeyboard();

        return v;
    }

    private void updateUI( ) {

        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(TO_USER_NAME);
//        //获取此会话的所有消息
//        List<EMMessage> mMessageList = conversation.getAllMessages();

        mAdapter = new ChatAdapter(mMessageList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void bindView(View v) {
        ivVoice = (ImageView) v.findViewById(R.id.iv_chat_voice);
        etInput = (EditText) v.findViewById(R.id.et_chat_input);
        ivEmoji = (ImageView) v.findViewById(R.id.iv_chat_emoji);
        ivMore = (ImageView) v.findViewById(R.id.iv_chat_more);
        btnSend = (Button) v.findViewById(R.id.btn_chat_send);

        mLlContent = (LinearLayout) v.findViewById(R.id.llContent);
        mFlEmotionView = (FrameLayout) v.findViewById(R.id.flEmotionView);
        mElEmotion = (EmoticonLayout) v.findViewById(R.id.elEmotion);
        mLlMore = (ConstraintLayout) v.findViewById(R.id.llMore);
    }

    private void bindListener( ) {

        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etInput.getText().toString().trim().length() > 0) {
                    btnSend.setVisibility(View.VISIBLE);
                    ivMore.setVisibility(View.GONE);
//                    RongIMClient.getInstance().sendTypingStatus(mConversationType, mSessionId, TextMessage.class.getAnnotation(MessageTag.class).value());
                } else {
                    btnSend.setVisibility(View.GONE);
                    ivMore.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        // 发送按钮
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = etInput.getText().toString().trim();
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage(input, TO_USER_NAME);
//                //如果是群聊，设置chattype，默认是单聊
//                if (chatType == CHATTYPE_GROUP)
//                    message.setChatType(ChatType.GroupChat);

                message.setMessageStatusCallback(messageStatusCallback);

                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);
                etInput.setText("");

                mMessageList.add(message);
                refreshRecyclerView();

            }
        });

        mElEmotion.attachEditText(etInput);
        mElEmotion.setEmotionSelectedListener(new IEmotionSelectedListener() {
            @Override
            public void onEmojiSelected(String key) {
                Log.i(TAG,"onEmojiSelected() 调用");
            }

            @Override
            public void onStickerSelected(String categoryName, String stickerName, String stickerBitmapPath) {
                Log.i(TAG,"onStickerSelected() 调用");
            }
        });
        mElEmotion.setEmotionAddVisiable(true);
        mElEmotion.setEmotionSettingVisiable(true);
        mElEmotion.setEmotionExtClickListener(new IEmotionExtClickListener() {
            @Override
            public void onEmotionAddClick(View view) {
                Log.i(TAG,"onEmotionAddClick() 调用");
            }

            @Override
            public void onEmotionSettingClick(View view) {
                Log.i(TAG,"onEmotionSettingClick() 调用");
            }
        });
    }

    private void initEmotionKeyboard( ) {
        mEmotionKeyboard = EmoticonKeyBoard.with(getActivity());
        mEmotionKeyboard.bindToEditText(etInput);
        mEmotionKeyboard.bindToContent(mLlContent);
        mEmotionKeyboard.setEmotionLayout(mFlEmotionView);
        mEmotionKeyboard.bindToEmotionButton(ivEmoji, ivMore);
        mEmotionKeyboard.setOnEmotionButtonOnClickListener(view -> {
            switch (view.getId()) {
                case R.id.iv_chat_emoji:
                    handler.postDelayed(( ) -> mRecyclerView.scrollToPosition(mRecyclerView.getAdapter().getItemCount() - 1), 50);

//                    mRecyclerView.scrollToPosition(mAdapter.getItemCount());
//                    UIUtils.postTaskDelay(( ) -> mRvMsg.smoothMoveToPosition(mRvMsg.getAdapter().getItemCount() - 1), 50);
                    etInput.clearFocus();
                    if (!mElEmotion.isShown()) {
                        if (mLlMore.isShown()) {
                            mElEmotion.setVisibility(View.VISIBLE);
                            ivEmoji.setImageResource(R.drawable.chat_keyboard);
                            mLlMore.setVisibility(View.GONE);
//                            showEmotionLayout();
//                            hideMoreLayout();
//                            hideAudioButton();
                            return true;
                        }
                    } else if (mElEmotion.isShown() && !mLlMore.isShown()) {
                        // 置换成键盘按钮
                        ivEmoji.setImageResource(R.drawable.chat_emoji);
                        return false;
                    }
                    mElEmotion.setVisibility(View.VISIBLE);
                    ivEmoji.setImageResource(R.drawable.chat_keyboard);
                    mLlMore.setVisibility(View.GONE);
//                    showEmotionLayout();
//                    hideMoreLayout();
//                    hideAudioButton();
                    break;
                case R.id.iv_chat_more:
                    handler.postDelayed(( ) -> mRecyclerView.scrollToPosition(mRecyclerView.getAdapter().getItemCount() - 1), 50);
//                    mRecyclerView.scrollToPosition(mAdapter.getItemCount());
//                    UIUtils.postTaskDelay(( ) -> mRvMsg.smoothMoveToPosition(mRvMsg.getAdapter().getItemCount() - 1), 50);
                    etInput.clearFocus();
                    if (!mLlMore.isShown()) {
                        if (mElEmotion.isShown()) {
                            mLlMore.setVisibility(View.VISIBLE);
                            mElEmotion.setVisibility(View.GONE);
                            ivEmoji.setImageResource(R.drawable.chat_emoji);
//                            showMoreLayout();
//                            hideEmotionLayout();
//                            hideAudioButton();
                            return true;
                        }
                    }
                    mLlMore.setVisibility(View.VISIBLE);
                    mElEmotion.setVisibility(View.GONE);
                    ivEmoji.setImageResource(R.drawable.chat_emoji);
//                    showMoreLayout();
//                    hideEmotionLayout();
//                    hideAudioButton();
                    break;
            }
            return false;
        });
    }

    protected EMCallBack messageStatusCallback = new EMCallBack() {
        @Override
        public void onSuccess( ) {
            Log.i(TAG, "发送成功");
//            if(isMessageListInited) {
//                messageList.refresh();
//            }
        }

        @Override
        public void onError(int code, String error) {
            Log.i(TAG, "发送失败" + String.valueOf(code));
//            if(isMessageListInited) {
//                messageList.refresh();
//            }
        }

        @Override
        public void onProgress(int progress, String status) {
            Log.i(TAG, "发送中: " + progress);
//            if(isMessageListInited) {
//                messageList.refresh();
//            }
        }
    };

    private void refreshRecyclerView( ) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run( ) {
                mAdapter.notifyItemInserted(mMessageList.size() - 1);
                mRecyclerView.scrollToPosition(mMessageList.size() - 1);
            }
        });

    }

    private EMMessageListener msgListener = new EMMessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            // 收到消息
            Log.i(TAG, "收到消息");
            for (EMMessage emMessage : messages) {
                mMessageList.add(emMessage);
                refreshRecyclerView();
            }
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            Log.i(TAG, "收到透传消息");
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
            Log.i(TAG, "收到已读回执");
        }

        @Override
        public void onMessageDelivered(List<EMMessage> messages) {
            Log.i(TAG, "收到已送达回执");
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            Log.i(TAG, "消息状态变动");
        }
    };


    // 发送消息内部类
    public class SendMsgHolder extends RecyclerView.ViewHolder {

        private TextView tvSend;

        public SendMsgHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_text_send, parent, false));

            tvSend = (TextView) itemView.findViewById(R.id.tvText);
        }

        public void bind(EMMessage message) {
            String content = ((EMTextMessageBody) message.getBody()).getMessage();
            tvSend.setText(content);
        }
    }

    // 接收消息内部类
    public class ReceiveMsgHolder extends RecyclerView.ViewHolder {

        private TextView tvReceive;

        public ReceiveMsgHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_text_receive, parent, false));

            tvReceive = (TextView) itemView.findViewById(R.id.tvText);
        }

        public void bind(EMMessage message) {
            String content = ((EMTextMessageBody) message.getBody()).getMessage();
            tvReceive.setText(content);
        }
    }

    // RecyclerView.Adapter 内部类
    public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int MESSAGE_TYPE_RECV_TXT = 0;
        private static final int MESSAGE_TYPE_SENT_TXT = 1;

        private List<EMMessage> mEmMessages;

        public ChatAdapter(List<EMMessage> messages) {
            mEmMessages = messages;
        }

        @Override
        public int getItemViewType(int position) {
            EMMessage message = mEmMessages.get(position);
            if (message.direct() == EMMessage.Direct.SEND) {
                return MESSAGE_TYPE_SENT_TXT;
            } else {
                return MESSAGE_TYPE_RECV_TXT;
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == MESSAGE_TYPE_SENT_TXT) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                return new SendMsgHolder(layoutInflater, parent);
            } else {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                return new ReceiveMsgHolder(layoutInflater, parent);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            EMMessage message = mEmMessages.get(position);
            if (holder instanceof SendMsgHolder) {
                ((SendMsgHolder) holder).bind(message);
            } else {
                ((ReceiveMsgHolder) holder).bind(message);
            }

        }

        @Override
        public int getItemCount( ) {
            return mEmMessages.size();
        }
    }

    @Override
    public void onDestroy( ) {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }
}
