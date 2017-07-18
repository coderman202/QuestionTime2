package com.example.android.questiontime2.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.questiontime2.R;
import com.example.android.questiontime2.model.Results;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Reggie on 16/07/2017.
 * Custom adapter for displaying a list of the results.
 */

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ViewHolder> {

    private static final String LOG_TAG = ResultsAdapter.class.getSimpleName();
    private List<Results> resultsList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.answer)
        TextView answerView;
        @BindView(R.id.user_answer)
        TextView userAnswerView;
        @BindView(R.id.right_wrong)
        ImageView rightWrongView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


    }

    public ResultsAdapter(Context context, List<Results> resultsList) {
        this.context = context;
        this.resultsList = resultsList;
    }


    /**
     * Setting the views for all the elements in the RecyclerView item.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Results results = resultsList.get(position);

        holder.answerView.setText(results.getCorrectAnswer());
        holder.userAnswerView.setText(results.getUserAnswer());

        int drawable = R.drawable.wrong;
        if (results.isCorrect()) {
            drawable = R.drawable.right;
            holder.userAnswerView.setTextColor(ContextCompat.getColor(context, R.color.right_color));
        }

        holder.rightWrongView.setImageResource(drawable);
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.results_list_item, parent, false);
        return new ViewHolder(view);
    }
}
