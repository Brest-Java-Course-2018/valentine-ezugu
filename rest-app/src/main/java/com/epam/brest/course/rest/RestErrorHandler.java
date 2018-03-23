package com.epam.brest.course.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller advice works by finding.
 * and mapping errors found during controller execution.
 */
@ControllerAdvice
public class RestErrorHandler {
    /**
     * log for debug.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     *
     * @param exception to mapped.
     * @return message of exception.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataAccessException.class)
    public final  @ResponseBody String handleDataAccessException(
            final DataAccessException exception) {
        LOGGER.debug("handleDataAccesException({})", exception);
        return "Data -# Access -# Exception" + exception.getLocalizedMessage();
    }
}
