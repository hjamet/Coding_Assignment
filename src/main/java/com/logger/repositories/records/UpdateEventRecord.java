package com.logger.repositories.records;
//FINISHED

import java.sql.Connection;

import com.logger.services.models.Event;

public final class UpdateEventRecord extends SqlRecord {
    
/* ------------------------------ PUBLIC BASIC ------------------------------ */
    public UpdateEventRecord(final Connection connection, final Event newEvent, final String tableName){
        super(connection);
        this.REQUEST = "UPDATE " + tableName + " SET DURATION = " + Long.valueOf(newEvent.getDuration().getTime()).toString() + " WHERE ID = \'" + newEvent.getId() + "\'; ";
        this.REQUEST += "UPDATE " + tableName + " SET TYPE = \'" + newEvent.getType() + "\' WHERE ID = \'" + newEvent.getId() + "\'; ";
        this.REQUEST += "UPDATE " + tableName + " SET ALERT = \'" + newEvent.isflagged() + "\' WHERE ID = \'" + newEvent.getId() + "\'; ";
        this.REQUEST += "UPDATE " + tableName + " SET HOST = \'" + newEvent.getHost() + "\' WHERE ID = \'" + newEvent.getId() + "\'";
    }
}
