package com.example.android.questiontime2.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * A Quiz Class which models the Quiz table in database
 */
public class Quiz implements Parcelable {

    private String quizName;
    private List<Question> questionList;

    /**
     * Instantiates a new Quiz.
     *
     * @param quizName     the quiz name
     * @param questionList the question list
     */
    public Quiz(String quizName, List<Question> questionList) {
        this.quizName = quizName;
        this.questionList = questionList;
    }

    /**
     * Gets quiz name.
     *
     * @return the quiz name
     */
    public String getQuizName() {
        return quizName;
    }

    /**
     * Gets question list.
     *
     * @return the question list
     */
    public List<Question> getQuestionList() {
        return questionList;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quizName='" + quizName + '\'' +
                ", questionList=" + questionList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.quizName);
        dest.writeTypedList(this.questionList);
    }

    /**
     * Instantiates a new Quiz.
     *
     * @param in the in
     */
    protected Quiz(Parcel in) {
        this.quizName = in.readString();
        this.questionList = in.createTypedArrayList(Question.CREATOR);
    }

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<Quiz> CREATOR = new Parcelable.Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel source) {
            return new Quiz(source);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };


}
