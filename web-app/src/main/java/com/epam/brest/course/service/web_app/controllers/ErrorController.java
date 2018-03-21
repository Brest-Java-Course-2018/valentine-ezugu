package com.epam.brest.course.service.web_app.controllers;

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

    private static final Logger LOGGER = LogManager.getLogger();

    @ExceptionHandler(value = Exception.class)
    public String handleControllerError(HttpServletRequest request, Exception exception){

        LOGGER.error("Request" + request.getRequestURL() + "threw " + exception );
        return "error";
    }

}
