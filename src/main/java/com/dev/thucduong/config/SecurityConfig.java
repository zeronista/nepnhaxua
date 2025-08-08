package com.dev.thucduong.config;

import com.dev.thucduong.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter)
            throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Allow CORS preflight requests
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Allow actuator health/info for container orchestration
                        .requestMatchers("/actuator/health", "/actuator/info").permitAll()
                        // Allow root and static assets for local dev
                        .requestMatchers("/", 
                                "/home", 
                                "/index.html", 
                                "/error", 
                                "/favicon.ico", 
                                "/assets/**", 
                                "/static/**", 
                                "/public/**", 
                                "/webjars/**",
                                "/**/*.css", 
                                "/**/*.js", 
                                "/**/*.map", 
                                "/**/*.png", 
                                "/**/*.jpg", 
                                "/**/*.svg", 
                                "/**/*.ico").permitAll()
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
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
                "http://localhost:5173",
                "http://127.0.0.1:5173"
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin"));
        configuration.setExposedHeaders(List.of("Authorization", "Content-Disposition"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
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