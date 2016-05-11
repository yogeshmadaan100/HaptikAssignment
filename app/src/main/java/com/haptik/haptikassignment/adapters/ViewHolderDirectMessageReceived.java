package com.haptik.haptikassignment.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.haptik.haptikassignment.R;
import com.haptik.haptikassignment.fragments.ChatFragment;
import com.haptik.haptikassignment.models.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yogeshmadaan on 11/5/16.
 */
public class ViewHolderDirectMessageReceived extends RecyclerView.ViewHolder {

    TextView textMessage,textMessageTime,txtSenderName;
    ImageView imgMessageStatus,imgProfilePic;
    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
    SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    CardView cardView;
    public ViewHolderDirectMessageReceived(final Context context, View itemView, Message messages)
    {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
//        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        txtSenderName = (TextView) itemView.findViewById(R.id.txt_sender);
        textMessage = (TextView) itemView.findViewById(R.id.txt_message);
        textMessageTime = (TextView) itemView.findViewById(R.id.txt_last_message);
        imgMessageStatus = (ImageView) itemView.findViewById(R.id.img_message_status);
        imgProfilePic = (ImageView) itemView.findViewById(R.id.img_profile_pic);
        txtSenderName.setText(messages.getName());
        txtSenderName.setTextColor(ChatFragment.getColors(messages.getName()));
        textMessage.setText(messages.getBody());
//        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
//        calendar.setTimeInMillis(messages.getMessageTime());
        Date messageDate = null;
        try {
             messageDate = inputDateFormat.parse(messages.getMessageTime());
            Log.e("message date is",""+messageDate.toLocaleString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        String date = dateFormat.format(dateFormat.format(messageDate));
//        date = date.substring(0,date.length()-4);
        textMessageTime.setText(dateFormat.format(messageDate));
        imgMessageStatus.setVisibility(View.GONE);
//        Glide.with(context).load(messages.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imgProfilePic);
        Glide.with(context).load(messages.getImageUrl()).asBitmap().centerCrop().into(new BitmapImageViewTarget(imgProfilePic) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imgProfilePic.setImageDrawable(circularBitmapDrawable);
            }
        });
    }
}
