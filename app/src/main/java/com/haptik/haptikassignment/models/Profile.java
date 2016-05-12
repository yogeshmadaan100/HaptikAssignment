package com.haptik.haptikassignment.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yogeshmadaan on 11/05/16.
 */
public class Profile implements Parcelable{
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("image-url")
    @Expose
    private String imageUrl;
    @SerializedName("message-count")
    @Expose
    private int messageCount;

    public Profile()
    {

    }
    protected Profile(Parcel in) {
        Name = in.readString();
        imageUrl = in.readString();
        messageCount = in.readInt();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(imageUrl);
        dest.writeInt(messageCount);
    }
}
