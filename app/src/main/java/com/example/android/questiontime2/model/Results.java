package com.example.android.questiontime2.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Reggie on 16/07/2017.
 * Custom answer class to store the user answer and the correct answer for display in the results
 * list on the final screen.
 * Created to allow for easy pass via intent and to be used with the custom adapter for ease for
 * display as opposed to passed a 2d list of strings or something like that.
 */
public class Results implements Parcelable {

    private String correctAnswer;

    private String userAnswer;

    /**
     * Instantiates a new Results.
     *
     * @param correctAnswer the correct answer
     * @param userAnswer    the user answer
     */
    public Results(String correctAnswer, String userAnswer) {
        this.correctAnswer = correctAnswer;
        this.userAnswer = userAnswer;
    }

    /**
     * Gets correct answer.
     *
     * @return the correct answer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Gets user answer.
     *
     * @return the user answer
     */
    public String getUserAnswer() {
        return userAnswer;
    }

    public boolean isCorrect(){
        return this.correctAnswer.equals(this.userAnswer);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.correctAnswer);
        dest.writeString(this.userAnswer);
    }

    /**
     * Instantiates a new Results.
     *
     * @param in the in
     */
    protected Results(Parcel in) {
        this.correctAnswer = in.readString();
        this.userAnswer = in.readString();
    }

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<Results> CREATOR = new Parcelable.Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel source) {
            return new Results(source);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };
}
