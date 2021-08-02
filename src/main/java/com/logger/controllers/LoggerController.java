package com.logger.controllers;
//FINISHED

import java.nio.file.Paths;
import java.sql.SQLException;

import com.logger.controllers.entitites.ExecutionReport;
import com.logger.services.LoggerService;
import com.logger.utils.Logged;

public final class LoggerController implements Logged {

    
/* ------------------------------ INITALIZATION ----------------------------- */
    private final LoggerService loggerService;

/* ------------------------------ PUBLIC BASIC ------------------------------ */
    public LoggerController(final String inputPath, final String DatabaseName) throws ExceptionInInitializerError {
        try{
            this.loggerService = new LoggerService(Paths.get(inputPath), Paths.get(DatabaseName));
        }catch(final SQLException e){
            throw new ExceptionInInitializerError(e.getMessage());
        }
    }
    
/* --------------------------------- PUBLIC --------------------------------- */
    /**
    * Read inut file and write or update the database in functional
    */
    public final void flagLog(){
        final ExecutionReport report = this.loggerService.computeFlagLog();
        if (!report.isSuccess()){
            logger().error(report.getFormatedError());
        }
        logger().info(report.getEventNumber().toString() + " events were successfully computed.");
        logger().info(report.getFlagNumber().toString() + " events were flagged");
    }   
}
