package com.logger.repositories.records;
//FINISHED

import java.sql.Connection;

import com.logger.services.models.Event;

public final class AddNewEventRecord extends SqlRecord {
    
/* ------------------------------ PUBLIC BASIC ------------------------------ */
    public AddNewEventRecord(final Connection connection, final Event newEvent, final String TableName){
        super(connection);
        this.REQUEST = "INSERT INTO " + TableName + " VALUES (" + newEvent.toString() + ")";
    }
}
