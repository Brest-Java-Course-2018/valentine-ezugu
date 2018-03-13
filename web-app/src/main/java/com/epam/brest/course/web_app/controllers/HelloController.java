package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class HelloController {

    @GetMapping(value = "/")
    public String helloWorld(Model model) {
        return  "redirect:hello";

    }
    @GetMapping(value = "/hello")
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "world")
                                    String name, Model model){

        model.addAttribute("name", name);
        return "hello";
    }

}
