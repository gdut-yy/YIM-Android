package net.devyy.yi.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.devyy.yi.R;
import net.devyy.yi.test.ContactTest;
import net.devyy.yi.test.SingletonData;
import net.devyy.yi.view.activity.DetailActivity;

import java.util.List;


/**
 * "联系人" 页面
 */
public class TabContactsFragment extends Fragment {

    private static final String TAG = "TabContactsFragment";

    private static final int POSITION_NOT_FOUND = -1;

    private RecyclerView mRrecyclerView;
    private ContactAdapter mContactAdapter;
    private ContactsSlideBar mSlideBar;
    private TextView mTVLetter;
    // 测试
    LinearLayoutManager mLinearLayoutManager;



    public TabContactsFragment( ) {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab_fragment_contacts, container, false);

        mRrecyclerView = (RecyclerView) v.findViewById(R.id.rv_contact);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRrecyclerView.setLayoutManager(mLinearLayoutManager);
        mSlideBar = (ContactsSlideBar) v.findViewById(R.id.slidebar_contact);
        mTVLetter = (TextView) v.findViewById(R.id.tv_letter);
        mSlideBar.setSlideBarChangedListener(new ContactsSlideBar.OnSlideBarChangedListener() {
            @Override
            public void onSectionChange(int index, String section) {
                mTVLetter.setVisibility(View.VISIBLE);
                mTVLetter.setText(section);
                int sectionPosition = mContactAdapter.getSectionPosition(section);

                if (sectionPosition != POSITION_NOT_FOUND) {
//                    mRrecyclerView.scrollToPosition(mContactAdapter.getItemCount());
//                    mRrecyclerView.scrollToPosition(sectionPosition);
////                    mRrecyclerView.smoothScrollToPosition(sectionPosition);
                    mRrecyclerView.scrollToPosition(sectionPosition);
                    mLinearLayoutManager.scrollToPositionWithOffset(sectionPosition, 0);

//                    int firstItem = mLinearLayoutManager.findFirstVisibleItemPosition();
//                    int lastItem = mLinearLayoutManager.findLastVisibleItemPosition();
//                    if (sectionPosition <= firstItem) {
//                        mRrecyclerView.scrollToPosition(sectionPosition);
//                    } else if (sectionPosition <= lastItem) {
//                        int top = mRrecyclerView.getChildAt(sectionPosition - firstItem).getTop();
//                        mRrecyclerView.scrollBy(0, top);
//                    } else {
//                        int top = mLinearLayoutManager.findViewByPosition(sectionPosition).getTop();
//                        mRrecyclerView.scrollBy(0, top);
//                    }
                }


            }

            @Override
            public void onSlidingFinish( ) {
                mTVLetter.setVisibility(View.GONE);
            }
        });

        updateUI();

        return v;
    }


    private void updateUI( ) {
        SingletonData data = SingletonData.get(getActivity());
        List<ContactTest> contactList = data.getContacts();

        mContactAdapter = new ContactAdapter(contactList);
        mRrecyclerView.setAdapter(mContactAdapter);
    }

    // 头部内容 内部类
    private class HeaderHolder extends RecyclerView.ViewHolder {

        public HeaderHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.contacts_recycler_item_header, parent, false));
        }
    }

    // 中间内容 内部类
    private class ContactHolder extends RecyclerView.ViewHolder {

        private TextView mTVContactSection;
        private ImageView mIVContactImg;
        private TextView mTVContactName;
        private LinearLayout mLayout;

        private ContactTest mContact;

        public ContactHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.contacts_recycler_item_content, parent, false));

            mTVContactSection = (TextView) itemView.findViewById(R.id.tv_contact_section);
            mIVContactImg = (ImageView) itemView.findViewById(R.id.iv_contact_img);
            mTVContactName = (TextView) itemView.findViewById(R.id.tv_contact_name);
            mLayout = (LinearLayout) itemView.findViewById(R.id.ll_item);

            mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, mContact.getUserName() + " onClick()");
                    Intent i = DetailActivity.newIntent(getActivity(), mContact);
                    startActivity(i);
                }
            });
        }

        public void bind(ContactTest contact) {
            mContact = contact;

            if (mContact.isShowSection()) {
                mTVContactSection.setVisibility(View.VISIBLE);
                mTVContactSection.setText(mContact.getFirstLetterString());
            } else {
                mTVContactSection.setVisibility(View.GONE);
            }
            mIVContactImg.setImageDrawable(getResources().getDrawable(mContact.getUserImgId()));
            mTVContactName.setText(mContact.getUserName());
        }


    }

    // 尾部内容
    private class FooterHolder extends RecyclerView.ViewHolder {

        private TextView mTVFootNum;

        public FooterHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.contacts_recycler_item_footer, parent, false));

            mTVFootNum = (TextView) itemView.findViewById(R.id.tv_contact_foot);
        }

        public void bind(int num) {
            final String s = num + "位联系人";
            mTVFootNum.setText(s);
        }
    }

    // Adapter 内部类
    private class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<ContactTest> mContacts;

        public ContactAdapter(List<ContactTest> contacts) {
            mContacts = contacts;
        }

        //        @NonNull
//        @Override
//        public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
//
//            return new ContactHolder(layoutInflater, parent);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
//            ContactTest contact = mContacts.get(position);
//            holder.bind(contact);
//        }
//
//        @Override
//        public int getItemCount( ) {
//            return mContacts.size();
//        }
        private static final int TYPE_HEAD = 1;
        private static final int TYPE_MID = 2;
        private static final int TYPE_FOOT = 3;

        public int getSectionPosition(String section) {
            if (section.equals("↑") || section.equals("☆")) {
                return 0;
            } else {
                for (int i = 0; i < mContacts.size(); i++) {
                    if (section.equals(mContacts.get(i).getFirstLetterString())) {
                        return i + 1;
                    }
                }
                return POSITION_NOT_FOUND;
            }

        }

        @Override
        public int getItemViewType(int position) {
            int cnt = mContacts.size();
            if (position == 0) {
                return TYPE_HEAD;
            } else if (position == cnt + 1) {
                return TYPE_FOOT;
            } else {
                return TYPE_MID;
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == TYPE_HEAD) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                return new HeaderHolder(layoutInflater, parent);
            } else if (viewType == TYPE_MID) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                return new ContactHolder(layoutInflater, parent);
            } else {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                return new FooterHolder(layoutInflater, parent);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof HeaderHolder) {

            } else if (holder instanceof ContactHolder) {
                ContactTest contact = mContacts.get(position - 1);  // 减去头部的，一共 1
                ((ContactHolder) holder).bind(contact);
            } else {
                ((FooterHolder) holder).bind(mContacts.size());
            }
        }

        @Override
        public int getItemCount( ) {
            return mContacts.size() + 2;    // 加上头部尾部，一共 2
        }
    }


}
