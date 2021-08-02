package com.logger.repositories;
//FINISHED

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import com.logger.services.models.Event;
import com.logger.utils.DatabaseReader;
import com.logger.utils.Log;

import org.junit.Test;

public class LogDatabaseRepositoryTest {
    
    private final DatabaseReader databaseReader = new DatabaseReader();
    
    @Test
    public void testAddNewEventNotFlagged() {
        final File database = new File("logDatabase.mv.db");
        final Event event = new Event("id", new Timestamp(150), false);
        event.setEndTime(new Timestamp(155));
        database.delete();
        try{
            final LogDatabaseRepository repository = new LogDatabaseRepository(Paths.get("logDatabase"));
            repository.addNewEvent(event);
        }catch(final Exception e){
            assertTrue(false);
        }
        
        final List<Log> logList = databaseReader.databaseToList();
        assertTrue(databaseReader.containsLog(logList, new Log("id", Long.valueOf(5), "null", "null", false)));
    }
    
    @Test
    public void testAddNewEventFlagged() {
        final File database = new File("logDatabase.mv.db");
        final Event event = new Event("id", new Timestamp(150), false);
        event.setEndTime(new Timestamp(156));
        database.delete();
        try{
            final LogDatabaseRepository repository = new LogDatabaseRepository(Paths.get("logDatabase"));
            repository.addNewEvent(event);
        }catch(final Exception e){
            assertTrue(false);
        }
        
        final List<Log> logList = databaseReader.databaseToList();
        assertTrue(databaseReader.containsLog(logList, new Log("id", Long.valueOf(6), "null", "null", true)));
    }
}
