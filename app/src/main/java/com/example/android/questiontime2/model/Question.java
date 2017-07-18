package com.example.android.questiontime2.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collections;
import java.util.List;

/**
 * A class to represent all questions with their answers, possible answers, topics,
 * and submitted answers.
 */
public class Question implements Parcelable {

    private String question;
    private String topic;
    private List<String> options;
    private List<String> answerList;
    private Type questionType;

    public Question(String question, String topic, List<String> options, List<String> answerList, Type questionType) {
        this.question = question;
        this.topic = topic;
        this.options = options;
        this.answerList = answerList;
        this.questionType = questionType;
    }

    /**
     * Gets question.
     *
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Gets topic.
     *
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Gets options.
     *
     * @return the options
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * Gets answerList.
     *
     * @return the answerList
     */
    public List<String> getAnswerList() {
        return answerList;
    }

    public Type getQuestionType() {
        return questionType;
    }

    public void shuffleOptions(){
        Collections.shuffle(this.options);
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", topic='" + topic + '\'' +
                ", options=" + options +
                ", answerList='" + answerList + '\'' +
                ", questionType=" + questionType +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.question);
        dest.writeString(this.topic);
        dest.writeStringList(this.options);
        dest.writeStringList(this.answerList);
        dest.writeParcelable(this.questionType, flags);
    }

    protected Question(Parcel in) {
        this.question = in.readString();
        this.topic = in.readString();
        this.options = in.createStringArrayList();
        this.answerList = in.createStringArrayList();
        this.questionType = in.readParcelable(Type.class.getClassLoader());
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}