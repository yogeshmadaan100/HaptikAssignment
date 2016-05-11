package com.haptik.haptikassignment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haptik.haptikassignment.R;
import com.haptik.haptikassignment.models.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yogeshmadaan on 11/05/16.
 */
public class ViewHolderDirectMessageSent extends RecyclerView.ViewHolder {

    TextView textMessage,textMessageTime;
    ImageView imgMessageStatus;
    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
    SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public ViewHolderDirectMessageSent (final Context context,View itemView, Message messages)
    {
        super(itemView);
        textMessage = (TextView) itemView.findViewById(R.id.txt_message);
        textMessageTime = (TextView) itemView.findViewById(R.id.txt_last_message);
        imgMessageStatus = (ImageView) itemView.findViewById(R.id.img_message_status);
        textMessage.setText(messages.getBody());
//        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
//        calendar.setTimeInMillis(messages.getMessageTime());
//        String date = dateFormat.format(calendar.getTime());
        Date messageDate = null;
        try {
            messageDate = inputDateFormat.parse(messages.getMessageTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        String date = dateFormat.format(dateFormat.format(messageDate));
//        date = date.substring(0,date.length()-4);
        textMessageTime.setText(dateFormat.format(messageDate));
        imgMessageStatus.setImageResource(R.drawable.icon_message_sent);
    }
}
