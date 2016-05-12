package com.haptik.haptikassignment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by yogeshmadaan on 11/05/16.
 */
public class Utils {
    /**
     * Checks if the device is connected to the Internet.
     *
     * @param context
     * @return
     */
    public static boolean isConnectedToInternet(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public static Date getDisplayDate(Date date, String timeZoneOffset) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT" + timeZoneOffset);
        Calendar cal = Calendar.getInstance(timeZone);
        cal.setTimeZone(timeZone);
        cal.setTime(date);
        cal.add(Calendar.MILLISECOND, timeZone.getRawOffset());
        return cal.getTime();

    }

    public static void copyToClipboard(Context context, String stringYouExtracted) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(stringYouExtracted);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", stringYouExtracted);
            clipboard.setPrimaryClip(clip);
        }
        Toast.makeText(context,"Message Copied",Toast.LENGTH_SHORT).show();
    }


}
