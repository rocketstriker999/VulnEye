package com.hammerbyte.vulneye.results_backengine;

public class ResultAdminEye {

    String targetUrl;
    int responseCode;

    public ResultAdminEye(String targetUrl, int responseCode) {
        this.targetUrl = targetUrl;
        this.responseCode = responseCode;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }


    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
