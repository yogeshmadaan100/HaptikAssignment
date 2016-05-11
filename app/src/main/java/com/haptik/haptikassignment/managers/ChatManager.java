package com.haptik.haptikassignment.managers;

import android.content.Context;

import com.haptik.haptikassignment.models.ChatResponse;
import com.haptik.haptikassignment.services.ChatService;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by yogeshmadaan on 11/05/16.
 */
public class ChatManager {

    private static ChatManager _instance = null;
    private EventBus eventBus;
    private Context context;
    private ChatService chatService = null;

    private ChatManager(Context context)
    {
        this.context =context;
        eventBus = EventBus.getDefault();
        chatService = new ChatService(context);
    }

    public static ChatManager getInstance(Context context)
    {
        if (_instance == null)
            _instance = new ChatManager(context);
        return _instance;
    }

    public void fetchChats()
    {
        chatService.fetchChats(chatResponseCallback);
    }
    private Callback<ChatResponse> chatResponseCallback = new Callback<ChatResponse>() {
        @Override
        public void success(ChatResponse chatResponse, Response response) {
            eventBus.post(chatResponse);
        }

        @Override
        public void failure(RetrofitError error) {

        }
    };
}
