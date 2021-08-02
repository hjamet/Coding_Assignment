package com.logger.repositories.records;
//FINISHED

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class SqlRecord {
    
/* -------------------------------- CONSTANT -------------------------------- */
    protected String REQUEST;
    
/* ------------------------------- INITIALIZE ------------------------------- */
    private final Connection connection;
    
/* ------------------------------ PUBLIC BASIC ------------------------------ */
    public SqlRecord(final Connection connection){
        this.connection = connection;
    }
    
    /* ----------------------------- PUBLIC COMPLEX ----------------------------- */
    /**
     * Execute the record on the given database.
     * @return The resultSet of returned request value
     * @throws SQLException if an error occurs during execution.
     */
    public final ResultSet execute() throws SQLException {
        final Statement statement = this.connection.createStatement();
        statement.execute(this.REQUEST);
        return statement.getResultSet();
    }
}
