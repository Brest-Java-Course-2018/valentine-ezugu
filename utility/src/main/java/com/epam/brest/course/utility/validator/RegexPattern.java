package com.epam.brest.course.utility.validator;

import java.util.regex.Pattern;

public class RegexPattern {

   public static Pattern datePattern =
             Pattern.compile("^((19|2[0-9])[0-9]{2})" +
                    "-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");
}
