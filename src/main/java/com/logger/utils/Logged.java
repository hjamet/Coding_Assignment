package com.logger.utils;
//FINISHED

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Logged {

    /**
     * Get logger
     *
     * @return Logger
     */
    default Logger logger() {
        return LoggerFactory.getLogger(getClass());
    }
}