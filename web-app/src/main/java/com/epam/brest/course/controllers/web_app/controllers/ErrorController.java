package com.epam.brest.course.controllers.web_app.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * exception handling class.
 */
@ControllerAdvice
public class ErrorController {
    /**
     *
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     *
     * @param request for request coming in.
     * @param exception if error the log this error.
     * @return template page.
     */
    @ExceptionHandler(value = Exception.class)
    public final String handleControllerError(final HttpServletRequest request,
                                         final Exception exception) {

        LOGGER.error("Request" + request.getRequestURL() + " threw "
               + " " + exception);
        return "error";
    }

}
