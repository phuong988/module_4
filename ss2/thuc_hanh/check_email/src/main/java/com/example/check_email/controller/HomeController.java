package com.example.check_email.controller;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class HomeController {
    private static final String regex_email = "^[a-zA-Z0-9._%+-]+[a-zA-Z0-9]@[a-zA-Z0-9]+[.][a-zA-Z]{2,4}$";
    private static Pattern pattern ;

    public HomeController() {
        pattern = Pattern.compile(regex_email);
    }

    @GetMapping(value = "/")
    public String home() {
        return "index";
    }

    @PostMapping(value = "/validate")
    public String user(@RequestParam("email") String email, ModelMap modelMap) {
        boolean isValid = this.validate(email);
        if (!isValid) {
            modelMap.addAttribute("message", "Email is invalid");
            return "index";
        }
        modelMap.addAttribute("email", email);
        return "success";
    }

    private boolean validate(String regex) {
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
