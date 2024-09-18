package com.mealplanner.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error/error-404.html")
    public String handleError404() {
        return "/error/error-404";
    }

    public String getErrorPath() {
        return "/error/error-404.html";
    }
}
