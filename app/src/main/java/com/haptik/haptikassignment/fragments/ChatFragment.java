package com.haptik.haptikassignment.fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haptik.haptikassignment.R;
import com.haptik.haptikassignment.adapters.MessageAdapter;
import com.haptik.haptikassignment.interfaces.ToolbarInterface;
import com.haptik.haptikassignment.managers.ChatManager;
import com.haptik.haptikassignment.models.ChatResponse;
import com.haptik.haptikassignment.models.Message;
import com.haptik.haptikassignment.models.Profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


public class ChatFragment extends Fragment implements MessageAdapter.onChatClicked{
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private static final String TAG = ChatFragment.class.getCanonicalName();
    private static final String KEY_CHATS = "chats";
    private MessageAdapter messageAdapter;
    private List<Message> messageList;
    LinearLayoutManager linearLayoutManager;
    private ChatResponse chatResponse;
    ViewGroup rootView;
    EventBus eventBus;
    ChatManager chatManager;
    onChatClickedInterface onChatClickedInterface;
    ToolbarInterface toolbarInterface;
    static HashMap<String,Integer> colors = new HashMap<>() ;
    private HashMap<String,Integer> messagesHasmap = new HashMap<>();
    public ChatFragment() {
        // Required empty public constructor
    }


    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, rootView);
        initViews();
        if (savedInstanceState != null) {
            if(savedInstanceState.getParcelable(KEY_CHATS)!=null)
            {
                chatResponse = savedInstanceState.getParcelable(KEY_CHATS);
                refreshData(chatResponse);
            }

        }
        if(chatResponse==null)
            refreshContent();
        return rootView;
    }
    public void initViews() {
        chatManager = ChatManager.getInstance(getActivity());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    refreshContent();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        messageList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        messageAdapter = new MessageAdapter(getActivity(), messageList,this);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(messageAdapter);

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_CHATS,chatResponse);
    }

    public void refreshContent() {
        startRefreshing();
        chatManager.fetchChats();

    }
    public void startRefreshing() {
        swipeRefreshLayout.setRefreshing(true);
    }

    public void stopRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.onChatClickedInterface = (onChatClickedInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnChatClickedListener");
        }
        try {
            toolbarInterface = (ToolbarInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnToolbarListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.onChatClickedInterface = null;
        toolbarInterface = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        eventBus = EventBus.getDefault();
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    public void onEvent(ChatResponse chatResponse)
    {
        refreshData(chatResponse);

    }
    public void refreshData(ChatResponse chatResponse)
    {
        if (chatResponse != null && chatResponse.getCount() > 0) {

                this.chatResponse = chatResponse;
                messageList.clear();
            messageList.addAll(chatResponse.getMessages());
            messageAdapter.notifyDataSetChanged();
            resetHashmap(chatResponse.getMessages());
            stopRefreshing();


        }
    }

    @Override
    public void onChatClicked(View itemView, Message message, boolean isDefaultSelection) {
        Profile profile = new Profile();
        profile.setImageUrl(message.getImageUrl());
        profile.setName(message.getName());
        profile.setMessageCount(messagesHasmap.get(message.getName()));
        onChatClickedInterface.onChatClicked(itemView, profile, isDefaultSelection);
    }

    public  interface onChatClickedInterface
    {
        void onChatClicked(View itemView, Profile profile, boolean isDefaultSelection);
    }
    public static int getColors(String name)
    {
            if(colors.get(name)!=null)
                return colors.get(name).intValue();
            Random rnd = new Random();
            colors.put(name, Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        return colors.get(name).intValue();

    }

    public void resetHashmap(List<Message> messages)
    {
        messagesHasmap = new HashMap<>();
        for(Message message:messages)
        {
            if(messagesHasmap.get(message.getName())!=null)
                messagesHasmap.put(message.getName(),messagesHasmap.get(message.getName())+1);
            else
                messagesHasmap.put(message.getName(),1);
        }
    }
}
