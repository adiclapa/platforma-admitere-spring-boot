package com.code.controller;

import com.code.model.SesiuniAdmitereEntity;
import com.code.payload.requests.DocCheckForm;
import com.code.payload.requests.RegisterRequest;
import com.code.payload.responses.DocumenteCandidat;
import com.code.payload.responses.UserDTO;
import com.code.repository.DocumenteRepository;
import com.code.repository.SessionRepository;
import com.code.service.MinIOService;
import com.code.service.SesiuneAdmitereService;
import com.code.service.TokenService;
import com.code.service.UserService;
import com.code.utils.UserRolesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/secretar")
public class SecretarController {
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
    String main_secretary_page(){ return "main_secretary";}

    @GetMapping("/home-error")
    public String secretaryMainError(Model model){
        model.addAttribute("secretaryMainError", true);
        return "main_secretary";
    }

    @GetMapping("/register-page")
    public String register(){
        return "centru-register-page";
    }

    @PostMapping("/register")
    public String registerForm(RegisterRequest registerRequest, Model model){
        String token = appUserService.signUpUser(registerRequest, UserRolesEnum.CANDIDAT.value);
        if(token.isEmpty()){
            model.addAttribute("registerError", true);
            return "centru-register-page";
        }

        return "main_secretary";
    }

    @GetMapping("/candidati")
    public String Candidati(Model model){
        Date now = Date.from(Instant.now());
        List<UserDTO> users = new ArrayList<UserDTO>();

        SesiuniAdmitereEntity currentSession = sessionRepository.getLastSession();
        if(!currentSession.getEndDate().after(Date.from(Instant.now())) &&
            !currentSession.getStartDate().before(Date.from(Instant.now()))){
            model.addAttribute("errorMsg", true);
            model.addAttribute("candidati", users);
            return "candidati";
        }

        users = appUserService.getUsersFromCurrentSession();
        if(users.isEmpty()){
            model.addAttribute("errorMsg", true);
            model.addAttribute("candidati", users);
            return "candidati";
        }
        model.addAttribute("candidati", users);
        return "candidati";
    }

    @PostMapping("/documents")
    public String Documents(@RequestParam("candidatId") Integer candidatId, Model model){
        if(appUserService.areUserDocsChecked(candidatId)){
            model.addAttribute("checked", true);
            return "candidati";
        }
        DocCheckForm docs = new DocCheckForm();
        docs.setDocs(appUserService.getUserDocs(candidatId));

        model.addAttribute("errorMsg", true);
        model.addAttribute("docsForm", docs);
        return "documente-candidati";
    }

    @GetMapping("/view-pdf")
    public ResponseEntity<InputStreamResource> viewPdf(@RequestParam("link") String link, @RequestParam("tip") String tip, Model model) {
        try {
            String downloadPath = minIOService.ReadFromMinIO(link, tip);
            if(downloadPath == null){
                return null;
                //TODO add return to error page
            }


            // Load the file as an input stream
            InputStream file = new FileInputStream(downloadPath);
            //InputStream file = new FileInputStream("C:\\Users\\costan.raluca\\Desktop\\Ali2\\springboot-admitere\\src\\main\\resources\\lab8.pdf");

            // Set the content type and attachment header.
            HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Disposition", "inline; filename="+link);
            //headers.add("Content-Disposition", "inline; filename=lab8.pdf");

            headers.add("Content-Type", "application/pdf");

            ResponseEntity<InputStreamResource> response = new ResponseEntity<>(
                    new InputStreamResource(file), headers, HttpStatus.OK);
            return response;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/check-docs")
    public String checkDocs(@ModelAttribute DocCheckForm docsForm, Model model){
        for(DocumenteCandidat doc : docsForm.getDocs()){
            userDocs.updateValidity(doc.id, doc.valid);
        }

        model.addAttribute("validareCompleta", true);
        return "candidati";
    }

}
