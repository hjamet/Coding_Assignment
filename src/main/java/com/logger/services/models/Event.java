package com.logger.services.models;
//FINISHED

import java.sql.Timestamp;

import com.logger.utils.Logged;


public final class Event implements Logged{
    
/* ------------------------------ INITALIZATION ----------------------------- */
    private String id;
    private Timestamp startTime = null;
    private Timestamp endTime = null;
    
    private String type = null;
    private String host = null;
    
    private boolean isflagged = false;
    
/* ------------------------------ PUBLIC BASIC ------------------------------ */
    
    public Event(final String id, final Timestamp time, final Boolean isFinished){
        this.id = id;
        if (isFinished){
            this.endTime = time;
        }else{
            this.startTime = time;
        }
    }
    
    public Event(final String id, final Timestamp time, final Boolean isFinished, final String host, final String type){
        this.id = id;
        if (isFinished){
            this.endTime = time;
        }else{
            this.startTime = time;
        }
        this.host = host;
        this.type = type;
    }
    
    public String getId() {
        return id;
    }
    public void setId(final String id) {
        this.id = id;
    }
    public Timestamp getStartTime() {
        return startTime;
    }
    public void setStartTime(final Timestamp startTime) {
        this.startTime = startTime;
    }
    public Timestamp getEndTime() {
        return endTime;
    }
    public void setEndTime(final Timestamp endtime) {
        this.endTime = endtime;
    }
    public String getType() {
        return type;
    }
    public void setType(final String type) {
        this.type = type;
    }
    public String getHost() {
        return host;
    }
    public void setHost(final String host) {
        this.host = host;
    }
    public boolean isflagged() {
        return this.isflagged;
    }
    public void setFlagged(final boolean flagged) {
        this.isflagged = flagged;
    }
    
    /* ----------------------------- PUBLIC COMPLEX ----------------------------- */
    /**
     * Get the state of the event.
     * @return true if the event is finished, false otherwise.
     */
    public Boolean isFinished(){
        return this.endTime != null;
    }
    
    /**
     * Tell if the event is complete, ie has a start and end timestamp.
     * @return true if the event is complete, false otherwise.
     */
    public Boolean isComplete(){
        if (this.startTime != null && this.endTime != null){
            return true;
        }
        return false;
    }
    
    /**
     * Get the duration of the event. If it is not finished, use the current time as endTime.
     * In case of error, return a null Timestamp
     * @return the duration of the event.
     */
    public Timestamp getDuration(){
        if (this.isComplete() && this.isFinished()){
            return new Timestamp(this.endTime.getTime() - this.startTime.getTime());
        }else if (this.isComplete()){
            logger().warn("Event " + this.id + "is not finished yet");
            return new Timestamp(System.currentTimeMillis() - this.startTime.getTime());
        }else{
            logger().error("Event " + this.id + "is not complete!");
            return null;
        }
    }
    
    /**
     * Returns if the event is a server event or not.
     * @return true if the event is a server event, false otherwise.
     */
    public Boolean isServerEvent(){
        return this.host != null;
    }
    
    
/* -------------------------------- OVERRIDE -------------------------------- */
    @Override
    public String toString(){
        if (this.isServerEvent()){
            return "\'" + this.getId() + "\', " + Long.valueOf(this.getDuration().getTime()).toString() + ", \'" + this.getType() + "\', \'" + this.getHost() + "\', " + this.isflagged();
        }else{
            return "\'" + this.getId() + "\', " + Long.valueOf(this.getDuration().getTime()).toString() + ", NULL, NULL, " + this.isflagged();
        }
    }
}
