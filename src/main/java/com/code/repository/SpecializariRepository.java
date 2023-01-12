package com.code.repository;

import com.code.model.SpecializariEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecializariRepository
        extends JpaRepository<SpecializariEntity, Integer>  {

    List<SpecializariEntity> findAll();
}
