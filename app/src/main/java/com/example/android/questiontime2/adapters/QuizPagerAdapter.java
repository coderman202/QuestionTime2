package com.example.android.questiontime2.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.questiontime2.QuizActivity;
import com.example.android.questiontime2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reggie on 17/07/2017.
 * A custom pager adapter to handle screen rotation better.
 * Solution code was adapted from here:
 * @see <a href="https://stackoverflow.com/a/21517213/6162963">Stackoverflow handling screen rotation with viewpager</a>
 *
 */

public class QuizPagerAdapter extends PagerAdapter {

    private FragmentManager fragmentManager;
    private QuizActivity quizActivity;
    private List<Fragment> fragmentList = new ArrayList<>();

    public QuizPagerAdapter(QuizActivity quizActivity, FragmentManager fm){
        fragmentManager = fm;
        this.quizActivity = quizActivity;
    }

    public List<Fragment> getFragmentList() {
        return fragmentList;
    }

    public void addFragment(Fragment fragment) {
        this. fragmentList.add(fragment);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        assert(0 <= position && position < fragmentList.size());
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.remove(fragmentList.get(position));
        trans.commit();
        fragmentList.remove(position);
    }

    @Override
    public Fragment instantiateItem(ViewGroup container, int position){
        Fragment fragment = getItem(position);
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.add(container.getId(),fragment,"fragment:"+position);
        trans.commit();
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return quizActivity.getString(R.string.page_title, position + 1);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object fragment) {
        return ((Fragment) fragment).getView() == view;
    }

    public Fragment getItem(int position){
        assert(0 <= position && position < fragmentList.size());
        if(fragmentList.get(position) == null){
            fragmentList.add(position, QuizActivity.PlaceholderFragment.newInstance(position + 1));
        }
        return fragmentList.get(position);
    }

}
