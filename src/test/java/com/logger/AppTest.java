package com.logger;

import static org.junit.Assert.assertTrue;

import com.logger.controllers.LoggerControllerTest;
import com.logger.controllers.entitites.ExecutionReportTest;
import com.logger.repositories.LogDatabaseRepositoryTest;
import com.logger.repositories.LogFileRepositoryTest;
import com.logger.services.models.EventTest;

import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    
    @RunWith(Suite.class)
    @Suite.SuiteClasses({
    ExecutionReportTest.class,
    LoggerControllerTest.class,
    LogDatabaseRepositoryTest.class,
    LogFileRepositoryTest.class,
    EventTest.class
    })
    public class MyTestSuite {
    }
    
    /**
     * Rigorous Test :-)
     */
    @Test
    public static void main(String[] args){
        assertTrue( true );
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        Result result = junit.run(MyTestSuite.class);
    }
}
