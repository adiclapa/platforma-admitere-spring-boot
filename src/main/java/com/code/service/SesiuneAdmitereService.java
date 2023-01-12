package com.code.service;

import com.code.payload.responses.SesiuneAdmitereDTO;
import com.code.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SesiuneAdmitereService {
    @Autowired
    UserRepository appUserRepository;

    @Autowired
    UserRolesRepository userRolesRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    CandidatiAdmitereRepository candidatiAdmitereRepo;

    @Autowired
    DocumenteRepository documenteRepository;

    @Autowired
    TokenService tokenService;

    public List<SesiuneAdmitereDTO> getAllSessions(){
        return sessionRepository.getAllSessions().stream().map(item -> {
            if (item != null)
                return new SesiuneAdmitereDTO(item.getStartDate(), item.getEndDate(), candidatiAdmitereRepo.countAllByIdAdmitere(item.getIdAdmitere()));
            return new SesiuneAdmitereDTO();
        }).toList();
    }
}
