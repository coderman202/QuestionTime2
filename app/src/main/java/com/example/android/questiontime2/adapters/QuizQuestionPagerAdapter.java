package com.example.android.questiontime2.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.questiontime2.QuizActivity;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class QuizQuestionPagerAdapter extends FragmentPagerAdapter {

    private QuizActivity quizActivity;

    public QuizQuestionPagerAdapter(QuizActivity quizActivity, FragmentManager fm) {
        super(fm);
        this.quizActivity = quizActivity;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return QuizActivity.PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show number of pages equal to number of questions.
        return quizActivity.quiz.getQuestionList().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Question " + (position + 1);
    }
}
