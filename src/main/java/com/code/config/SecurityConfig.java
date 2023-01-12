package com.code.config;

import com.code.security.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
class SpringSecurityConfiguration {
    private final JwtTokenFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/**")
                .permitAll()
                .requestMatchers("/login-page")
                .permitAll()
                .requestMatchers("/login")
                .permitAll()
                .requestMatchers("/logout")
                .permitAll()
                .requestMatchers("/")
                .permitAll()
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/secretar/**").hasAuthority("SECRETAR")
                .requestMatchers("/candidat/**").hasAuthority("CANDIDAT")
                .requestMatchers("/centru/**").hasAuthority("CENTRU")
                .anyRequest()
                .authenticated()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/").deleteCookies("accessToken", "JSESSIONID").clearAuthentication(true).invalidateHttpSession(true)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}