package com.haptik.haptikassignment.services;

import android.content.Context;

import com.haptik.haptikassignment.api.ChatApi;
import com.haptik.haptikassignment.generators.ServiceGenerator;
import com.haptik.haptikassignment.models.ChatResponse;

import retrofit.Callback;

/**
 * Created by yogeshmadaan on 11/05/16.
 */
public class ChatService {

    public ChatApi chatApi = null;
    private Context context = null;
    public ChatService(Context context)
    {
        this.context = context;
        chatApi = ServiceGenerator.createService(ChatApi.class,context);
    }

    public void fetchChats(Callback<ChatResponse> chatResponseCallback)
    {
        chatApi.fetchChats(chatResponseCallback);
    }
}
