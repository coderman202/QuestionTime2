package com.example.android.questiontime2.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.questiontime2.QuizActivity;
import com.example.android.questiontime2.R;
import com.example.android.questiontime2.model.Quiz;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Reggie on 12/07/2017. A custom recycler view adapter for generating lists of quizzes
 */

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {

    private static final String LOG_TAG = QuizAdapter.class.getSimpleName();
    private List<Quiz> quizList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.quiz_name) TextView quizNameView;
        @BindView(R.id.num_questions) TextView quizNumQuestions;
        @BindView(R.id.quiz_icon) TextView quizIconView;
        @BindView(R.id.quiz_list_item) LinearLayout layout;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }


    }

    public QuizAdapter(Context context, List<Quiz> quizList){
        this.context = context;
        this.quizList = quizList;
    }


    /**
     * Setting the views for all the elements in the RecyclerView item.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Quiz quiz = quizList.get(position);
        int numQuestions = quiz.getQuestionList().size();
        GradientDrawable quizIconCircle = (GradientDrawable) holder.quizIconView.getBackground();

        final int holderPosition = position;

        holder.quizNameView.setText(quiz.getQuizName());
        holder.quizNumQuestions.setText(context.getString(R.string.num_questions, numQuestions));
        quizIconCircle.setColor(getIconBgColor());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, QuizActivity.class);
                intent.putExtra("Quiz", quizList.get(holderPosition));
                Log.d("intent", intent.toString());
                Log.d("context", context.toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return quizList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_list_item, parent, false);
        return new ViewHolder(view);
    }

    public int getIconBgColor(){
        int randomNum = ThreadLocalRandom.current().nextInt(1, 13);
        switch (randomNum){
            case 0:
                return ContextCompat.getColor(context, R.color.circle1);
            case 1:
                return ContextCompat.getColor(context, R.color.circle1);
            case 2:
                return ContextCompat.getColor(context, R.color.circle2);
            case 3:
                return ContextCompat.getColor(context, R.color.circle3);
            case 4:
                return ContextCompat.getColor(context, R.color.circle4);
            case 5:
                return ContextCompat.getColor(context, R.color.circle5);
            case 6:
                return ContextCompat.getColor(context, R.color.circle6);
            case 7:
                return ContextCompat.getColor(context, R.color.circle7);
            case 8:
                return ContextCompat.getColor(context, R.color.circle8);
            case 9:
                return ContextCompat.getColor(context, R.color.circle9);
            case 10:
                return ContextCompat.getColor(context, R.color.circle10);
            case 11:
                return ContextCompat.getColor(context, R.color.circle11);
            case 12:
                return ContextCompat.getColor(context, R.color.circle12);
            default:
                return ContextCompat.getColor(context, R.color.circle10);
        }
    }
}
