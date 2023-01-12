package com.code.security;

import com.code.model.UsersEntity;
import com.code.repository.UserRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;


public class SecurityUser implements UserDetails {

    private UsersEntity user;

    @Autowired
    private UserRolesRepository usersRolesRepository;

    public SecurityUser(UsersEntity user, UserRolesRepository usersRolesRepository) {
        this.user = user;
        this.usersRolesRepository = usersRolesRepository;
    }

    public SecurityUser(UserRolesRepository usersRolesRepository) {
        this.usersRolesRepository = usersRolesRepository;
    }

    public SecurityUser(UsersEntity user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Arrays.stream(usersRolesRepository.findDistinctById(user.getIdRol()).getDenumire()
                        .split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}