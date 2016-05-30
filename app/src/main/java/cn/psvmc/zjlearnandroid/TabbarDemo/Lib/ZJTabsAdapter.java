package cn.psvmc.zjlearnandroid.TabbarDemo.Lib;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class ZJTabsAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {
    public ZJTabSelectDelegate delegate;
    private final Context mContext;
    private final ViewPager mViewPager;
    private final RadioGroup mTabRg;
    private final ArrayList<ZJTabInfo> mTabs;


    public ZJTabsAdapter(FragmentActivity activity, ViewPager pager, RadioGroup tabRg, final ArrayList<ZJTabInfo> mTabs) {
        super(activity.getSupportFragmentManager());

        this.mContext = activity;
        this.mViewPager = pager;
        this.mTabRg = tabRg;
        this.mTabs = mTabs;
        mViewPager.setOnPageChangeListener(this);
        mTabRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int itemIndex = 0;
                for (int i = 0; i < group.getChildCount(); i++) {
                    RadioButton button = (RadioButton) group.getChildAt(i);
                    if (button.getId() == checkedId) {
                        itemIndex = i;
                        break;
                    }
                }
                //防止XML中的按钮比对应的页面多
                if (itemIndex < mTabs.size()) {
                    mViewPager.setCurrentItem(itemIndex);
                }
            }
        });
        ((RadioButton) mTabRg.getChildAt(0)).setChecked(true);
        mViewPager.setCurrentItem(0);
        if(delegate != null){
            delegate.zjtabbarSelectItem(0);
        }
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public Fragment getItem(int position) {
        ZJTabInfo info = mTabs.get(position);
        return Fragment.instantiate(mContext, info.clss.getName(), info.args);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mViewPager.setCurrentItem(position);
        ((RadioButton) mTabRg.getChildAt(position)).setChecked(true);
        if(delegate != null){
            delegate.zjtabbarSelectItem(position);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
