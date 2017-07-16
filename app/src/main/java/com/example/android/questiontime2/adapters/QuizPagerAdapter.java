package com.example.android.questiontime2.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.questiontime2.QuizActivity;
import com.example.android.questiontime2.R;

/**
 * Created by Reggie on 17/07/2017.
 * A custom pager adapter to handle screen rotation better.
 * Solution code was adapted from here:
 * @see <a href="https://stackoverflow.com/a/21517213/6162963">Stackoverflow handling screen rotation with viewpager</a>
 *
 */

public class QuizPagerAdapter extends PagerAdapter {

    private FragmentManager fragmentManager;
    private Fragment[] fragments;
    private QuizActivity quizActivity;

    public QuizPagerAdapter(QuizActivity quizActivity, FragmentManager fm){
        fragmentManager = fm;
        this.quizActivity = quizActivity;
        fragments = new Fragment[quizActivity.quiz.getQuestionList().size()];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        assert(0 <= position && position < fragments.length);
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.remove(fragments[position]);
        trans.commit();
        fragments[position] = null;
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
        return fragments.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object fragment) {
        return ((Fragment) fragment).getView() == view;
    }

    public Fragment getItem(int position){
        assert(0 <= position && position < fragments.length);
        if(fragments[position] == null){
            fragments[position] = QuizActivity.PlaceholderFragment.newInstance(position + 1);
        }
        return fragments[position];
    }

}
