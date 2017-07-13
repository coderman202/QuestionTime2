package com.example.android.questiontime2.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Reggie on 12/07/2017. A custom class to represent the types of questions such as true
 * or false or multiple choice.
 */
public class Type implements Parcelable {

    private String name;
    private String instructions;

    /**
     * Instantiates a new Type.
     *
     * @param name         the name
     * @param instructions the instructions
     */
    public Type(String name, String instructions) {
        this.name = name;
        this.instructions = instructions;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets instructions.
     *
     * @return the instructions
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Sets instructions.
     *
     * @param instructions the instructions
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + name + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.instructions);
    }

    /**
     * Instantiates a new Type.
     *
     * @param in the in
     */
    protected Type(Parcel in) {
        this.name = in.readString();
        this.instructions = in.readString();
    }

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<Type> CREATOR = new Parcelable.Creator<Type>() {
        @Override
        public Type createFromParcel(Parcel source) {
            return new Type(source);
        }

        @Override
        public Type[] newArray(int size) {
            return new Type[size];
        }
    };
}
