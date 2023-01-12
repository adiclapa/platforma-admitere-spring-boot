package com.code.service;

import com.code.model.SpecializariEntity;
import com.code.repository.SpecializariRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializariService {
    @Autowired
    SpecializariRepository appSpecializariRepository;
    public List<SpecializariEntity> getSpec(){
        List<SpecializariEntity> spec= appSpecializariRepository.findAll();
        return spec;
    }

}

