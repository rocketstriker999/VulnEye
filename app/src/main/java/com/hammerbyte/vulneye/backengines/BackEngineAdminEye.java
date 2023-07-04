package com.hammerbyte.vulneye.backengines;

import android.os.AsyncTask;

import com.hammerbyte.vulneye.R;
import com.hammerbyte.vulneye.backengine_fragments.FragmentBackEngineAdminEye;
import com.hammerbyte.vulneye.results_backengine.ResultAdminEye;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.hammerbyte.vulneye.utils.UtilitiesEngines.getResponseCodeHost;
import static com.hammerbyte.vulneye.utils.UtilitiesEngines.testTargetHost;

public class BackEngineAdminEye extends AsyncTask<Void, Integer, Void> {

    //Parent Fragment
    private FragmentBackEngineAdminEye fragmentBackEngineAdminEye;

    //Keywords
    private String[] targetPlatformKeywords;

    public BackEngineAdminEye(FragmentBackEngineAdminEye fragmentBackEngineAdminEye) {
        setFragmentBackEngineAdminEye(fragmentBackEngineAdminEye);
    }

    private FragmentBackEngineAdminEye getFragmentBackEngineAdminEye() {
        return fragmentBackEngineAdminEye;
    }

    private void setFragmentBackEngineAdminEye(FragmentBackEngineAdminEye fragmentBackEngineAdminEye) {
        this.fragmentBackEngineAdminEye = fragmentBackEngineAdminEye;
    }

    private String[] getTargetPlatformKeywords() {
        return targetPlatformKeywords;
    }

    private void setTargetPlatformKeywords(String[] targetPlatformKeywords) {
        this.targetPlatformKeywords = targetPlatformKeywords;
    }


    @Override
    protected Void doInBackground(Void... voids) {

        //Result HashMap Initialization
        getFragmentBackEngineAdminEye().setEngineResult(new ArrayList<ResultAdminEye>());

        //update engineProgress for host testing
        publishProgress(25);

        if (testTargetHost(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext(),
                getFragmentBackEngineAdminEye().getPrefAdminEye().getTargetHost(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getSharedPreferencesVulnEye()),
                getFragmentBackEngineAdminEye().getPrefAdminEye().useRandomUserAgent(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getSharedPreferencesVulnEye()),
                getFragmentBackEngineAdminEye().getPrefAdminEye().getConnectionTimeout(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getSharedPreferencesVulnEye()),
                (getFragmentBackEngineAdminEye().getPrefAdminEye().useProxy(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getSharedPreferencesVulnEye())) ? getFragmentBackEngineAdminEye().getPrefAdminEye().getProxyHost(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getSharedPreferencesVulnEye()) : null)
        ) {
            //update engineProgress if host connection failed
            publishProgress(50);
            //Getting the Target Specific Keywords
            setTargetPlatformKeyword();

            //Thread Pool of Maximum cores processor available
            ExecutorService executorPool = (getFragmentBackEngineAdminEye().getPrefAdminEye().useMultiThread(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getSharedPreferencesVulnEye())) ? Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()) : Executors.newSingleThreadExecutor();

            //Taking each keyword rom array and test
            for (int counter = 0; counter < getTargetPlatformKeywords().length; counter++)
                executorPool.execute(new RunnableUnitHostTester(getFragmentBackEngineAdminEye().getPrefAdminEye().getTargetHost(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getSharedPreferencesVulnEye()) + "/" + getTargetPlatformKeywords()[counter], counter));


        } else {
            //update engineProgress if host connection failed
            publishProgress(0);
        }
        return null;
    }


    //Updating the progress and progress message
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (values[0] % 10 == 0)
            getFragmentBackEngineAdminEye().getProgressManagerAdminEye().handleProgress(values[0]);
    }

    //Gets the keyword array
    private void setTargetPlatformKeyword() {
        switch (getFragmentBackEngineAdminEye().getPrefAdminEye().getTargetPlatform(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getSharedPreferencesVulnEye())) {
            case "PHP":
            default:
                setTargetPlatformKeywords(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getResources().getStringArray(R.array.phpKeyWords));
                break;
            case "JS":
                setTargetPlatformKeywords(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getResources().getStringArray(R.array.jsKeyWords));
                break;
            case "ASP":
                setTargetPlatformKeywords(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getResources().getStringArray(R.array.aspKeyWords));
                break;
            case "CFM":
                setTargetPlatformKeywords(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getResources().getStringArray(R.array.cfmKeyWords));
                break;
            case "CGI":
                setTargetPlatformKeywords(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getResources().getStringArray(R.array.cgiKeyWords));
                break;
            case "BRF":
                setTargetPlatformKeywords(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getResources().getStringArray(R.array.brfKeyWords));
                break;
        }
    }

    private class RunnableUnitHostTester implements Runnable {

        //Single Unit Tester Target Url
        private String targetUrl;
        private int counter;

        private RunnableUnitHostTester(String targetUrl, int counter) {
            this.targetUrl = targetUrl;
            this.counter = counter;
        }

        @Override
        public void run() {

            //Test the target host
            getFragmentBackEngineAdminEye().getEngineResult().add(new ResultAdminEye(targetUrl,
                    getResponseCodeHost(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext(), targetUrl,
                            getFragmentBackEngineAdminEye().getPrefAdminEye().useRandomUserAgent(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getSharedPreferencesVulnEye()),
                            getFragmentBackEngineAdminEye().getPrefAdminEye().getConnectionTimeout(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getSharedPreferencesVulnEye()),
                            (getFragmentBackEngineAdminEye().getPrefAdminEye().useProxy(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getSharedPreferencesVulnEye())) ? getFragmentBackEngineAdminEye().getPrefAdminEye().getProxyHost(getFragmentBackEngineAdminEye().getFragmentManagerAdminEye().getActivityContext().getSharedPreferencesVulnEye()) : null)));

            //setting the progress accordingly
            publishProgress(50 + (50 * (++counter) / targetPlatformKeywords.length));

        }
    }

}




