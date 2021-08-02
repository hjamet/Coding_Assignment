package com.logger.repositories;
//FINISHED

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.logger.repositories.records.AddNewEventRecord;
import com.logger.repositories.records.CreateTableRecord;
import com.logger.repositories.records.GetEventListRecord;
import com.logger.repositories.records.UpdateEventRecord;
import com.logger.services.models.Event;
import com.logger.utils.Logged;

public final class LogDatabaseRepository implements Logged {
    
/* -------------------------------- CONSTANT -------------------------------- */
    private static final String TABLE_NAME = "Logs";
    
/* ------------------------------ INITALIZATION ----------------------------- */
    private final Path pathToDatabase;
    
/* ------------------------------ PUBLIC BASIC ------------------------------ */
    public LogDatabaseRepository(final Path pathToDatabase) throws SQLException {
        this.pathToDatabase = pathToDatabase;
        this.getConnection();
    }
    
/* ----------------------------- PUBLIC COMPLEX ----------------------------- */
    /**
     * Add an event to the database. If the databse does not exist, create it.
     * If an event with the same id already exist, replace it.
     * @param event The event to add.
     * @throws SQLException in case of error while adding event.
     */
    public void addNewEvent(final Event event) throws SQLException {
        final GetEventListRecord eventListRecord = new GetEventListRecord(this.connection, this.TABLE_NAME);
        final ResultSet result = eventListRecord.execute();
        while (result.next()){
            if (event.getId().equals(result.getString("id"))) {
                logger().warn("A similar event was already present in the database.");
                final UpdateEventRecord updateEvent = new UpdateEventRecord(this.connection, event, this.TABLE_NAME);
                updateEvent.execute();
                return;
            }
        }
        
        final AddNewEventRecord addNewEvent = new AddNewEventRecord(this.connection, event, this.TABLE_NAME);
        addNewEvent.execute();
    }
    
/* --------------------------------- PRIVATE -------------------------------- */
    private Connection connection = null;
    
    /**
     * Get the connection to the database and ensure that everything is in order.
     * @return the connection to the database.
     * @throws SQLException if there were an error while quering the connection.
     */
    private final Connection getConnection() throws SQLException {
        if (this.connection != null){
            return this.connection;
        }
        this.connection = this.connextToDatabase();
        this.createTable();
        return this.connection;
    }
    
    /**
     * Connect to the database and create it if it doesn't exist.
     * @return the connection to the database.
     * @throws SQLException if there is an error while connecting to the database.
     */
    private Connection connextToDatabase() throws SQLException {
        final String url = "jdbc:h2:" + this.pathToDatabase.toAbsolutePath().toString();
        final Connection connexion = DriverManager.getConnection(url);
        if (connexion == null){
            throw new SQLException("No connection could be loaded.");
        }
        return connexion;
    }
    
    /**
     * Check wether a table exists in the database or not. If the connexion is null, instantiate it.
     * @param tableName The name of the table to look after.
     * @return true if the table exists, false otherwise.
     * @throws SQLException if the connection was not and could not be established.
     */
    private final boolean tableExists(final String tableName) throws SQLException {
        if (this.connection == null){
            this.connection = this.connextToDatabase();
        }
        final DatabaseMetaData databaseMetaData = this.connection.getMetaData();
        final ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[] {"TABLE"});

        while (resultSet.next()) {
            if (tableName.toUpperCase().equals(resultSet.getString("TABLE_NAME"))){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Create the table in the database if it was not yet created.
     * @throws SQLException if there was an error during the creation.
     */
    private final void createTable() throws SQLException {
        if (!this.tableExists(this.TABLE_NAME)){
            this.logger().info("Creating a new table...");
            final CreateTableRecord createTable = new CreateTableRecord(this.connection, this.TABLE_NAME);
            createTable.execute();
            this.logger().info("A new table was created.");
        }
    }
}