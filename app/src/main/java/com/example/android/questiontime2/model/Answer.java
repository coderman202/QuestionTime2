package com.example.android.questiontime2.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Reggie on 18/07/2017.
 * Custom class to easily store answers.
 */
public class Answer implements Parcelable {

    private List<String> answerList;

    /**
     * Instantiates a new Answer.
     *
     * @param answerList the answer list
     */
    public Answer(List<String> answerList) {
        this.answerList = answerList;
    }

    /**
     * Gets answer list.
     *
     * @return the answer list
     */
    public List<String> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<String> answerList) {
        this.answerList = answerList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.answerList);
    }

    /**
     * Instantiates a new Answer.
     *
     * @param in the in
     */
    protected Answer(Parcel in) {
        this.answerList = in.createStringArrayList();
    }

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<Answer> CREATOR = new Parcelable.Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel source) {
            return new Answer(source);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };
}
