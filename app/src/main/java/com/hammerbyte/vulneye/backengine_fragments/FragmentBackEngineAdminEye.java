package com.hammerbyte.vulneye.backengine_fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hammerbyte.vulneye.R;
import com.hammerbyte.vulneye.backengines.BackEngineAdminEye;
import com.hammerbyte.vulneye.custom_views.ProgressBarCircular;
import com.hammerbyte.vulneye.fragment_managers.FragmentManagerAdminEye;
import com.hammerbyte.vulneye.input_fragments.FragmentInputAdminEye;
import com.hammerbyte.vulneye.output_fragments.FragmentOutputAdminEye;
import com.hammerbyte.vulneye.pref_fragments.FragmentPrefAdminEye;
import com.hammerbyte.vulneye.progress_managers.ProgressManagerAdminEye;
import com.hammerbyte.vulneye.results_backengine.ResultAdminEye;

import java.util.ArrayList;

public class FragmentBackEngineAdminEye extends Fragment implements View.OnClickListener {

    //Result Variable
    private ArrayList<ResultAdminEye> engineResult;
    //Parent Fragment Object
    private FragmentManagerAdminEye fragmentManagerAdminEye;
    //Circular ProgressBar
    private ProgressManagerAdminEye progressManagerAdminEye;
    //AsyncEngine
    private BackEngineAdminEye backEngineAdminEye;
    //View of Fragment
    private View fragmentView;
    //Runnable which will be execute when processes are done
    private Runnable onFinishRunnable;
    //Prefrence Object
    private FragmentPrefAdminEye prefAdminEye;
    public FragmentBackEngineAdminEye(FragmentManagerAdminEye fragmentManagerAdminEye, FragmentPrefAdminEye fragmentPrefAdminEye) {
        //Parent Manage Allocation
        setFragmentManagerAdminEye(fragmentManagerAdminEye);
        //Set the preference object
        setPrefAdminEye(fragmentPrefAdminEye);
        //Setting up the finish task
        setOnFinishRunnable(new Runnable() {
            @Override
            public void run() {
                getFragmentManagerAdminEye().setCurrentFragment(new FragmentOutputAdminEye(getFragmentManagerAdminEye(), getEngineResult()));
            }
        });
    }

    public ArrayList<ResultAdminEye> getEngineResult() {
        return engineResult;
    }

    public void setEngineResult(ArrayList<ResultAdminEye> engineResult) {
        this.engineResult = engineResult;
    }

    public FragmentPrefAdminEye getPrefAdminEye() {
        return prefAdminEye;
    }

    private void setPrefAdminEye(FragmentPrefAdminEye prefAdminEye) {
        this.prefAdminEye = prefAdminEye;
    }

    private BackEngineAdminEye getBackEngineAdminEye() {
        return backEngineAdminEye;
    }

    private void setBackEngineAdminEye(BackEngineAdminEye backEngineAdminEye) {
        this.backEngineAdminEye = backEngineAdminEye;
    }

    private Runnable getOnFinishRunnable() {
        return onFinishRunnable;
    }

    private void setOnFinishRunnable(Runnable onFinishRunnable) {
        this.onFinishRunnable = onFinishRunnable;
    }

    public ProgressManagerAdminEye getProgressManagerAdminEye() {
        return progressManagerAdminEye;
    }

    private void setProgressManagerAdminEye(ProgressManagerAdminEye progressManagerAdminEye) {
        this.progressManagerAdminEye = progressManagerAdminEye;
    }

    private View getFragmentView() {
        return fragmentView;
    }

    private void setFragmentView(View fragmentView) {
        this.fragmentView = fragmentView;
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

    public FragmentManagerAdminEye getFragmentManagerAdminEye() {
        return fragmentManagerAdminEye;
    }

    private void setFragmentManagerAdminEye(FragmentManagerAdminEye fragmentManagerAdminEye) {
        this.fragmentManagerAdminEye = fragmentManagerAdminEye;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setFragmentView((fragmentView == null) ? inflater.inflate(R.layout.fragment_backengine_admineye, container, false) : getFragmentView());
        return getFragmentView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //if background task is not setup yet then start it up
        if (getBackEngineAdminEye() == null) {
            setBackEngineAdminEye(new BackEngineAdminEye(this));
            getBackEngineAdminEye().execute();
        }

        //Setup the ProgressHandler Object
        setProgressManagerAdminEye((getProgressManagerAdminEye() == null) ? new ProgressManagerAdminEye((ProgressBarCircular) getFragmentView().findViewById(R.id.PROGRESS_EYE), getOnFinishRunnable()) : getProgressManagerAdminEye());

        //The stop button click listner
        getFragmentView().findViewById(R.id.FAB_EYE_STOP).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //If Clicked to the stop button
        getBackEngineAdminEye().cancel(true);
        //Return Back to Input Fragment
        getFragmentManagerAdminEye().setCurrentFragment(new FragmentInputAdminEye(getFragmentManagerAdminEye()));
    }


}

