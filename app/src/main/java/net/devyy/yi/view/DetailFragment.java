package net.devyy.yi.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.devyy.yi.R;
import net.devyy.yi.test.ContactTest;

/**
 * "联系人" -> "item" 详细资料页面
 */
public class DetailFragment extends Fragment {

    private static final String CONTACT_BEAN = "contact_bean";

    private ImageView ivHead;
    private TextView tvUserName;
    private TextView tvId;
    private TextView tvNickName;
    private TextView tvLocation;
    private ImageView ivImg1;
    private ImageView ivImg2;
    private ImageView ivImg3;

    private ContactTest mContact;

    public static DetailFragment newInstance(ContactTest contact) {
        Bundle args = new Bundle();
        args.putSerializable(CONTACT_BEAN, contact);

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContact = (ContactTest) getArguments().getSerializable(CONTACT_BEAN);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.module_fragment_detail, container, false);

        bindViews(v);


        return v;
    }

    private void bindViews(View v) {
        ivHead = (ImageView) v.findViewById(R.id.iv_detail_head);
        tvUserName = (TextView) v.findViewById(R.id.tv_detail_username);
        tvId = (TextView) v.findViewById(R.id.tv_detail_id);
        tvNickName = (TextView) v.findViewById(R.id.tv_detail_nickname);
        tvLocation = (TextView) v.findViewById(R.id.tv_location);
        ivImg1 = (ImageView) v.findViewById(R.id.iv_photo_1);
        ivImg2 = (ImageView) v.findViewById(R.id.iv_photo_2);
        ivImg3 = (ImageView) v.findViewById(R.id.iv_photo_3);

        final String id = "ID: " + mContact.getNameString();
        final String nick = "昵称: " + mContact.getNickName();

        ivHead.setImageDrawable(getResources().getDrawable(mContact.getUserImgId()));
        tvUserName.setText(mContact.getUserName());
        tvId.setText(id);
        tvNickName.setText(nick);
        tvLocation.setText(mContact.getLocation());
    }
}
