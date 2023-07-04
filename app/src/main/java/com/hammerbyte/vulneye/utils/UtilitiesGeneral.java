package com.hammerbyte.vulneye.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class UtilitiesGeneral {

    public static int runTimePermissionRequestCode = 91;


    //Returns Current Date & time
    public static String getCurrentDateTime() {
        return String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connMan.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public  enum CurrentState { INPUT, RUNNING, OUTPUT };



}
