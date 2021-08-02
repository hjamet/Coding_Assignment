package com.logger.repositories.records;
//FINISHED

import java.sql.Connection;

public final class CreateTableRecord extends SqlRecord {
    
/* ------------------------------ PUBLIC BASIC ------------------------------ */
    public CreateTableRecord(final Connection connection, final String tableName){
        super(connection);
        this.REQUEST = "CREATE TABLE " + tableName + "( ID VARCHAR(64) NOT NULL, DURATION BIGINT(256) NOT NULL, TYPE VARCHAR(64), HOST VARCHAR(64), ALERT BOOLEAN, PRIMARY KEY(ID))";
    }  
}
