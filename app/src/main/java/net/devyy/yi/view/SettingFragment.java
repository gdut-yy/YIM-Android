package net.devyy.yi.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;

import net.devyy.yi.R;
import net.devyy.yi.YIMHelper;
import net.devyy.yi.account.SplashActivity;

/**
 * "我" -> "设置" 页面
 */
public class SettingFragment extends Fragment {

    private TextView tvLogout;

    public static SettingFragment newInstance( ) {
        return new SettingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.discovery_fragment_setting, container, false);

        tvLogout = (TextView) v.findViewById(R.id.tv_setting_logout);
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        return v;
    }

    void logout( ) {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        String st = "退出登录";
        pd.setMessage(st);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        YIMHelper.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess( ) {
                getActivity().runOnUiThread(( ) -> {
                    pd.dismiss();
                    // show login screen
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), SplashActivity.class));

                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                getActivity().runOnUiThread(( ) -> {
                    pd.dismiss();
                    Toast.makeText(getActivity(), "unbind devicetokens failed", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
}
