package com.code.controller;

import com.code.model.UsersEntity;
import com.code.payload.requests.LoginRequest;
import com.code.repository.UserRepository;
import com.code.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepo;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final Optional<UsersEntity> user = userRepo.findByEmail(request.getEmail());

        if(user != null){
            return ResponseEntity.ok(tokenService.generateToken(user.get()));
        }
        return ResponseEntity.status(400).body("A avut loc o eroare!");
    }

}
