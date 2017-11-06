package com.lokey.student.web.model;

public class Managerfunction {
    private String id;

    private String roleid;

    private String modelid;

    private String functionid;

    private String btfunctionid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String userid) {
        this.roleid = userid == null ? null : userid.trim();
    }

    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid == null ? null : modelid.trim();
    }

    public String getFunctionid() {
        return functionid;
    }

    public void setFunctionid(String functionid) {
        this.functionid = functionid == null ? null : functionid.trim();
    }

    public String getBtfunctionid() {
        return btfunctionid;
    }

    public void setBtfunctionid(String btfunctionid) {
        this.btfunctionid = btfunctionid == null ? null : btfunctionid.trim();
    }
}