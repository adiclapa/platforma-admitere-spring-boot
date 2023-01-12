package com.code.repository;

import com.code.model.OptiuniCandidatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface OptiuniCandidatRepository
        extends JpaRepository<OptiuniCandidatEntity, Integer>{
//    List<OptiuniCandidatEntity> saveAll(ArrayList<OptiuniCandidatEntity> Oc );
}
