package net.devyy.yi.view;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import net.devyy.yi.R;
import net.devyy.yi.view.activity.SettingActivity;
import net.devyy.yi.view.activity.WebViewActivity;

/**
 * "我" 页面
 */
public class TabMimeFragment extends Fragment implements View.OnClickListener {

    private LinearLayout llSetting;
    private LinearLayout llCSDN;
    private LinearLayout llGitee;
    private LinearLayout llGithub;

    public TabMimeFragment( ) {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_fragment_mime, container, false);

        llSetting = (LinearLayout) v.findViewById(R.id.ll_shezhi);
        llCSDN = (LinearLayout) v.findViewById(R.id.ll_csdn);
        llGitee = (LinearLayout) v.findViewById(R.id.ll_gitee);
        llGithub = (LinearLayout) v.findViewById(R.id.ll_github);

        llSetting.setOnClickListener(this);
        llCSDN.setOnClickListener(this);
        llGitee.setOnClickListener(this);
        llGithub.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.ll_shezhi:
                i = new Intent(getActivity(), SettingActivity.class);
                startActivity(i);
                break;
            case R.id.ll_csdn:
                i = WebViewActivity.newIntent(getActivity(), Uri.parse("https://blog.csdn.net/gdut_yy"));
                startActivity(i);
                break;
            case R.id.ll_gitee:
                i = WebViewActivity.newIntent(getActivity(), Uri.parse("https://gitee.com/gdut_yy"));
                startActivity(i);
                break;
            case R.id.ll_github:
                i = WebViewActivity.newIntent(getActivity(), Uri.parse("https://github.com//gdut-yy"));
                startActivity(i);
                break;
        }
    }
}
