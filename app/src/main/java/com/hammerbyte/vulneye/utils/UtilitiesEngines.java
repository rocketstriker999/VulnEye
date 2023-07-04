package com.hammerbyte.vulneye.utils;

import android.content.Context;

import com.hammerbyte.vulneye.R;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Random;

import static com.hammerbyte.vulneye.utils.UtilitiesGeneral.isNetworkConnected;


public class UtilitiesEngines {


    public static boolean testTargetHost(Context context, String targetHost, boolean useRandomUserAgent, int connectionTimeOut, String throughProxy) {
        return getResponseCodeHost(context, targetHost, useRandomUserAgent, connectionTimeOut, throughProxy) == 200;
    }


    public static int getResponseCodeHost(Context context, String targetHost, boolean useRandomUserAgent, int connectionTimeOut, String throughProxy) {
        if (!isNetworkConnected(context) || (throughProxy == null) || validateProxy(throughProxy)) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) ((throughProxy != null) ? new URL(targetHost).openConnection(formatProxy(throughProxy)) : new URL(targetHost).openConnection());
                httpURLConnection.setConnectTimeout(connectionTimeOut * 1000);
                httpURLConnection.setRequestProperty("User-Agent", (useRandomUserAgent) ? generateRandomUserAgent(context) : "VulnEye");
                httpURLConnection.connect();
                return httpURLConnection.getResponseCode();
            } catch (Exception e) {
                e.printStackTrace();
                return 404;
            }
        } else {
            return 404;
        }
    }


    public static boolean validateProxy(String targetProxy) {
        return targetProxy.matches("\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b:\\d{2,5}");
    }

    public static Proxy formatProxy(String targetProxy) {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(targetProxy.split(":")[0], Integer.parseInt(targetProxy.split(":")[1])));
    }

    public static String generateRandomUserAgent(Context context) {
        String[] userAgents = context.getResources().getStringArray(R.array.userAgents);
        return userAgents[new Random().nextInt(userAgents.length)];
    }


}
