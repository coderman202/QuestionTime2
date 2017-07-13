package com.example.android.questiontime2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.questiontime2.adapters.QuizAdapter;
import com.example.android.questiontime2.dbhelper.QuestionTimeDB;
import com.example.android.questiontime2.model.Quiz;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizListActivity extends AppCompatActivity {

    public static final String LOG_TAG = QuizListActivity.class.getSimpleName();

    @BindView(R.id.quiz_list) RecyclerView quizRecyclerView;

    private QuizAdapter quizAdapter;
    private List<Quiz> quizList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);

        ButterKnife.bind(this);

        initQuizRecyclerView();
    }

    /**
     * A method to initiate the quiz recycler view. Use a layout manager to set it to a vertical
     * orientation and use the HorizontalDividerItemDecoration Builder class to create a divider.
     * For more on the divider:
     * @see <a href="https://github.com/yqritc/RecyclerView-FlexibleDivider">RecyclerView-FlexibleDivider</a>
     */
    public void initQuizRecyclerView() {
        QuestionTimeDB questionTimeDB = new QuestionTimeDB(this);
        quizList = questionTimeDB.getAllQuizzes();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        quizRecyclerView.setLayoutManager(layoutManager);
        quizRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).
                color(Color.LTGRAY).sizeResId(R.dimen.quiz_list_item_divider_width).
                marginResId(R.dimen.quiz_list_item_divider_margin, R.dimen.quiz_list_item_divider_margin).build());


        quizAdapter = new QuizAdapter(this, quizList);
        quizRecyclerView.setAdapter(quizAdapter);
    }
}