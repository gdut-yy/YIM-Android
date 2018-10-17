package net.devyy.yi.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.devyy.yi.R;

/**
 * 支持自定义 Toolbar
 */
public abstract class BaseFragment extends Fragment {


    protected ImageView ivToolbarSearch;
    protected ImageView ivToolbarAdd;
    protected ImageView ivToolbarBack;
    protected ImageView ivToolbarMore;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.include_toolbar,container,false);



        return v;
    }
}
