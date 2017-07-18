package com.example.android.questiontime2.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by Reggie on 16/07/2017.
 * Custom answer class to store the user answer and the correct answer for display in the results
 * list on the final screen.
 * Created to allow for easy pass via intent and to be used with the custom adapter for ease for
 * display as opposed to passed a 2d list of strings or something like that.
 */
public class Results implements Parcelable {

    private List<String> correctAnswer;

    private List<String> userAnswer;

    public Results(List<String> correctAnswer, List<String> userAnswer) {
        this.correctAnswer = correctAnswer;
        this.userAnswer = userAnswer;
    }

    public String getCorrectAnswer() {
        return TextUtils.join(", ", correctAnswer);
    }


    public String getUserAnswer() {
        return TextUtils.join(", ", userAnswer);
    }

    public boolean isCorrect(){
        Collections.sort(correctAnswer);
        Collections.sort(userAnswer);
        return this.correctAnswer.equals(this.userAnswer);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.correctAnswer);
        dest.writeStringList(this.userAnswer);
    }

    protected Results(Parcel in) {
        this.correctAnswer = in.createStringArrayList();
        this.userAnswer = in.createStringArrayList();
    }

    public static final Creator<Results> CREATOR = new Creator<Results>() {
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
