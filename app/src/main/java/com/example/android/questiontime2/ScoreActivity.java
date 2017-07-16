package com.example.android.questiontime2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.questiontime2.adapters.ResultsAdapter;
import com.example.android.questiontime2.model.Results;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.replay) TextView replayView;
    @BindView(R.id.final_score) TextView finalScoreView;
    @BindView(R.id.results_list) RecyclerView resultsListView;

    ResultsAdapter adapter;

    List<Results> resultsList;

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        ButterKnife.bind(this);

        resultsList = getIntent().getParcelableArrayListExtra("Results");

        for (Results results:resultsList) {
            if(results.isCorrect()){
                score++;
            }
        }
        String scoreMessage = getString(R.string.final_score, score, resultsList.size());
        finalScoreView.setText(scoreMessage);

        adapter = new ResultsAdapter(this, resultsList);
        resultsListView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        resultsListView.setLayoutManager(layoutManager);
        resultsListView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).
                color(Color.LTGRAY).sizeResId(R.dimen.quiz_list_item_divider_width).
                marginResId(R.dimen.quiz_list_item_divider_margin, R.dimen.quiz_list_item_divider_margin).build());

        replayView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.replay:
                Intent intent = new Intent(this, QuizListActivity.class);
                startActivity(intent);
        }
    }
}
