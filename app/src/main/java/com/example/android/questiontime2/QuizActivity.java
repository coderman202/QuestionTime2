package com.example.android.questiontime2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.android.questiontime2.adapters.QuizPagerAdapter;
import com.example.android.questiontime2.adapters.QuizQuestionPagerAdapter;
import com.example.android.questiontime2.model.Question;
import com.example.android.questiontime2.model.Quiz;
import com.example.android.questiontime2.model.Results;
import com.example.android.questiontime2.utilities.QuizUtilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private QuizQuestionPagerAdapter quizQuestionPagerAdapter;
    private static QuizPagerAdapter quizPagerAdapter;

    public static List<Fragment> fragmentList = new ArrayList<>();


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public Quiz quiz;
    public static List<Question> questionList;

    public static List<String> answerList = new ArrayList<>();

    public static int scoreCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null){
            Bundle bundle = getIntent().getExtras();
            quiz = bundle.getParcelable("Quiz");

            // Shuffle all the questions and their options up.
            questionList = quiz.getQuestionList();
            Collections.shuffle(questionList);
            QuizUtilities.shuffleAllQuestionsOptions(questionList);

            // Set the default answer to be changed when an option is selected in the radio group.
            while(answerList.size() < questionList.size()) answerList.add("No answer");

            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            quizPagerAdapter = new QuizPagerAdapter(this, getSupportFragmentManager());

            for (int i = 0; i < questionList.size(); i++) {
                quizPagerAdapter.addFragment(PlaceholderFragment.newInstance(i + 1));

            }

            fragmentList = quizPagerAdapter.getFragmentList();
        }

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.quiz_container);
        mViewPager.setAdapter(quizPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onClick(View view){

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSaveInstanceState(Bundle outState){
        for (int i = 0; i < fragmentList.size(); i++) {
            getSupportFragmentManager().putFragment(outState,"fragment" + i, fragmentList.get(i));
        }

    }
    @Override
    public void onRestoreInstanceState(Bundle inState){
        int i = 0;

        while(getSupportFragmentManager().getFragment(inState,"fragment" + i)!= null){
            quizPagerAdapter.addFragment(getSupportFragmentManager().getFragment(inState,"fragment" + i));
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener{

        @BindView(R.id.question) TextView questionView;
        @BindView(R.id.options_group) RadioGroup optionsGroup;
        @BindView(R.id.submit_button) ImageView submitButton;

        private int sectionNumber;

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);

            sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            ButterKnife.bind(this, rootView);

            Question currentQuestion = questionList.get(sectionNumber - 1);
            final List<String> options = currentQuestion.getOptions();

            for(int i = 0; i < options.size(); i++) {
                RadioButton option = (RadioButton) optionsGroup.getChildAt(i);
                option.setText(options.get(i));
            }

            optionsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    switch(checkedId){
                        case R.id.option1:
                            answerList.set(sectionNumber - 1, options.get(0));
                            break;
                        case R.id.option2:
                            answerList.set(sectionNumber - 1, options.get(1));
                            break;
                        case R.id.option3:
                            answerList.set(sectionNumber - 1, options.get(2));
                            break;
                        case R.id.option4:
                            answerList.set(sectionNumber - 1, options.get(3));
                            break;
                    }
                }
            });

            questionView.setText(currentQuestion.getQuestion());

            if(sectionNumber == questionList.size()){
                submitButton.setVisibility(View.VISIBLE);
            }
            else{
                submitButton.setVisibility(View.GONE);
            }

            submitButton.setOnClickListener(this);

            return rootView;
        }

        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.submit_button:
                    ArrayList<Results> resultsList = new ArrayList<>();
                    for (int i = 0; i < questionList.size(); i++) {
                        Results results = new Results(questionList.get(i).getAnswer(), answerList.get(i));
                        resultsList.add(results);
                    }

                    Intent intent = new Intent(getActivity(), ScoreActivity.class);
                    intent.putParcelableArrayListExtra("Results", resultsList);
                    startActivity(intent);
            }
        }
    }
}