package com.code.service;

import com.code.model.UsersEntity;
import com.code.repository.UserRepository;
import com.code.repository.UserRolesRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
@AllArgsConstructor
public class TokenService{
    private final Logger LOGGER = LogManager.getLogger();

    @Autowired
    UserRolesRepository userRolesRepository;

    JwtEncoder jwtEncoder;

    JwtDecoder jwtDecoder;

    private final UserRepository userRepo;

    // Generates token based on username and password authentication
    public String generateToken(UsersEntity user) {
        Instant now = Instant.now();
        Integer roleId = userRepo.findByEmail(user.getEmail()).get().getIdRol();
        String scope = "ROLE_" + userRolesRepository.findDistinctById(roleId).getDenumire();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(user.getEmail())
                .claim("roles", scope)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public Map<String, Object> parseClaims(String token){
        return jwtDecoder.decode(token).getClaims();
    }

    public String getEmail(String token){
        return jwtDecoder.decode(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        Instant expire = jwtDecoder.decode(token).getExpiresAt();
        return !Instant.now().isBefore(expire);
    }

    public boolean validateAccessToken(String token) {
        try {
            if(this.getEmail(token).isEmpty() || this.getEmail(token).isBlank())
                return false;

            if(isTokenExpired(token))
                return false;
        } catch (Exception ex) {
            LOGGER.error("JWT invalid", ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        if(!validateAccessToken(jwt))
            return false;
        if(!userDetails.getUsername().equals(this.getEmail(jwt)))
            return false;
        return true;
    }

    public String getTokenFromRequest(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");
        String jwt = "";
        final String userEmail;

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            if (request.getCookies() != null) {
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie : cookies) {
                    if ("accessToken".equals(cookie.getName())) {
                        jwt = cookie.getValue();
                        break;
                    }
                }
            }
        } else {
            jwt = authHeader.substring(7);
        }

        return jwt.isEmpty() ? null : jwt;
    }
}
