package com.logger.services.models;
//FINISHED


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.sql.Timestamp;

import org.junit.Test;

public class EventTest {
    @Test
    public void testGetDuration() {
        Long time = System.currentTimeMillis();
        final Event event = new Event ("Annabelle", new Timestamp(time), true);
        event.setStartTime(new Timestamp(time - 10));
        assertTrue(Long.valueOf(event.getDuration().getTime()).equals(Long.valueOf(10)));
    }

    @Test
    public void testIsComplete() {
        final Event event = new Event ("Annabelle", new Timestamp(System.currentTimeMillis()), true);
        assertFalse(event.isComplete());
        event.setStartTime(new Timestamp(System.currentTimeMillis()));
        assertTrue(event.isComplete());
    }

    @Test
    public void testIsFinished() {
        final Event event = new Event ("Annabelle", new Timestamp(System.currentTimeMillis()), true);
        assertTrue(event.isFinished());
        event.setStartTime(new Timestamp(System.currentTimeMillis()));
        event.setEndTime(null);
        assertFalse(event.isFinished());   
    }

    @Test
    public void testIsServerEventTrue() {
        final Event event = new Event ("Annabelle", new Timestamp(System.currentTimeMillis()), true);
        assertFalse(event.isServerEvent());
    }
    
    @Test
    public void testIsServerEventFalse() {
        final Event event = new Event ("Annabelle", new Timestamp(System.currentTimeMillis()), true, "test", "test");
        assertTrue(event.isServerEvent());
    }
}
