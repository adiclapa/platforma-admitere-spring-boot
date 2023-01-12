package com.code.service;

import com.code.model.FacultatiEntity;
import com.code.repository.FacultatiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultatiService {
    @Autowired
    FacultatiRepository appFacultatiRepository;

    public List<FacultatiEntity> getFac(){
        List<FacultatiEntity> fac=appFacultatiRepository.findAll();
        return fac;
    }
}
