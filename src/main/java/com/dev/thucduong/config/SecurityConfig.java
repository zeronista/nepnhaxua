package com.dev.thucduong.config;

import com.dev.thucduong.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter)
            throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Allow test endpoints
                        .requestMatchers("/api/test/**").permitAll()
                        // Allow original endpoints
                        .requestMatchers("/api/users/register", "/api/users/login", "/api/products/**", "/api/blogs/**",
                                "/api/reviews/**")
                        .permitAll()
                        .requestMatchers("/api/orders/**", "/api/chat-messages/**").hasRole("CUSTOMER")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Temporary UserDetailsService while fixing Lombok issues
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // For demo purposes, create a test user
            // In production, this should query the database
            if ("test@example.com".equals(username)) {
                return User.builder()
                        .username("test@example.com")
                        .password(passwordEncoder().encode("password"))
                        .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMER")))
                        .build();
            }
            throw new UsernameNotFoundException("User not found: " + username);
        };
    }
}