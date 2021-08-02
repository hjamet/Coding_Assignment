package com.logger.controllers;
//FINISHED

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.logger.utils.DatabaseReader;
import com.logger.utils.Log;

import org.junit.Test;

public class LoggerControllerTest {
    
    private final DatabaseReader databaseReader;
    
    public LoggerControllerTest(){
        this.databaseReader = new DatabaseReader();
    }
    
    @Test
    public void testFlagLog1() throws Exception {
        final File database = new File("logDatabase.mv.db");
        database.delete(); 
        try{
            final LoggerController controller = new LoggerController(Paths.get("src/test/ressources/input_1.txt").toString(), "logDatabase");
            controller.flagLog();
        }catch(final Exception e){
            System.err.println("A critical error occured during initialization. The program will now terminate.");
        }
        
        final ArrayList<Log> logList = this.databaseReader.databaseToList();
        assertTrue(logList.size() == 3);
        final Log log1 = new Log("scsmbstgra", Long.valueOf(5), "12345", "APPLICATION_LOG", false);
        final Log log2 = new Log("scsmbstgrc", Long.valueOf(8), "null", "null", true);
        final Log log3 = new Log("scsmbstgrb", Long.valueOf(3), "null", "null", false);
        assertTrue(this.databaseReader.containsLog(logList, log1, log2, log3));
    }
    
    @Test
    public void testFlagLog2() throws Exception {
        final File database = new File("logDatabase.mv.db");
        database.delete(); 
        try{
            final LoggerController controller = new LoggerController(Paths.get("src/test/ressources/input_2.txt").toString(), "logDatabase");
            controller.flagLog();
        }catch(final Exception e){
            System.err.println("A critical error occured during initialization. The program will now terminate.");
        }
        
        final ArrayList<Log> logList = this.databaseReader.databaseToList();
        assertTrue(logList.size() == 3);
        final Log log1 = new Log("scsmbstgra", Long.valueOf(5), "12345", "APPLICATION_LOG", false);
        final Log log2 = new Log("scsmbstgrc", Long.valueOf(8), "null", "null", true);
        final Log log3 = new Log("scsmbstgrb", Long.valueOf(43), "null", "null", true);
        assertTrue(this.databaseReader.containsLog(logList, log1, log2, log3));
    }
    
    @Test
    public void testFlagLog3() throws Exception {
        final File database = new File("logDatabase.mv.db");
        database.delete(); 
        try{
            final LoggerController controller = new LoggerController(Paths.get("src/test/ressources/input_3.txt").toString(), "logDatabase");
            controller.flagLog();
        }catch(final Exception e){
            System.err.println("A critical error occured during initialization. The program will now terminate.");
        }
        
        final ArrayList<Log> logList = this.databaseReader.databaseToList();
        assertTrue(logList.size() == 3);
        final Log log1 = new Log("scsmbstgra", Long.valueOf(5), "12345", "APPLICATION_LOG", false);
        final Log log2 = new Log("scsmbstgrc", Long.valueOf(8), "null", "null", true);
        final Log log3 = new Log("scsmbstgrb", Long.valueOf(3), "null", "null", false);
        assertTrue(this.databaseReader.containsLog(logList, log1, log2, log3));
    }
    
    @Test
    public void testFlagLog4() throws Exception {
        final File database = new File("logDatabase.mv.db");
        database.delete(); 
        try{
            final LoggerController controller = new LoggerController(Paths.get("src/test/ressources/input_4.txt").toString(), "logDatabase");
            controller.flagLog();
        }catch(final Exception e){
            System.err.println("A critical error occured during initialization. The program will now terminate.");
        }
        
        final ArrayList<Log> logList = this.databaseReader.databaseToList();
        assertTrue(logList.size() == 3);
        final Log log1 = new Log("scsmbstgra", Long.valueOf(5), "12345", "APPLICATION_LOG", false);
        final Log log2 = new Log("scsmbstgrc", Long.valueOf(-8), "null", "null", false);
        final Log log3 = new Log("scsmbstgrb", Long.valueOf(3), "null", "null", false);
        assertTrue(this.databaseReader.containsLog(logList, log1, log2, log3));
    }
    
    @Test
    public void testFlagLog5() throws Exception {
        final File database = new File("logDatabase.mv.db");
        database.delete(); 
        try{
            final LoggerController controller = new LoggerController(Paths.get("src/test/ressources/input_5.txt").toString(), "logDatabase");
            controller.flagLog();
        }catch(final Exception e){
            System.err.println("A critical error occured during initialization. The program will now terminate.");
        }
        
        final ArrayList<Log> logList = this.databaseReader.databaseToList();
        assertTrue(logList.size() == 3);
        final Log log1 = new Log("scsmbstgra", Long.valueOf(5), "12345", "APPLICATION_LOG", false);
        final Log log2 = new Log("scsmbstgrc", Long.valueOf(-8), "null", "null", false);
        final Log log3 = new Log("scsmbstgrb", Long.valueOf(3), "null", "null", false);
        assertTrue(this.databaseReader.containsLog(logList, log1, log2, log3));
    }
    
    @Test
    public void testFlagLog6() throws Exception {
        final File database = new File("logDatabase.mv.db");
        database.delete(); 
        try{
            final LoggerController controller = new LoggerController(Paths.get("src/test/ressources/input_6.txt").toString(), "logDatabase");
            controller.flagLog();
            assertTrue(false);
        }catch(final Exception e){
            assertTrue(true);
        }
    }
        
    @Test
    public void testFlagLog7() throws Exception {
        final File database = new File("logDatabase.mv.db");
        database.delete(); 
        try{
            final LoggerController controller = new LoggerController(Paths.get("src/test/ressources/input_7.txt").toString(), "logDatabase");
            controller.flagLog();
            assertTrue(false);
        }catch(final Exception e){
            assertTrue(true);
            System.err.println("A critical error occured during initialization. The program will now terminate.");
        }
    }
}
