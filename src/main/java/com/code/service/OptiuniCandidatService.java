package com.code.service;

import com.code.model.OptiuniCandidat;
import com.code.model.OptiuniCandidatEntity;
import com.code.payload.requests.OptiuniRequest;
import com.code.repository.OptiuniCandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OptiuniCandidatService {
    @Autowired
    OptiuniCandidatRepository appOptiuniCandidatRepository;
    @Autowired
    UserService appUserService;
    @Autowired
    TokenService appTokenService;

    public void optiuni(String email, List<OptiuniRequest> opt){
        int Id_user= appUserService.getUserId(email);
        List<OptiuniCandidatEntity> model=new ArrayList<OptiuniCandidatEntity>();

        opt.forEach((n)->{
            OptiuniCandidatEntity o=new OptiuniCandidatEntity();
            o.setIdCandidat(Id_user);
            o.setOrdineOptiuni(n.getOrdine());
            o.setIdSpecializare(n.getSpecializare());
            model.add(o);
        });

       appOptiuniCandidatRepository.saveAll(model);

    }
}
