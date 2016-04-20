package com.hyn.studioframe.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyn.baselibrary.avc.wight.tabstrip.PagerSlidingTabStrip;
import com.hyn.studioframe.R;

/**
 * Created by hyn on 2016/1/13.
 */
public class HomePageFragment extends Fragment {
    private static String[] TITLES;

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        pager = (ViewPager) view.findViewById(R.id.pager);
        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TITLES = getResources().getStringArray(R.array.news_titles);

        FragmentPagerAdapter adapter = new NewsAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, TITLES.length, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);

        tabs.setViewPager(pager);
    }

    class NewsAdapter extends FragmentPagerAdapter {
        public NewsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new HomeFragment();
            }
            if (position == 1) {
                return new OtherFragment();
            }
            if (position == 2) {
                return new MyFragment();
            }
            if (position == 3) {
                return new MyFragment();
            }
            if (position == 5) {
                return new MyFragment();
            }
            if (position == 7) {
                return new MyFragment();
            }

            return new HomeFragment();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position % TITLES.length];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
}
