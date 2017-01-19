package edu.xtu.androidbase.weaher.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by huilin on 2016/11/21.
 */
public class HomeViewPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public HomeViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setDatas(Collection<Fragment> fragments, Collection<String> titles){
        this.fragments.clear();
        this.titles.clear();
        if(fragments!=null){
            this.fragments.addAll(fragments);
        }
        if(titles!=null){
            this.titles.addAll(titles);
        }
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
