package com.lokey.student.web.model;

/**
 * Created by yinhaijin on 15/12/4.
 */
public class RequestModel {
    private String channelId;

    private String clientVersion;

    private Integer dHeight;

    private Integer dWidth;

    private int osType;

    private String osVer;

    private String params;

    private String uuid;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion == null ? null : clientVersion.trim();
    }

    public Integer getdHeight() {
        return dHeight;
    }

    public void setdHeight(Integer dHeight) {
        this.dHeight = dHeight == null ? null : dHeight;
    }

    public Integer getdWidth() {
        return dWidth;
    }

    public void setdWidth(Integer dWidth) {
        this.dWidth = dWidth == null ? null : dWidth;
    }

    public int getOsType() {
        return osType;
    }

    public void setOsType(int osType) {
        this.osType = osType;
    }

    public String getOsVer() {
        return osVer;
    }

    public void setOsVer(String osVer) {
        this.osVer = osVer == null ? null : osVer.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }
}
