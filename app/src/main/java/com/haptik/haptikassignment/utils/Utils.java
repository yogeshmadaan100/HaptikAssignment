package com.haptik.haptikassignment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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


}
