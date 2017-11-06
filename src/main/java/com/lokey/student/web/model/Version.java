package com.lokey.student.web.model;

/**
 * Created by yinhaijin on 15/5/21.
 */
public class Version {

    private int id;

    private String iosversion;

    private String androidversion;

    private String ioslimitversion;

    private String androidlimitversion;

    private String createtime;

    private String updatetime;

    private String iosurl;

    private String androidurl;

    private String description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIosversion() {
        return iosversion;
    }

    public void setIosversion(String iosversion) {
        this.iosversion = iosversion;
    }

    public String getAndroidversion() {
        return androidversion;
    }

    public void setAndroidversion(String androidversion) {
        this.androidversion = androidversion;
    }

    public String getIoslimitversion() {
        return ioslimitversion;
    }

    public void setIoslimitversion(String ioslimitversion) {
        this.ioslimitversion = ioslimitversion;
    }

    public String getAndroidlimitversion() {
        return androidlimitversion;
    }

    public void setAndroidlimitversion(String androidlimitversion) {
        this.androidlimitversion = androidlimitversion;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getIosurl() {
        return iosurl;
    }

    public void setIosurl(String iosurl) {
        this.iosurl = iosurl;
    }

    public String getAndroidurl() {
        return androidurl;
    }

    public void setAndroidurl(String androidurl) {
        this.androidurl = androidurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
