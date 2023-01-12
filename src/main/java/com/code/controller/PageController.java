package com.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    String home_page(){
        return "home";
    }
    @GetMapping("/home-error")
    public String homeError(Model model){
        model.addAttribute("homeError",true);
        return "home";
    }

    @GetMapping("/login-page")
    String login_page(){
        return "login-page";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login-page";
    }

}
