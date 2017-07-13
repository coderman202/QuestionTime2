package com.example.android.questiontime2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.replay) TextView replayView;
    @BindView(R.id.final_score) TextView finalScoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        ButterKnife.bind(this);

        String scoreMessage = getIntent().getStringExtra("Score");
        finalScoreView.setText(scoreMessage);

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
