package com.haptik.haptikassignment.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.haptik.haptikassignment.R;
import com.haptik.haptikassignment.models.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yogeshmadaan on 11/5/16.
 */
public class ViewHolderDirectMessageReceived extends RecyclerView.ViewHolder {

    TextView textMessage,textMessageTime;
    ImageView imgMessageStatus,imgProfilePic;
    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
    SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    CardView cardView;
    public ViewHolderDirectMessageReceived(final Context context, View itemView, Message messages)
    {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        String color = Integer.toHexString(context.getResources().getColor(R.color.colorPrimary));
        String newColor = "#33"+color.substring(1);
        cardView.setCardBackgroundColor(Color.parseColor(newColor));
        textMessage = (TextView) itemView.findViewById(R.id.txt_message);
        textMessageTime = (TextView) itemView.findViewById(R.id.txt_last_message);
        imgMessageStatus = (ImageView) itemView.findViewById(R.id.img_message_status);
        imgProfilePic = (ImageView) itemView.findViewById(R.id.img_profile_pic);
        textMessage.setText(messages.getBody());
//        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
//        calendar.setTimeInMillis(messages.getMessageTime());
        Date messageDate = null;
        try {
             messageDate = inputDateFormat.parse(messages.getMessageTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String date = dateFormat.format(dateFormat.format(messageDate));
//        date = date.substring(0,date.length()-4);
        textMessageTime.setText(date);
        imgMessageStatus.setVisibility(View.GONE);
        Glide.with(context).load(messages.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imgProfilePic);

    }
}
