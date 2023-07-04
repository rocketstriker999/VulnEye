package com.hammerbyte.vulneye.progress_managers;

import com.hammerbyte.vulneye.custom_views.ProgressBarCircular;

public class ProgressManagerAdminEye {

    //Circular ProgressBar Object
    private ProgressBarCircular progressBarCircular;

    public ProgressManagerAdminEye(ProgressBarCircular progressBarCircular, Runnable onFinishRunnable) {
        setProgressBarCircular(progressBarCircular);
        getProgressBarCircular().setOnFinishRunnable(onFinishRunnable);
    }

    public ProgressBarCircular getProgressBarCircular() {
        return progressBarCircular;
    }

    public void setProgressBarCircular(ProgressBarCircular progressBarCircular) {
        this.progressBarCircular = progressBarCircular;
    }

    //Override method from Super Class
    public void handleProgress(int progress) {
        getProgressBarCircular().setProgressWithAnimation(progress);
        switch (progress) {
            case 0:
                getProgressBarCircular().setProgressMessage("Connection Failed to Target !");
                break;
            case 25:
                getProgressBarCircular().setProgressMessage("Connecting to Target...");
                break;

            case 50:
                getProgressBarCircular().setProgressMessage("AdminEye Watching...");
                break;

            //If we get to 100 then show message and destroy the view
            case 100:
                getProgressBarCircular().setProgressMessage("Generating Results !");
                break;
        }

    }


}
