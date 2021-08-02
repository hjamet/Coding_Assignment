package com.logger.controllers.entitites;
//FINISHED

public final class ExecutionReport {
    
/* --------------------------------- PRIVATE -------------------------------- */
    private final boolean isSuccess;
    private final String errorCode;
    private final String errorMessage;
    
    private final Integer eventNumber;
    private final Integer flagNumber;
    
/* ------------------------------ PUBLIC BASIC ------------------------------ */
    public ExecutionReport(final Integer eventNumber, final Integer flagNumber) {
        this.eventNumber = eventNumber;
        this.flagNumber = flagNumber;
        
        this.isSuccess = true;
        this.errorCode = null;
        this.errorMessage = null;
    }
    
    public ExecutionReport(final String errorCode, final String errorMessage){
        this.eventNumber = null;
        this.flagNumber = null;
        
        this.isSuccess = false;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer getEventNumber() {
        return eventNumber;
    }

    public Integer getFlagNumber() {
        return flagNumber;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    
/* ----------------------------- PUBLIC COMPLEX ----------------------------- */
    /**
     * Get a formatted string explaining the error. The string is empty if there as no error.
     * @return
     */
    public String getFormatedError(){
        if (this.isSuccess){
            return "";
        }
        return "[" + this.errorCode + "]" + " " + this.errorMessage;
    }
}
