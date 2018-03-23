package com.epam.brest.course.service.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home MVC controller.
 */
@Controller
public class HomeController {

    /**
     *
     * @return departments.
     */
    @GetMapping(value = "/")
    public final String defaultPageRedirect() {
        return "redirect:departments";
    }
}
