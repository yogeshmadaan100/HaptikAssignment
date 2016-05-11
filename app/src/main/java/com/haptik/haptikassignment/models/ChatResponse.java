package com.haptik.haptikassignment.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yogeshmadaan on 11/05/16.
 */
public class ChatResponse implements Parcelable{
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("messages")
    @Expose
    private List<Message> messages = new ArrayList<Message>();

    protected ChatResponse(Parcel in) {
        count = in.readInt();
        messages = in.createTypedArrayList(Message.CREATOR);
    }

    public static final Creator<ChatResponse> CREATOR = new Creator<ChatResponse>() {
        @Override
        public ChatResponse createFromParcel(Parcel in) {
            return new ChatResponse(in);
        }

        @Override
        public ChatResponse[] newArray(int size) {
            return new ChatResponse[size];
        }
    };

    /**
     *
     * @return
     * The count
     */
    public int getCount() {
        return count;
    }

    /**
     *
     * @param count
     * The count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     *
     * @return
     * The messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     *
     * @param messages
     * The messages
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        dest.writeTypedList(messages);
    }
}
