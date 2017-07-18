package com.example.android.questiontime2.adapters;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reggie on 17/07/2017.
 * A custom pager adapter to handle screen rotation better.
 * Solution code was adapted from here:
 *
 * @see <a href="https://stackoverflow.com/a/21517213/6162963">Stackoverflow handling screen rotation with viewpager</a>
 */

public class QuizPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();

    public QuizPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public List<Fragment> getFragmentList() {
        return fragmentList;
    }

    public void addFragment(Fragment fragment) {
        this.fragmentList.add(fragment);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Question " + (position + 1);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object fragment) {
        return ((Fragment) fragment).getView() == view;
    }

    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

}
