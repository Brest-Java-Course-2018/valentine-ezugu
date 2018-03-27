package com.epam.brest.course.client;

/**
 * Runtime exception handler.
 */
public class ServletDataAccesException extends RuntimeException {

    /**
     *
     * @param message exception.
     */
    public ServletDataAccesException(final String message) {
        super(message);
    }

}
