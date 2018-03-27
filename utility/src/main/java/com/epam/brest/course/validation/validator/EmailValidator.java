package com.epam.brest.course.validation.validator;

import java.util.regex.Pattern;

/**
 * This class contains email validation.
 */
final class EmailValidator {
    /**
     * regex.
     */
    public static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"

            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    /**
     * constructor.
     */
    private EmailValidator() {
    }
}

