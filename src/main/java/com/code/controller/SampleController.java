package com.code.controller;

import com.code.payload.requests.LoginRequest;
import com.code.payload.responses.LoginResponse;
import com.code.repository.DocumenteRepository;
import com.code.repository.SessionRepository;
import com.code.service.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class SampleController {

    @Autowired
    UserService appUserService;

    @Autowired
    TokenService jwtService;

    // Login form
    @RequestMapping("/login")
    public ModelAndView login(LoginRequest request, HttpServletResponse res){
            LoginResponse response = appUserService.signInUser(request);
            ModelAndView page = new ModelAndView();

            Object roles = null;
            if(response != null)
                roles = jwtService.parseClaims(response.getAccessToken()).get("roles");
            else{
                page.setViewName("login-page.html");
                page.addObject("loginError", true);
                return page;
            }

            if ("ROLE_ADMIN".equals(roles)) {
                page.setViewName("main_admin.html");
                page.addObject("userList", appUserService.getAllUsers());
            }
            if ("ROLE_SECRETAR".equals(roles)) {
                page.setViewName("main_secretary.html");
            }
            if ("ROLE_CANDIDAT".equals(roles)) {
                page.setViewName("main_candidat.html");
            }
            if ("ROLE_CENTRU".equals(roles)) {
                page.setViewName("main_zonal.html");
            }

            page.setStatus(HttpStatusCode.valueOf(200));
            page.addObject(response);
            res.addCookie(new Cookie("accessToken", response.getAccessToken()));
            return page;
    }

//    @PostMapping("/specializari")
//    public ArrayList<SpecializariRequest> specializari() {
//        ArrayList<SpecializariRequest> spec_final = new ArrayList<SpecializariRequest>();
//        List<FacultatiEntity> fac = appFacultatiService.getFac();
//        List<SpecializariEntity> spec = appSpecializariService.getSpec();
//        spec.forEach((n) -> {
//            SpecializariRequest s = new SpecializariRequest(n.getNume(), n.getLocuri(), fac.get(n.getIdFacultate() - 1).getDenumire(), n.getId());
//            spec_final.add(s);
//        });
//        return spec_final;
//    }
}