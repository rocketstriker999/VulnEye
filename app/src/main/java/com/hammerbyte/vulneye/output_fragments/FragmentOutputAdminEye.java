package com.hammerbyte.vulneye.output_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hammerbyte.vulneye.R;
import com.hammerbyte.vulneye.adapters.Adapter_Result_AdminEye;
import com.hammerbyte.vulneye.fragment_managers.FragmentManagerAdminEye;
import com.hammerbyte.vulneye.input_fragments.FragmentInputAdminEye;
import com.hammerbyte.vulneye.results_backengine.ResultAdminEye;

import java.util.ArrayList;

public class FragmentOutputAdminEye extends Fragment implements View.OnClickListener {

    //View of Fragment
    private View fragmentView;
    //RecyclerView Object for Result
    private ListView resultListView;
    //The Parent FragmentManager
    private FragmentManagerAdminEye fragmentManagerAdminEye;
    //Result ArrayList
    private ArrayList<ResultAdminEye> engineResult;

    public FragmentOutputAdminEye(FragmentManagerAdminEye fragmentManagerAdminEye, ArrayList<ResultAdminEye> engineResult) {
        setFragmentManagerAdminEye(fragmentManagerAdminEye);
        setEngineResult(engineResult);
    }

    private ArrayList<ResultAdminEye> getEngineResult() {
        return engineResult;
    }

    private void setEngineResult(ArrayList<ResultAdminEye> engineResult) {
        this.engineResult = engineResult;
    }

    private ListView getResultListView() {
        return resultListView;
    }

    private void setResultListView(ListView resultListView) {
        this.resultListView = resultListView;
    }


    private View getFragmentView() {
        return fragmentView;
    }

    private void setFragmentView(View fragmentView) {
        this.fragmentView = fragmentView;
    }

    private FragmentManagerAdminEye getFragmentManagerAdminEye() {
        return fragmentManagerAdminEye;
    }

    private void setFragmentManagerAdminEye(FragmentManagerAdminEye fragmentManagerAdminEye) {
        this.fragmentManagerAdminEye = fragmentManagerAdminEye;
    }

    @Override
    public void onStart() {
        super.onStart();
        getResultListView().setAdapter(new Adapter_Result_AdminEye(getEngineResult()));
    }

    @Override
    public void onResume() {
        super.onResume();
        getFragmentManagerAdminEye().setAttached(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        getFragmentManagerAdminEye().setAttached(false);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setFragmentView((fragmentView == null) ? inflater.inflate(R.layout.fragment_output_admineye, container, false) : getFragmentView());
        return getFragmentView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //mapping the views
        setResultListView((ListView) getFragmentView().findViewById(R.id.OUTPUT_EYE));
        //Click Listner for clear button
        view.findViewById(R.id.FAB_CLEAR).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //Changing State to Running and creating the whole new progress fragment
        getFragmentManagerAdminEye().setCurrentFragment(new FragmentInputAdminEye(getFragmentManagerAdminEye()));
    }
}
