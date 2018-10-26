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

    public TabDiscoveryFragment( ) {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_fragment_discovery, container, false);

        return v;
    }

}
