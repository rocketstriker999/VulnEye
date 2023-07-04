package com.hammerbyte.vulneye.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hammerbyte.vulneye.R;
import com.hammerbyte.vulneye.results_backengine.ResultAdminEye;

import java.util.ArrayList;

public class Adapter_Result_AdminEye extends BaseAdapter {

    //HashMap Object for Result Showing
    private ArrayList<ResultAdminEye> engineResult;

    public Adapter_Result_AdminEye(ArrayList<ResultAdminEye> engineResult) {
        setEngineResult(engineResult);
    }

    public ArrayList<ResultAdminEye> getEngineResult() {
        return engineResult;
    }

    public void setEngineResult(ArrayList<ResultAdminEye> engineResult) {
        this.engineResult = engineResult;
    }

    @Override
    public int getCount() {
        return getEngineResult().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_result_admineye, parent, false);

        TextView textViewHost = convertView.findViewById(R.id.TV_HOST);
        TextView textViewHostResponseCode = convertView.findViewById(R.id.TV_HOST_RESPONSECODE);

        textViewHost.setText("Url :" + getEngineResult().get(position).getTargetUrl());
        textViewHostResponseCode.setText("Url :" + getEngineResult().get(position).getResponseCode());


        return convertView;
    }
}
