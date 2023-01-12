package com.code.repository;

import com.code.model.SesiuniAdmitereEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<SesiuniAdmitereEntity, Integer> {
    Optional<SesiuniAdmitereEntity> findSesiuniAdmitereEntityByEndDateAfter(Date current);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query( value = "SELECT * FROM SesiuniAdmitere",
            nativeQuery = true)
    List<SesiuniAdmitereEntity> getAllSessions();

    @Transactional
    @Query( value = "SELECT TOP 1* FROM SesiuniAdmitere t ORDER BY t.IdAdmitere DESC",
            nativeQuery = true)
    SesiuniAdmitereEntity getLastSession();


}
