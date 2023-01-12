package com.code.service;

import com.code.model.DocumenteEntity;
import com.code.repository.DocumenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumenteService
{
    @Autowired
    DocumenteRepository appDocumenteRepository;
    public void inregistrareDoc(DocumenteEntity doc){
        appDocumenteRepository.save(doc);
    }
    public void valideazaDoc(String doc_name){
        DocumenteEntity a=appDocumenteRepository.findByLink(doc_name);
        a.setValid(1);
        appDocumenteRepository.save(a);
    }

    public void respingeDoc(String doc_name){
        DocumenteEntity a= appDocumenteRepository.findByLink(doc_name);
        a.setValid(0);
        appDocumenteRepository.save(a);
    }

}
