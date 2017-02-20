package come.example.weinan.day56_yitingmusic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by weinan on 2017/2/15.
 */

 public class MyTabViewPagerAdapter extends FragmentPagerAdapter {
    Fragment[] fragments;
    String[] titleList;

    public MyTabViewPagerAdapter(FragmentManager fm, Fragment[] fragments,  String[] titleList) {
        super(fm);
        this.fragments = fragments;
        this.titleList = titleList;
    }

    public MyTabViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList[position];

    }
}
