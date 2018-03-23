package com.epam.brest.course.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *Version control to test rest.
 */
@RestController
public class VersionController {

    /**
     *version.
     */
    public static final String VERSION = "1.0";

    /**
     *
     * @return version value.
     */
    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public final String getVersion() {
        return VERSION;
    }


}

