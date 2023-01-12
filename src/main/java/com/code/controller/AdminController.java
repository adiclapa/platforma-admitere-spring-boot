package com.code.controller;

import com.code.payload.requests.RegisterRequest;
import com.code.payload.requests.RegisterRequestAdmin;
import com.code.repository.DocumenteRepository;
import com.code.repository.SessionRepository;
import com.code.service.MinIOService;
import com.code.service.SesiuneAdmitereService;
import com.code.service.TokenService;
import com.code.service.UserService;
import com.code.utils.UserRolesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService appUserService;
    @Autowired
    DocumenteRepository userDocs;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    SesiuneAdmitereService sesiuneAdmitereService;
    @Autowired
    UserService userService;
    @Autowired
    TokenService jwtService;
    @Autowired
    MinIOService minIOService;

    @GetMapping("/")
    String main_page(Model model){
        model.addAttribute("userList", appUserService.getAllUsers());
        return "main_admin";
    }

    @GetMapping("/admin-error")
    public String adminMainError(Model model){
        model.addAttribute("adminMainError", true);
        return "main_admin";
    }

    @GetMapping("/admiteri")
    public String admiteri(Model model){
        model.addAttribute("admiteri", sesiuneAdmitereService.getAllSessions());
        return "admiteri";
    }

    @GetMapping("/form-sesiune")
    public String admitereNoua(Model model){

        return "form-sesiune";
    }

    @GetMapping("/register-page")
    public String register() {
        return "admin-register-page";
    }

    @PostMapping("/register")
    public String registerForm(RegisterRequestAdmin registerRequest, Model model){
        String token = appUserService.signUpUser(registerRequest);
        if(token.isEmpty()){
            model.addAttribute("registerError", true);
            return "admin-register-page";
        }

        model.addAttribute("userList", appUserService.getAllUsers());
        return "main_admin";
    }
}
