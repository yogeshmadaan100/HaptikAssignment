package com.haptik.haptikassignment.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yogeshmadaan on 11/05/16.
 */
public class Message {
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("image-url")
    @Expose
    private String imageUrl;
    @SerializedName("message-time")
    @Expose
    private String messageTime;

    /**
     *
     * @return
     * The body
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * @param body
     * The body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     * The Name
     */
    public String getName() {
        return Name;
    }

    /**
     *
     * @param Name
     * The Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     *
     * @return
     * The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     *
     * @param imageUrl
     * The image-url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     *
     * @return
     * The messageTime
     */
    public String getMessageTime() {
        return messageTime;
    }

    /**
     *
     * @param messageTime
     * The message-time
     */
    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
}
