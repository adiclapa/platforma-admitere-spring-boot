package com.code.repository;

import com.code.model.DocumenteEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DocumenteRepository
        extends JpaRepository<DocumenteEntity, Integer> {

    List<DocumenteEntity> findAllByDataBetween(Date start, Date end);
    List<DocumenteEntity> findAllByIdCandidat(int idCandidat);

    List<DocumenteEntity> findAllByIdCandidatAndValid(int idCandidat, int valid);
    List<DocumenteEntity> countAllByIdCandidatAndValid(int idCandidat, int valid);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update DocumenteEntity doc set doc.valid =:isValid where doc.indexDoc =:entryId")
    void updateValidity(@Param("entryId") Integer EntryId, @Param("isValid") Integer isValid);

    DocumenteEntity findByLink(String link);
}
