package com.hammerbyte.vulneye.other_fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hammerbyte.vulneye.R;
import com.hammerbyte.vulneye.activities.ActivityVulnEye;

public class FragmentDonate extends Fragment implements View.OnClickListener {

    private ActivityVulnEye activityContext;
    private View donateView;
    private Button donateButton;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activityContext = (ActivityVulnEye) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        donateView = inflater.inflate(R.layout.fragment_donate, container, false);
        donateButton = donateView.findViewById(R.id.DONATE_BTN);
        return donateView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        donateButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.DONATE_BTN:
                activityContext.loadDonationConfirmation();
                break;

        }

    }


}
