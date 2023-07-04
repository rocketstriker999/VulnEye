package com.hammerbyte.vulneye.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.hammerbyte.vulneye.R;
import com.hammerbyte.vulneye.fragment_managers.FragmentManagerAdminEye;
import com.hammerbyte.vulneye.pref_fragments.FragmentPrefSettings;

import static com.hammerbyte.vulneye.utils.UtilitiesGeneral.runTimePermissionRequestCode;


public class ActivityVulnEye extends AppCompatActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

    //Prefrencefragments for Settings Only
    public FragmentPrefSettings fragmentPrefSettings;
    //Shared Pref Main Object
    private SharedPreferences sharedPreferencesVulnEye;
    //Material Design Views
    private BottomSheetBehavior preferenceBottomSheet;
    private BottomNavigationView bottomNavigationView;
    //Handler Fragments
    private FragmentManagerAdminEye fragmentManagerAdminEye;
    //Toolbar Object
    private Toolbar vulneyeToolBar;

    public ActivityVulnEye() {

        //Initiating Input fragments
        fragmentManagerAdminEye = new FragmentManagerAdminEye(this);
        //Initiating PreferenceFragments
        fragmentPrefSettings = new FragmentPrefSettings();

    }

    public SharedPreferences getSharedPreferencesVulnEye() {
        return sharedPreferencesVulnEye;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initiating UI
        setContentView(R.layout.activity_vulneye);
        vulneyeToolBar = findViewById(R.id.VULNEYE_TOOLBAR);
        preferenceBottomSheet = BottomSheetBehavior.from(findViewById(R.id.PREFRENCE_BOTTOMSHEET));
        bottomNavigationView = findViewById(R.id.BOTTOM_NAVIGATION_MENU);

        //Setup toolbar
        setSupportActionBar(vulneyeToolBar);

        //Check all the permissions
        requestForRunTimePermission();
    }


    //Function to Check Run Time Permission Each Time
    private void requestForRunTimePermission() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

            final Dialog runTimePermissionDialogue = new Dialog(ActivityVulnEye.this);
            runTimePermissionDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            runTimePermissionDialogue.setContentView(R.layout.dialouge_runtime_permission);
            runTimePermissionDialogue.setCancelable(false);
            runTimePermissionDialogue.show();

            runTimePermissionDialogue.findViewById(R.id.RUNTIME_PERMISSION_GRANT_BTN).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    runTimePermissionDialogue.dismiss();
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, runTimePermissionRequestCode);
                }
            });

            runTimePermissionDialogue.findViewById(R.id.RUNTIME_PERMISSION_DECLINE_BTN).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finishAndRemoveTask();
                }
            });
        }
    }

    //Function to Check the Result of Runtime Permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
            requestForRunTimePermission();
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //the real shared preference of Vulneye
        sharedPreferencesVulnEye = PreferenceManager.getDefaultSharedPreferences(this);

        //Setting up Listeners
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        //Setting up First Screen Manually According to Settings
        switch (fragmentPrefSettings.getStartingTab(sharedPreferencesVulnEye)) {
            case "NetworkEye":
                bottomNavigationView.setSelectedItemId(R.id.NETWORK_EYE_OPENER);
                break;

            case "SequelEye":
                bottomNavigationView.setSelectedItemId(R.id.SEQUEL_EYE_OPENER);
                break;

            case "AdminEye":
                bottomNavigationView.setSelectedItemId(R.id.ADMIN_EYE_OPENER);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vulneye_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.TOOLBAR_HELP:
                startActivity(new Intent(new Intent(this, ActivityHelp.class)));
                break;

            case R.id.TOOLBAR_SHARE:
                startActivity(new Intent(android.content.Intent.ACTION_SEND).setType("text/plain").putExtra(android.content.Intent.EXTRA_SUBJECT, "VulnEye").putExtra(android.content.Intent.EXTRA_TEXT, "I Found a good Application"));
                break;

            case R.id.TOOLBAR_DONATE:
                loadDonationConfirmation();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.PREFRENCE_BOTTOMSHEET_TITLE:
                preferenceBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.NETWORK_EYE_OPENER:
                updateToolbarTitle(getString(R.string.title_networkye));
                break;

            case R.id.SEQUEL_EYE_OPENER:
                updateToolbarTitle(getString(R.string.title_sequeleye));
                break;

            case R.id.ADMIN_EYE_OPENER:
                updateToolbarTitle(getString(R.string.title_admineye));
                fragmentManagerAdminEye.setAttached(true);
                break;

            case R.id.LOGS_OPENER:
                updateToolbarTitle(getString(R.string.title_logs));
                break;

            case R.id.SETTINGS_OPENER:
                updateToolbarTitle(getString(R.string.title_settings));
                loadFragment(fragmentPrefSettings, R.id.CONTAINER_FRAGMENT);
                break;
        }

        return true;
    }


    public void loadFragment(Fragment targetFragment, int containerId) {
        getSupportFragmentManager().beginTransaction().replace(containerId, targetFragment).commit();
    }

    //Both Functions are Called by Fragments or PreferenceFragment to Settle up the Screen Info
    private void updateToolbarTitle(String toolbarTitle) {
        vulneyeToolBar.setTitle(toolbarTitle);
    }


    //make function public so can toolbar donate button can also use this function
    public void loadDonationConfirmation() {
        final Dialog donationConfirmationDialogue = new Dialog(this);
        donationConfirmationDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        donationConfirmationDialogue.setContentView(R.layout.dialouge_donate);
        donationConfirmationDialogue.setCancelable(true);
        donationConfirmationDialogue.show();

        final EditText donationAmountEditText = donationConfirmationDialogue.findViewById(R.id.DONATION_AMOUNT_ETX);

        donationConfirmationDialogue.findViewById(R.id.DONATE_PROCEED_BTN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (donationAmountEditText.getText().toString().isEmpty() || Integer.parseInt(donationAmountEditText.getText().toString()) <= 0) {
                    donationAmountEditText.setError("Please Enter Valid Amount");
                } else {
                    //Code to Redirect at Payment Gateway
                    donationConfirmationDialogue.dismiss();
                }
            }
        });

        donationConfirmationDialogue.findViewById(R.id.DONATE_LATER_BTN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donationConfirmationDialogue.dismiss();
            }
        });


    }

}
