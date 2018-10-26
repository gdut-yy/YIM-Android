package net.devyy.yi.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import net.devyy.yi.R;
import net.devyy.yi.util.PopupWindowUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 主页 HomeFragment
 */
public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private List<Fragment> fragments;
    private BottomNavigationBar bottomNavigationBar;

    public static int statusBarHeight;  // 状态栏高度（最顶）
    public static int titleBarHeight;   // 标题栏高度（次顶）


    public static HomeFragment newInstance( ) {
        return new HomeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.module_fragment_home, container, false);

        fragments = new ArrayList<>(4);
        fragments.add(new TabChatsFragment());
        fragments.add(new TabContactsFragment());
        fragments.add(new TabDiscoveryFragment());
        fragments.add(new TabMimeFragment());

        viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        FragmentManager fragmentManager = getFragmentManager();
        viewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = fragments.get(position);
                return fragment;
            }


            @Override
            public int getCount( ) {
                return fragments.size();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        bottomNavigationBar = (BottomNavigationBar) v.findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });


        BottomNavigationItem tab1 = new BottomNavigationItem(R.drawable.ic_chat_active, R.string.yim_chat_tab)
                .setInactiveIcon(getResources().getDrawable(R.drawable.ic_chat_inactive));
        BottomNavigationItem tab2 = new BottomNavigationItem(R.drawable.ic_contact_active, R.string.yim_contact_tab)
                .setInactiveIcon(getResources().getDrawable(R.drawable.ic_contact_inactive));
        BottomNavigationItem tab3 = new BottomNavigationItem(R.drawable.ic_discovery_active, R.string.yim_discovery_tab)
                .setInactiveIcon(getResources().getDrawable(R.drawable.ic_discovery_inactive));
        BottomNavigationItem tab4 = new BottomNavigationItem(R.drawable.ic_mime_active, R.string.yim_mime_tab)
                .setInactiveIcon(getResources().getDrawable(R.drawable.ic_mime_inactive));


//        TextBadgeItem textBadgeItem = new TextBadgeItem().setTextColor(Color.WHITE).setText("99+");
//        badgeItem = new BadgeItem().setBackgroundColor(Color.RED).setText("99");
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
//        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
//        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar.setBarBackgroundColor(R.color.bottom_bar_background_color);
        bottomNavigationBar.setInActiveColor(R.color.bottom_bar_normal_color).setActiveColor(R.color.bottom_bar_pressed_color);
        bottomNavigationBar
//                .addItem(new BottomNavigationItem(R.drawable.ic_chat_tab, R.string.yim_chat_tab).setActiveColorResource(R.color.module_bottom_bar_pressed_color).setBadgeItem(textBadgeItem))
                .addItem(tab1)
                .addItem(tab2)
                .addItem(tab3)
                .addItem(tab4)
                .setFirstSelectedPosition(0)
                .initialise(); //所有的设置需在调用该方法前完成



        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.module_top_bar_menu, menu);

        // 获取状态栏高度，标题栏高度，不可移至 onCreateView 内实现
        statusBarHeight = getResources().getDimensionPixelSize(getResources().getIdentifier("status_bar_height", "dimen", "android"));
        titleBarHeight = getActivity().getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                // 显示或隐藏 popupwindow
                View menuView = View.inflate(getActivity(), R.layout.module_menu_list, null);
                View parentView = getActivity().getWindow().findViewById(Window.ID_ANDROID_CONTENT);
                PopupWindow popupWindow = PopupWindowUtils.getPopupWindowAtLocation(menuView, parentView, Gravity.TOP | Gravity.END, 30, titleBarHeight + statusBarHeight);

                Toast.makeText(getActivity(), "add", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);

            case R.id.search:
                Toast.makeText(getActivity(), "search", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
