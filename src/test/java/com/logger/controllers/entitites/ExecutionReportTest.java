package com.logger.controllers.entitites;
//FINISHED

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExecutionReportTest {
    @Test
    public void testGetFormatedErrorWithNoError() {
        final ExecutionReport report = new ExecutionReport(5, 6);
        assertEquals(report.getFormatedError(), "");
    }
    
    @Test
    public void testGetFormatedErrorWithError() {
        final ExecutionReport report = new ExecutionReport("INVALID_INPUT_FILE", "The input file is invalid");
        assertEquals(report.getFormatedError(), "[INVALID_INPUT_FILE] The input file is invalid");
    }
}
