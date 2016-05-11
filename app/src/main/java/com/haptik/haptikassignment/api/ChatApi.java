package com.haptik.haptikassignment.api;

import com.haptik.haptikassignment.models.ChatResponse;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by yogeshmadaan on 11/05/16.
 */
public interface ChatApi {
    @GET("/test_data")
    void fetchChats(Callback<ChatResponse> chatResponseCallback);
}
