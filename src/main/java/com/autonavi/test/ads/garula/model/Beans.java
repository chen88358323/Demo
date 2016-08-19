package com.autonavi.test.ads.garula.model;

/**
 * Created by chichen.cc on 2015/7/24.
 */
public class Beans {
    private String tid;
    private String diu;
    private String os;
    private String adcode;

    public Beans(String tid, String diu, String os, String adcode) {
        this.tid = tid;
        this.diu = diu;
        this.os = os;
        this.adcode = adcode;
    }
    public Beans(){}
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getDiu() {
        return diu;
    }

    public void setDiu(String diu) {
        this.diu = diu;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }
}
