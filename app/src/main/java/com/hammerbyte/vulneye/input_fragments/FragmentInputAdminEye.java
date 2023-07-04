package com.hammerbyte.vulneye.input_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hammerbyte.vulneye.R;
import com.hammerbyte.vulneye.backengine_fragments.FragmentBackEngineAdminEye;
import com.hammerbyte.vulneye.fragment_managers.FragmentManagerAdminEye;
import com.hammerbyte.vulneye.pref_fragments.FragmentPrefAdminEye;

import java.util.Objects;

public class FragmentInputAdminEye extends Fragment implements View.OnClickListener {

    //Parent Manager
    private FragmentManagerAdminEye fragmentManagerAdminEye;
    //PrefFragment Object
    private FragmentPrefAdminEye fragmentPrefAdminEye;
    //View Objects
    private View fragmentView;

    public FragmentInputAdminEye(FragmentManagerAdminEye fragmentManagerAdminEye) {
        setFragmentManagerAdminEye(fragmentManagerAdminEye);
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

    private FragmentPrefAdminEye getFragmentPrefAdminEye() {
        return fragmentPrefAdminEye;
    }

    private void setFragmentPrefAdminEye(FragmentPrefAdminEye fragmentPrefAdminEye) {
        this.fragmentPrefAdminEye = fragmentPrefAdminEye;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setFragmentView((fragmentView == null) ? inflater.inflate(R.layout.fragment_input_admineye, container, false) : getFragmentView());
        return getFragmentView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Finding the Preference Fragment
        setFragmentPrefAdminEye((FragmentPrefAdminEye) getChildFragmentManager().findFragmentById(R.id.FRAGMENTPREF_ADMINEYE));
        //Find Start Button
        Objects.requireNonNull(getView()).findViewById(R.id.FAB_START).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Changing State to Running and creating the whole new progress fragment
        getFragmentManagerAdminEye().setCurrentFragment(new FragmentBackEngineAdminEye(getFragmentManagerAdminEye(), getFragmentPrefAdminEye()));
    }
}
