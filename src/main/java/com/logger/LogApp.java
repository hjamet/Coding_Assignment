package com.logger;
//FINISHED

import java.text.ParseException;

import com.logger.controllers.LoggerController;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

public class LogApp
{
    public static void main( final String[] args ){
        ArgumentParser parser = ArgumentParsers.newFor("LogApp").build()
                .defaultHelp(true)
                .description("Log events from a given file to a database. Flags them if their duration is greater than 5ms.");
        parser.addArgument("-f", "--file").setDefault("input.txt")
                .help("The input file to compute");
        parser.addArgument("-d", "--database").setDefault("logDatabase")
                .help("The database where the processed events are stored");

        LoggerController controller = null;
        
        Namespace argsProcessed = null;
        try{
            argsProcessed = parser.parseArgs(args); 
        }catch(ArgumentParserException e){
            parser.handleError(e);
            System.exit(1);
        }

        try{
            controller = new LoggerController(argsProcessed.getString("file"), argsProcessed.getString("database"));
        }catch(final Exception e){
            System.err.println("A critical error occured during initialization. The program will now terminate.");
        }
        if (controller != null)
            controller.flagLog();
    }
}
