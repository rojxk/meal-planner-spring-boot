package com.mealplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login-page")
    public String showLoginPage(){

        return "login/login-page";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "error/access-denied";
    }
}
