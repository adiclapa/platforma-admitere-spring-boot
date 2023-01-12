package com.code.repository;

import com.code.model.CandidatiAdmitereEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatiAdmitereRepository extends JpaRepository<CandidatiAdmitereEntity, Integer> {
    Integer countAllByIdAdmitere(Integer sessionId);
}
