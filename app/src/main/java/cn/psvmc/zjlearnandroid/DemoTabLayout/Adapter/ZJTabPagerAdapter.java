package cn.psvmc.zjlearnandroid.DemoTabLayout.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import cn.psvmc.zjlearnandroid.DemoTabbar.M.ZJTabInfo;

/**
 * Created by PSVMC on 16/7/15.
 */
public class ZJTabPagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<ZJTabInfo> mTabs;
    private final Context mContext;

    public ZJTabPagerAdapter(FragmentActivity activity, final ArrayList<ZJTabInfo> mTabs) {
        super(activity.getSupportFragmentManager());
        this.mContext = activity;
        this.mTabs = mTabs;

    }

    @Override
    public Fragment getItem(int position) {
        ZJTabInfo info = mTabs.get(position);
        Fragment fragment = Fragment.instantiate(mContext, info.clss.getName(), info.args);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position).title;
    }
}
