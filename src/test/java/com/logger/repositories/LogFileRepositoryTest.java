package com.logger.repositories;
//FINISHED

import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import com.logger.services.models.Event;
import com.logger.utils.DatabaseReader;

import org.junit.Test;

public class LogFileRepositoryTest {
    
    DatabaseReader databaseReader = new DatabaseReader();
    
    @Test
    public void testGetAllEvent() {
        LogFileRepository repository = new LogFileRepository(Paths.get("src/test/ressources/input_1.txt"));
        List<Event> eventList;
        try{
            eventList =  repository.getAllEvent();
            Event event1 = new Event("scsmbstgra", new Timestamp(1491377495212L), false, "12345", "APPLICATION_LOG");
            Event event2 = new Event("scsmbstgrb", new Timestamp(1491377495213L), false);
            Event event3 = new Event("scsmbstgrc", new Timestamp(1491377495218L), true);
            Event event4 = new Event("scsmbstgra", new Timestamp(1491377495217L), true, "12345", "APPLICATION_LOG");
            Event event5 = new Event("scsmbstgrc", new Timestamp(1491377495210L), false);
            Event event6 = new Event("scsmbstgrb", new Timestamp(1491377495216L), true);
            
            assertTrue(databaseReader.areEventEqual(eventList.get(0), event1));
            assertTrue(databaseReader.areEventEqual(eventList.get(1), event2));
            assertTrue(databaseReader.areEventEqual(eventList.get(2), event3));
            assertTrue(databaseReader.areEventEqual(eventList.get(3), event4));
            assertTrue(databaseReader.areEventEqual(eventList.get(4), event5));
            assertTrue(databaseReader.areEventEqual(eventList.get(5), event6));
            
        }catch(Exception e){
            assertTrue(false);
        }
    }
}
