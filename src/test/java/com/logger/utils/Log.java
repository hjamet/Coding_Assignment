package com.logger.utils;

public final class Log {
    public String id;
    public Long duration;
    public String host = "null";
    public String type = "null";
    public Boolean alert = false;

    public Log(String id, Long duration, String host, String type, Boolean alert) {
        this.id = id;
        this.duration = duration;
        if (host == null && type == null){
            this.host = "null";
            this.type = "null";
        }else{
            this.host = host;
            this.type = type;
        }
        this.alert = alert;
    }
}