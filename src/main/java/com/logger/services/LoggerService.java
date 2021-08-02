package com.logger.services;
//FINISHED

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.logger.controllers.entitites.ExecutionReport;
import com.logger.repositories.LogDatabaseRepository;
import com.logger.repositories.LogFileRepository;
import com.logger.services.models.Event;
import com.logger.utils.Logged;

public final class LoggerService implements Logged {
    
/* -------------------------------- CONSTANTS ------------------------------- */
    private static final Integer MAX_EVENT_DURATION = 5;
    
/* ------------------------------ INITALIZATION ----------------------------- */
    private final LogFileRepository logFileRepository;
    private final LogDatabaseRepository logDatabaseRepository;
    
/* ------------------------------ PUBLIC BASIC ------------------------------ */
    public LoggerService(final Path pathToLogFile, final Path pathToLogDatabase) throws SQLException {
        this.logFileRepository = new LogFileRepository(pathToLogFile);
        try{
            this.logDatabaseRepository = new LogDatabaseRepository(pathToLogDatabase);
        }catch(final SQLException e){
            logger().error("The connection to the database failed.");
            throw e;
        }
    }
    
    /* ----------------------------- PUBLIC COMPLEX ----------------------------- */
    /**
     * Read logs from input file and write it to the database after adding eventual flags
     * @param pathToFile The path to the input logs file. Should exist.
     * @param pathToDatabase The path to the database to write. The file can exist or not.
     * @return the operation report
     */
    public ExecutionReport computeFlagLog(){
        List<Event> eventList;
        try{
            eventList = this.logFileRepository.getAllEvent();
        }catch(final IOException e){
            return new ExecutionReport("INVALID_INPUT_FILE", e.getMessage());
        }
        
        final Map<String, Timestamp> eventBuffer = new HashMap<String, Timestamp>();
        Integer flaggedNumber = 0;
        Integer computedNumber = 0;
        for (final Event event : eventList){
            if (eventBuffer.containsKey(event.getId())){
                if (event.getStartTime() == null)
                    event.setStartTime(eventBuffer.remove(event.getId()));
                else
                    event.setEndTime(eventBuffer.remove(event.getId()));
                if (event.isComplete()){
                    if (event.getDuration().getTime() > MAX_EVENT_DURATION){
                        event.setFlagged(true);
                        flaggedNumber++;
                    }else if (event.getDuration().getTime() < 0){
                        logger().warn("The event " + event.getId() + " has a negative duration.");
                    }
                }
                try {
                    this.logDatabaseRepository.addNewEvent(event);
                    computedNumber++;
                }catch(final SQLException e){
                    return new ExecutionReport("SQL_ERROR", "[" + event.getId().toString() + "] ---> " + e.getMessage());
                }
            }else{
                if (event.getStartTime() == null){
                    eventBuffer.put(event.getId(), event.getEndTime());
                }else{
                    eventBuffer.put(event.getId(), event.getStartTime());
                }
            }
        }
        
        if (!eventBuffer.isEmpty()){
            logger().warn(eventBuffer.size() + " events are incompleted and could not be computed");
        }
        return new ExecutionReport(computedNumber, flaggedNumber);
    }
}
