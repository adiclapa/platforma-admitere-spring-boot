package com.code.repository;

import com.code.model.UsersEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<UsersEntity, Integer> {

    Optional<UsersEntity> findByEmail(String email);
    Optional<UsersEntity> findDistinctByPasswordAndEmail(String password, String username);

    Optional<UsersEntity> findByIdUser(Integer id);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query( value = "SELECT * FROM Users u",
            nativeQuery = true)
    List<UsersEntity> getAllUsers();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query( value = "SELECT * FROM CandidatiAdmitere ca WHERE ca.IdAdmitere=?1",
            nativeQuery = true)
    List<UsersEntity> getAllUsersFromSession(Integer sessionId);

    // int enableAppUser(String email);

}