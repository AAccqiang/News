package com.example.news.entity;

import java.util.List;

/**
 * Created by 86409 on 2016/12/7.
 */

public class User {

    private String uid;
    private String portrait;
    private int integration;
    private int comnum;
    private List<LoginLog> loginlog;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getIntegration() {
        return integration;
    }

    public void setIntegration(int integration) {
        this.integration = integration;
    }



    public List<LoginLog> getLoginlog() {
        return loginlog;
    }

    public void setLoginlog(List<LoginLog> loginlog) {
        this.loginlog = loginlog;
    }

    public int getComnum() {
        return comnum;
    }

    public void setComnum(int comnum) {
        this.comnum = comnum;
    }
}
