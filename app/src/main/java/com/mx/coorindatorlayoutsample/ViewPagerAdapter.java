package com.mx.coorindatorlayoutsample;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 2016/5/29.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    public static final int  PAGE_COUNT=5;
    private String tabTitles[]=new String[]{"","分享","收藏","关注","关注者"};
    private Context mContext;
    public ViewPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.getInstance(position+1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
