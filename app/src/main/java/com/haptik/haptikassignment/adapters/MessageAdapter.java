package com.haptik.haptikassignment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haptik.haptikassignment.MyApplication;
import com.haptik.haptikassignment.R;
import com.haptik.haptikassignment.models.Message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by yogeshmadaan on 11/05/16.
 */
public class MessageAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Message> messages;
    int resource;
    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
    public MessageAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.resource = R.layout.layout_direct_message_received;
        this.messages = messages;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        int rowLayout;
        View view;
        if(messages.get(position).getUsername().equalsIgnoreCase(MyApplication._user))
        {
            rowLayout = R.layout.layout_direct_message_sent;
            view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
            return new ViewHolderDirectMessageSent(context,view,messages.get(position));
        }
        else{
            rowLayout = R.layout.layout_direct_message_received;
            view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
            return new ViewHolderDirectMessageReceived(context,view,messages.get(position));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ViewHolderDirectMessageSent)
        {
            ((ViewHolderDirectMessageSent)holder).imgMessageStatus.setImageResource(R.drawable.icon_message_sent);

        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface onChatClicked
    {
        void onChatClicked(View itemView,Message message, boolean isDefaultSelection);
    }
}
