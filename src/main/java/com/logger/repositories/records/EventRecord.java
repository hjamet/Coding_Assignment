package com.logger.repositories.records;
//FINISHED

public final class EventRecord {
    
/* ------------------------------ INTIALIZATION ----------------------------- */
    private final String id;
    private final String state;
    private final Long timestamp;
    private String type;
    private String host = null;
    
    
/* ------------------------------ PUBLIC BASIC ------------------------------ */

    public EventRecord(final String id, final String state, final Long timestamp) {
        this.id = id;
        this.state = state;
        this.timestamp = timestamp;
    }
    public EventRecord(final String id, final String state, final String type, final String host, final Long timestamp) {
        this.id = id;
        this.state = state;
        this.type = type;
        this.host = host;
        this.timestamp = timestamp;
    }
        public String getId() {
        return id;
    }
    public String getState() {
        return state;
    }
    public String getType() {
        return type;
    }
    public String getHost() {
        return host;
    }
    public void setHost(final String host) {
        this.host = host;
    }
    public Long getTimestamp() {
        return timestamp;
    }
    public void setType(final String type) {
        this.type = type;
    }
}
