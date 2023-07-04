package com.hammerbyte.vulneye.pref_fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.hammerbyte.vulneye.R;
import com.hammerbyte.vulneye.activities.ActivityVulnEye;


public class FragmentPrefSettings extends PreferenceFragmentCompat {

    //Context Object
    private ActivityVulnEye activityContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Context Allocating
        activityContext = (ActivityVulnEye) context;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addPreferencesFromResource(R.xml.pref_settings);

        findPreference("check_update").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                new AsyncTaskUpdateChecker().execute();
                return false;
            }
        });
    }


    public String getStartingTab(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("start_tab", "NetworkEye");
    }


    @Override
    public RecyclerView onCreateRecyclerView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        RecyclerView recyclerView = super.onCreateRecyclerView(inflater, parent, savedInstanceState);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), RecyclerView.VERTICAL);
        itemDecoration.setDrawable(getContext().getDrawable(android.R.drawable.divider_horizontal_dark));
        recyclerView.addItemDecoration(itemDecoration);
        return recyclerView;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

    }


    private class AsyncTaskUpdateChecker extends AsyncTask<Void, Void, Void> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Update");
            progressDialog.setMessage("Please wait while connecting to server & fetching the details");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Logic to Check if new update on play store available or not !!!
            //if update is avilable then redirect it to pla store else toast you are already using latest version
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }
    }


}
