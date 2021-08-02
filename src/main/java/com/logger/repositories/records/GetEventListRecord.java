package com.logger.repositories.records;
//FINISHED

import java.sql.Connection;

public final class GetEventListRecord extends SqlRecord {
    
/* ------------------------------ PUBLIC BASIC ------------------------------ */
    public GetEventListRecord(final Connection connection, final String tableName){
        super(connection);
        this.REQUEST = "SELECT * FROM " + tableName;
    }
}