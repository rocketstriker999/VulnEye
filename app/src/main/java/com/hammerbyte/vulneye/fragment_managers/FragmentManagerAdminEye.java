package com.hammerbyte.vulneye.fragment_managers;

import androidx.fragment.app.Fragment;

import com.hammerbyte.vulneye.R;
import com.hammerbyte.vulneye.activities.ActivityVulnEye;
import com.hammerbyte.vulneye.input_fragments.FragmentInputAdminEye;


public class FragmentManagerAdminEye {

    //Context of activity object
    private ActivityVulnEye activityContext = null;
    //Boolean to keep track if we are attached to container or not !
    private boolean isAttached = false;
    //Current Fragment Tracker
    private Fragment currentFragment;

    //Constructor of Handler Fragment
    public FragmentManagerAdminEye(ActivityVulnEye activityContext) {
        //Context Allocation
        setActivityContext(activityContext);
        //Setting up initial State from where to start
        setCurrentFragment((getCurrentFragment() == null) ? new FragmentInputAdminEye(this) : getCurrentFragment());
    }

    private Fragment getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(Fragment currentFragment) {
        this.currentFragment = currentFragment;
        loadCurrentFragment();
    }

    private boolean isAttached() {
        return isAttached;
    }

    public void setAttached(boolean attached) {
        isAttached = attached;
        loadCurrentFragment();
    }

    public ActivityVulnEye getActivityContext() {
        return activityContext;
    }

    private void setActivityContext(ActivityVulnEye activityContext) {
        this.activityContext = activityContext;
    }

    private void loadCurrentFragment() {
        if (isAttached()) {
            activityContext.loadFragment(getCurrentFragment(), R.id.CONTAINER_FRAGMENT);
        }
    }


}
