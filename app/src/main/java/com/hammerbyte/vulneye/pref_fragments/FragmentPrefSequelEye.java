package com.hammerbyte.vulneye.pref_fragments;

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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.hammerbyte.vulneye.R;


public class FragmentPrefSequelEye extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {


    //preferences
    private ListPreference workingListSequelEye;


    @Override
    public void onStart() {
        super.onStart();
        //Config when it loads first time
        configWorkingModePreference(workingListSequelEye.getValue());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addPreferencesFromResource(R.xml.pref_sequeleye);

        //attaching with keys
        workingListSequelEye = findPreference("working_mode_sequeleye");

        //Calling config whenever it get changes
        workingListSequelEye.setOnPreferenceChangeListener(this);
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

        EditTextPreference targetHostInputSequelEye = findPreference("target_host_sequeleye");
        EditTextPreference targetDorkInputSequelEye = findPreference("target_dork_sequeleye");


        switch (newValue.toString()) {

            case "Dork":
                targetDorkInputSequelEye.setVisible(true);
                targetHostInputSequelEye.setVisible(false);
                break;

            case "Host":
                targetDorkInputSequelEye.setVisible(false);
                targetHostInputSequelEye.setVisible(true);
                break;
        }

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        switch (preference.getKey()) {
            case "working_mode_sequeleye":
                configWorkingModePreference(newValue);
                break;
        }

        return true;
    }
}
