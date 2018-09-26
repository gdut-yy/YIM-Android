package net.devyy.yi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;

import net.devyy.yi.view.ChatTabFragment;
import net.devyy.yi.view.ContactTabFragment;
import net.devyy.yi.view.DiscoveryTabFragment;
import net.devyy.yi.view.SettingTabFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * 主页 YIHomeFragment
 */
public class YIHomeFragment extends Fragment {

    private ViewPager viewPager;
    private List<Fragment> fragments;

    private BottomNavigationBar bottomNavigationBar;
    private TextBadgeItem textBadgeItem;

    public static YIHomeFragment newInstance( ) {
        return new YIHomeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.module_fragment_yimhome, container, false);

        fragments = new ArrayList<>();
        fragments.add(new ChatTabFragment());
        fragments.add(new ContactTabFragment());
        fragments.add(new DiscoveryTabFragment());
        fragments.add(new SettingTabFragment());

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
        textBadgeItem = new TextBadgeItem().setTextColor(Color.WHITE).setText("99+");
//        badgeItem = new BadgeItem().setBackgroundColor(Color.RED).setText("99");
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setBarBackgroundColor(R.color.YimDark);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_chat_tab, R.string.yim_chat_tab).setActiveColorResource(R.color.YimGreen).setBadgeItem(textBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_contact_tab, R.string.yim_contact_tab).setActiveColorResource(R.color.YimGreen))
                .addItem(new BottomNavigationItem(R.drawable.ic_discovery_tab, R.string.yim_discovery_tab).setActiveColorResource(R.color.YimGreen))
                .addItem(new BottomNavigationItem(R.drawable.ic_setting_tab, R.string.yim_setting_tab).setActiveColorResource(R.color.YimGreen))
                .setFirstSelectedPosition(0)
                .initialise(); //所有的设置需在调用该方法前完成

        return v;
    }


}
