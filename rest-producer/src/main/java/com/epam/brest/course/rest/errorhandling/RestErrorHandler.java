package com.epam.brest.course.rest.errorhandling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;
import java.util.Locale;

/**
 * error handler.
 */
@ControllerAdvice
public class RestErrorHandler {
    /**
     * logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @param e exception.
     * @return string and local message.
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    String handleDataAccessException(
                                             DataAccessException e) {
        LOGGER.debug("handleDataAccessException({})", e);
        return "DataAccessException: " + e.getLocalizedMessage();
    }


    /**
     * @param e exception.
     * @return string and local message.
     */
    @ExceptionHandler(ParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleIllegalArgumentException(ParseException e) {
        LOGGER.debug("handleIllegalArgumentException({})", e);
        return "Parse-Exception: " + e.getLocalizedMessage();
    }


    /**
     * @param e exception.
     * @return string and local message.
     */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String IllegalStateException(IllegalStateException e) {
        LOGGER.debug("IllegalStateException({})", e);
        return "IllegalStateException: " + e.getLocalizedMessage();
    }

    /**
     * message source.
     */
    @Autowired
    private MessageSource msgSource;

    /**
     *
     * @param ex x.
     * @return message.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO processValidationError(
                            MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        return processFieldError(error);
    }

    /**
     *
     * @param error error.
     * @return
     */
    private MessageDTO processFieldError(FieldError error) {
        MessageDTO message = null;
        if (error != null) {
            Locale currentLocale = LocaleContextHolder.getLocale();
            String msg = msgSource.getMessage(error.getCode(),
                    error.getCodes(),

                    null, currentLocale);
            message = new MessageDTO(MessageType.ERROR, msg);
        }
        return message;
    }

}
