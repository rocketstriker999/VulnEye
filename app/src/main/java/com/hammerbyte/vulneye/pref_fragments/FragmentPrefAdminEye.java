package com.hammerbyte.vulneye.pref_fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.hammerbyte.vulneye.R;


    public class FragmentPrefAdminEye extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addPreferencesFromResource(R.xml.pref_admineye);
    }

    public String getTargetPlatform(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("target_platform", "PHP");
    }

    public String getTargetHost(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("target_host_admineye", "http://127.0.0.1:80");
    }

    public int getConnectionTimeout(SharedPreferences sharedPreferences) {
        return sharedPreferences.getInt("connection_timeout_admineye", 5);
    }

    public boolean useProxy(SharedPreferences sharedPreferences) {
        return sharedPreferences.getBoolean("use_proxy_admineye", false);
    }

    public String getProxyHost(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("proxy_host_admineye", "127.0.0.1:80");
    }

    public boolean useRandomUserAgent(SharedPreferences sharedPreferences) {
        return sharedPreferences.getBoolean("random_useragent_admineye", false);
    }

    public boolean useMultiThread(SharedPreferences sharedPreferences) {
        return sharedPreferences.getBoolean("multithreading_admineye", false);
    }

    public boolean saveLogs(SharedPreferences sharedPreferences) {
        return sharedPreferences.getBoolean("save_logs_admineye", true);
    }


    @Override
    public RecyclerView onCreateRecyclerView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        RecyclerView recyclerView = super.onCreateRecyclerView(inflater, parent, savedInstanceState);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), RecyclerView.VERTICAL);
        itemDecoration.setDrawable(getContext().getDrawable(android.R.drawable.divider_horizontal_dark));
        recyclerView.addItemDecoration(itemDecoration);
        return recyclerView;
    }


}
