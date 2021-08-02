package com.logger.repositories;
//FINISHED

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.logger.repositories.records.EventRecord;
import com.logger.services.models.Event;
import com.logger.utils.Logged;

public final class LogFileRepository implements Logged{
    
/* ------------------------------ INITALIZATION ----------------------------- */
    private final Path pathToFile;
    
/* ------------------------------ PUBLIC BASIC ------------------------------ */
    public LogFileRepository(final Path pathToFile) {
        this.pathToFile = pathToFile;
        if (!Files.exists(this.pathToFile)){
            logger().warn("The file " + this.pathToFile + " does not exist yet.");
        }
    }
    
    /* ----------------------------- PUBLIC COMPLEX ----------------------------- */
    /**
     * Get the list of all events listed in the input file
     * @return a list of Events
     * @throws IOException if the log's file could not be read
     */
    public final List<Event> getAllEvent() throws IOException {
        final ArrayList<Event> result = new ArrayList<Event>();
		BufferedReader reader;
        reader = new BufferedReader(new FileReader(
                this.pathToFile.toAbsolutePath().toString()));
        String line = reader.readLine();
        while (line != null) {
            final Event event = this.createNewEvent(line);
            if (event == null){
                logger().warn("An event could not be parse.");
            }else{
                result.add(event);
            }
            line = reader.readLine();
            
        }
        reader.close();
        return result;
    }
    
    /* --------------------------------- PRIVATE -------------------------------- */
    /**
     * Create an event object by parsing the given line
     * @param line the line to parse
     * @return the event created of null if the line could not be unserialized
     */
    private Event createNewEvent(final String line) {
        try{
            final EventRecord eventRecord = new Gson().fromJson(line, EventRecord.class);
            if (eventRecord.getHost() != null){
                return new Event(eventRecord.getId(), new Timestamp(eventRecord.getTimestamp()), eventRecord.getState().equals("FINISHED"), eventRecord.getHost(), eventRecord.getType());
            }else{
                return new Event(eventRecord.getId(), new Timestamp(eventRecord.getTimestamp()), eventRecord.getState().equals("FINISHED"));
            }
        }catch(final JsonSyntaxException e) {
            return null;
        }
    }
}
