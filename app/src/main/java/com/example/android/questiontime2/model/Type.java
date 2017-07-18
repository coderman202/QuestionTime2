package com.example.android.questiontime2.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Reggie on 12/07/2017. A custom class to represent the types of questions such as true
 * or false or multiple choice.
 */
public class Type implements Parcelable {

    private int typeID;
    private String name;
    private String instructions;

    /**
     * Instantiates a new Type.
     *
     * @param name         the name
     * @param instructions the instructions
     */
    public Type(int typeID, String name, String instructions) {
        this.typeID = typeID;
        this.name = name;
        this.instructions = instructions;
    }

    public int getTypeID() {
        return typeID;
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
     * Gets instructions.
     *
     * @return the instructions
     */
    public String getInstructions() {
        return instructions;
    }

    @Override
    public String toString() {
        return "Type{" +
                "typeID=" + typeID +
                ", name='" + name + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.typeID);
        dest.writeString(this.name);
        dest.writeString(this.instructions);
    }

    protected Type(Parcel in) {
        this.typeID = in.readInt();
        this.name = in.readString();
        this.instructions = in.readString();
    }

    public static final Creator<Type> CREATOR = new Creator<Type>() {
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
