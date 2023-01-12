package com.code.repository;

import com.code.model.UserRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRolesRepository extends JpaRepository<UserRolesEntity, Integer> {
    UserRolesEntity findDistinctById(Integer id_rol);
    Optional<UserRolesEntity> findDistinctByDenumire(String denumire);
}
