package com.code.controller;

import com.code.repository.DocumenteRepository;
import com.code.repository.SessionRepository;
import com.code.service.MinIOService;
import com.code.service.SesiuneAdmitereService;
import com.code.service.TokenService;
import com.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/centru")
public class CentruController {
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

    @GetMapping("")
    String main_zonal_page(){ return "main_zonal";}

    @GetMapping("/zonal-main-error")
    public String zonalMainError(Model model){
        model.addAttribute("zonalMainError", true);
        return "main_zonal";
    }
}
