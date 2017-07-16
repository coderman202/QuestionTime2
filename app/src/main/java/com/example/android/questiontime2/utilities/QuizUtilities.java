package com.example.android.questiontime2.utilities;

import com.example.android.questiontime2.model.Question;

import java.util.List;

/**
 * Created by Reggie on 16/07/2017.
 * A quick utility class.
 */

public final class QuizUtilities {

    private QuizUtilities(){}

    public static void shuffleAllQuestionsOptions(List<Question> questionList){
        for (Question question:questionList) {
            question.shuffleOptions();
        }
    }
}
