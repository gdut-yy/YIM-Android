package net.devyy.yi.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import net.devyy.yi.R;


/**
 * "发现" 页面
 */
public class TabDiscoveryFragment extends Fragment {

//    private Button mButton;

    public TabDiscoveryFragment( ) {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.tab_fragment_discovery, container, false);

//        mButton = (Button) v.findViewById(R.id.bt_github);

//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = WebViewActivity.newIntent(getActivity(), Uri.parse("https://github.com/gdut-yy"));
//                startActivity(i);
//            }
//        });

        return v;
    }

}
