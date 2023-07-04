package com.hammerbyte.vulneye.pref_fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.hammerbyte.vulneye.R;
import com.hammerbyte.vulneye.activities.ActivityVulnEye;


public class FragmentPrefNetworkEye extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {

    //preferences
    private ListPreference workingListNetworkEye;

    @Override
    public void onStart() {
        super.onStart();
        //Config when it loads first time
        configWorkingModePreference(workingListNetworkEye.getValue());
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addPreferencesFromResource(R.xml.pref_networkeye);


        //attaching with keys
        workingListNetworkEye = findPreference("working_mode_networkeye");

        //Calling config whenever it get changes
        workingListNetworkEye.setOnPreferenceChangeListener(this);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

    }


    @Override
    public RecyclerView onCreateRecyclerView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        RecyclerView recyclerView = super.onCreateRecyclerView(inflater, parent, savedInstanceState);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), RecyclerView.VERTICAL);
        itemDecoration.setDrawable(getContext().getDrawable(android.R.drawable.divider_horizontal_dark));
        recyclerView.addItemDecoration(itemDecoration);
        return recyclerView;
    }


    private void configWorkingModePreference(Object newValue) {

        EditTextPreference targetHostInputNetworklEye = findPreference("target_host_networkeye");


        switch (newValue.toString()) {

            case "Entire Network":
                targetHostInputNetworklEye.setVisible(false);
                break;

            case "Host":
                targetHostInputNetworklEye.setVisible(true);
                break;
        }
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        switch (preference.getKey()) {
            case "working_mode_networkeye":
                configWorkingModePreference(newValue);
                break;
        }

        return true;
    }

}
