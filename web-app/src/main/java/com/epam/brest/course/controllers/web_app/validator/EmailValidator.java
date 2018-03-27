package com.epam.brest.course.controllers.web_app.validator;

import java.util.regex.Pattern;

/**
 * This class contains email validation for
 */
public class EmailValidator {
    /**
     * regex
     */
    public static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"

            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

}

