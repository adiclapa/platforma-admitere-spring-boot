package com.code.service;

import com.code.model.DocumenteEntity;
import com.code.model.SesiuniAdmitereEntity;
import com.code.model.UsersEntity;
import com.code.payload.requests.LoginRequest;
import com.code.payload.requests.RegisterRequest;
import com.code.payload.requests.RegisterRequestAdmin;
import com.code.payload.responses.DocumenteCandidat;
import com.code.payload.responses.LoginResponse;
import com.code.payload.responses.UserDTO;
import com.code.repository.DocumenteRepository;
import com.code.repository.SessionRepository;
import com.code.repository.UserRepository;
import com.code.repository.UserRolesRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    @Autowired
    UserRepository appUserRepository;

    @Autowired
    UserRolesRepository userRolesRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    SesiuneAdmitereService sesiuneAdmitereService;

    @Autowired
    DocumenteRepository documenteRepository;

    @Autowired
    TokenService tokenService;
    @Autowired
    EmailSenderService emailSenderService;


    public UsersEntity getUserByEmail(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public LoginResponse signInUser(@NotNull LoginRequest request){
        Optional<UsersEntity> auth = appUserRepository.findDistinctByPasswordAndEmail(request.getPassword(), request.getEmail());
        if(auth.isEmpty())
            return null;

        String accessToken = tokenService.generateToken(auth.get());
        return new LoginResponse(auth.get().getEmail(), accessToken);
    }


    public String signUpUser(RegisterRequest registerRequest, Integer rol) {
        boolean userExists = appUserRepository
                .findByEmail(registerRequest.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            return "";
        }

        UsersEntity newUser = new UsersEntity();
        newUser.setPrenume(registerRequest.getPrenume());
        newUser.setNume(registerRequest.getNume());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword("parola");
        newUser.setIdRol(rol);
        appUserRepository.save(newUser);

        emailSenderService.sendSimpleEmail(newUser.getEmail(), "Cont utilizator nou", newUser.getPassword());

        return tokenService.generateToken(newUser);
    }

    public String signUpUser(RegisterRequestAdmin registerRequest) {
        boolean userExists = appUserRepository
                .findByEmail(registerRequest.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            return "";
        }


        UsersEntity newUser = new UsersEntity();
        newUser.setPrenume(registerRequest.getPrenume());
        newUser.setNume(registerRequest.getNume());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword("parola");
        newUser.setIdRol(registerRequest.getRol());
        appUserRepository.save(newUser);

        emailSenderService.sendSimpleEmail(newUser.getEmail(), "Cont utilizator nou", newUser.getPassword());

        return tokenService.generateToken(newUser);
    }

    public Boolean areUserDocsChecked(Integer candidatId){
        return documenteRepository.findAllByIdCandidatAndValid(candidatId, -1).isEmpty();
    }
    public Boolean areUserDocsValid(Integer candidatId){
        if(documenteRepository.findAllByIdCandidatAndValid(candidatId, 0).size() > 0)
            return false;
        return true;
    }

    public List<DocumenteCandidat> getUserDocs(Integer candidatId){
        List<DocumenteEntity> docs =  documenteRepository.findAllByIdCandidat(candidatId);
        List<DocumenteCandidat> docsDTO = new ArrayList<DocumenteCandidat>();
        if(docs.isEmpty())
            return docsDTO;
        int count = 0;
        for(DocumenteEntity doc : docs){
            DocumenteCandidat newDoc = new DocumenteCandidat(candidatId, doc.getIndexDoc(), doc.getTip(), doc.getLink(), doc.getValid(), doc.getData());
            if(newDoc.data.after(sessionRepository.getLastSession().getStartDate()) &&
                    newDoc.data.before(sessionRepository.getLastSession().getEndDate())){
                newDoc.setCount(++count);
                docsDTO.add(newDoc);
            }
        }
        return docsDTO;
    }

    public List<UserDTO> getAllUsers(){
        return appUserRepository.getAllUsers().stream().map(item -> {
            if (item != null)
                return new UserDTO(item.getIdUser(), item.getNume(), item.getPrenume(), item.getEmail());
            return new UserDTO();
        }).toList();
    }

    public List<UserDTO> getUsersFromCurrentSession(){
        List<UserDTO> usersEntities = new ArrayList<>();
        SesiuniAdmitereEntity currentSession = sessionRepository.getLastSession();
        if(!currentSession.getStartDate().before(Date.from(Instant.now())) &&
                !currentSession.getEndDate().after(Date.from(Instant.now())))
            return usersEntities;
        List<DocumenteEntity> currentUserDocs = documenteRepository.findAllByDataBetween(currentSession.getStartDate(), currentSession.getEndDate());

        int i = 0;
        for(DocumenteEntity doc : currentUserDocs){
            Optional<UsersEntity> user = appUserRepository.findByIdUser(doc.getIdUser());
            if(user.isEmpty())
                continue;
            UserDTO found = new UserDTO(user.get().getIdUser(), user.get().getNume(), user.get().getPrenume(), user.get().getEmail());
            if(!usersEntities.contains(found)){
                if(doc.getValid().equals(-1) ){
                    found.setDocVerificate("neverificat");
                }
                else
                    found.setDocVerificate("verificat");
                found.setCount(++i);
                usersEntities.add(found);
            }

        }

        return usersEntities;
    }
    public int getUserId(String username){
        return (appUserRepository.findByEmail(username).map(UsersEntity::getIdUser).orElse(-1));
    }
}