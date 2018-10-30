package com.example.es.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author jianhui.Yang
 * @version $Id ElkTestVO.java, v 0.1 2018-10-30 14:57 jianhui.Yang Exp $$
 */
public class ElkTestVO {
    @JSONField(name = "@version")
    private String version;
    @JSONField(name = "@timestamp")
    private String timestamp;

    private String path;

    private String host;

    private String type;

    private String message;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
