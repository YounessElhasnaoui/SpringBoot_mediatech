package com.estc.mediatech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage(Model model) {
        // If you want, you can pass some model attributes here
        return "home"; // This corresponds to "home.html" in "src/main/resources/templates/"
    }
}
