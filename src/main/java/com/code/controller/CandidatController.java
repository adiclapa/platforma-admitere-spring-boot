package com.code.controller;

import com.code.model.DocumenteEntity;
import com.code.model.FacultatiEntity;
import com.code.model.SpecializariEntity;
import com.code.payload.requests.OptiuniARequest;
import com.code.payload.requests.OptiuniRequest;
import com.code.payload.requests.SpecializariRequest;
import com.code.repository.UserRepository;
import com.code.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/candidat")
public class CandidatController {
    public static String UPLOAD_DIRECTORYCI = "..\\..\\..\\..\\resources\\pdfs\\buletine";
    public static String UPLOAD_DIRECTORYDIP = "..\\..\\..\\..\\resources\\pdfs\\diplome";
    public static String UPLOAD_DIRECTORYP = "..\\..\\..\\..\\resources\\pdfs\\certificate";
    @Autowired
    UserService appUserService;
    @Autowired
    SpecializariService appSpecializariService;
    @Autowired
    FacultatiService appFacultatiService;
    @Autowired
    OptiuniCandidatService appOptiuniCandidatService;
    @Autowired
    DocumenteService appDocumenteService;
    @Autowired
    TokenService appTokenService;
    @Autowired
    MinIOService appMinIOService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    String main_candidat_page(){ return "main_candidat";}

    @GetMapping("/candidat-main-error")
    public String candidatMainError(Model model){
        model.addAttribute("candidatMainError", true);
        return "main_candidat";
    }

    @GetMapping("/specializari")
    public String specializari_page(Model model) {
        List<SpecializariRequest> spec_final = new ArrayList<SpecializariRequest>();
        List<FacultatiEntity> fac = appFacultatiService.getFac();
        List<SpecializariEntity> spec = appSpecializariService.getSpec();

        OptiuniARequest list=new OptiuniARequest();
        spec.forEach((n) -> {
            SpecializariRequest s = new SpecializariRequest(n.getNume(), n.getLocuri(), fac.get(n.getIdFacultate() - 1).getDenumire(),n.getId());
            spec_final.add(s);
            list.addOptiuni(new OptiuniRequest(n.getId(),n.getNume()));
        });


        model.addAttribute("specializari", spec_final);
        model.addAttribute("form", list);
        return "specializari";
    }

    @GetMapping("/documente")
    public String displayUploadForm(HttpServletRequest req, Model model) {
        String token = appTokenService.getTokenFromRequest(req);
        String email = appTokenService.getEmail(token);
        Integer IdCandidat = userRepository.findByEmail(email).get().getIdUser();

        if(appUserService.areUserDocsChecked(IdCandidat)){
            model.addAttribute("docsChecked", true);
        } else {
            if(appUserService.areUserDocsValid(IdCandidat))
                model.addAttribute("docsInvalid", true);
            model.addAttribute("docsChecked", false);
        }
        
        return "upload-documente";
    }

    @RequestMapping(value = "/adaugare", method = RequestMethod.POST)
    public String adaugare(HttpServletRequest req, @ModelAttribute OptiuniARequest form) {
        String email = appTokenService.getEmail(appTokenService.getTokenFromRequest(req));
        appOptiuniCandidatService.optiuni(email, form.getOptiuni());

        return "main_candidat";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadImage(HttpServletRequest req,
                              Model model,
                                    @RequestParam("buletin") MultipartFile buletin,
                                    @RequestParam("diploma1") MultipartFile diploma1,
                                    @RequestParam("poza") MultipartFile poza
                              ) throws IOException,
            NoSuchAlgorithmException, InvalidKeyException {
        String token = appTokenService.getTokenFromRequest(req);

        if(token.isEmpty()){
            return null;
        }


        String nume_utilizator=appTokenService.getEmail(token);
        int Id_user= appUserService.getUserId(nume_utilizator);

        String nume_fisier="[Buletin]["+nume_utilizator+"].pdf";
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORYCI, nume_fisier);
        fileNames.append(buletin.getOriginalFilename());
        Files.write(fileNameAndPath, buletin.getBytes());
        DocumenteEntity doc1=new DocumenteEntity();
        doc1.setIdUser(Id_user);
        doc1.setLink(nume_fisier);
        doc1.setTip("buletin");
        doc1.setValid(-1);
        doc1.setData(Date.valueOf(LocalDate.now()));
        doc1.setIdCandidat(Id_user);
        appDocumenteService.inregistrareDoc(doc1);
        appMinIOService.WriteToMinIO(nume_fisier, "buletin");

        nume_fisier="[Diploma]["+nume_utilizator+"].pdf";
        Path fileNameAndPath2 = Paths.get(UPLOAD_DIRECTORYDIP, nume_fisier);
        fileNames.append(diploma1.getOriginalFilename());
        Files.write(fileNameAndPath2, diploma1.getBytes());
        DocumenteEntity doc2=new DocumenteEntity();
        doc2.setIdUser(Id_user);
        doc2.setLink(nume_fisier);
        doc2.setTip("diploma");
        doc2.setValid(-1);
        doc2.setData(Date.valueOf(LocalDate.now()));
        doc2.setIdCandidat(Id_user);
        appDocumenteService.inregistrareDoc(doc2);
        appMinIOService.WriteToMinIO(nume_fisier, "diploma");

        nume_fisier="[Certificat]["+nume_utilizator+"].pdf";
        Path fileNameAndPath4 = Paths.get(UPLOAD_DIRECTORYP, nume_fisier);
        fileNames.append(poza.getOriginalFilename());
        Files.write(fileNameAndPath4, poza.getBytes());
        DocumenteEntity doc3=new DocumenteEntity();
        doc3.setIdUser(Id_user);
        doc3.setLink(nume_fisier);
        doc3.setTip("certificat");
        doc3.setValid(-1);
        doc3.setData(Date.valueOf(LocalDate.now()));
        doc3.setIdCandidat(Id_user);
        appDocumenteService.inregistrareDoc(doc3);
        appMinIOService.WriteToMinIO(nume_fisier, "certificat");

        model.addAttribute("uploadResult", true);
        return "main_candidat";
    }
}
