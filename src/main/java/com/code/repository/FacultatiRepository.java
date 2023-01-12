package com.code.repository;

import com.code.model.FacultatiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultatiRepository
            extends JpaRepository<FacultatiEntity,Integer> {

    List<FacultatiEntity> findAll();
}
