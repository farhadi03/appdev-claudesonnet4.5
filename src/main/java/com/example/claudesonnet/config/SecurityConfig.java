package com.example.claudesonnet.config;

import com.example.claudesonnet.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final CustomUserDetailsService userDetailsService;
    
    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity with Basic Auth
            .authorizeHttpRequests(auth -> auth
                // Allow H2 console access (for development)
                .requestMatchers("/h2-console/**").permitAll()
                
                // GET requests - accessible by all authenticated users (USER and ADMIN)
                .requestMatchers(HttpMethod.GET, "/api/planets/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/moons/**").hasAnyRole("USER", "ADMIN")
                
                // POST, PUT, DELETE requests - only accessible by ADMIN
                .requestMatchers(HttpMethod.POST, "/api/planets/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/planets/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/planets/**").hasRole("ADMIN")
                
                .requestMatchers(HttpMethod.POST, "/api/moons/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/moons/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/moons/**").hasRole("ADMIN")
                
                // All other requests require authentication
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> {}) // Enable HTTP Basic Authentication
            .headers(headers -> headers.frameOptions(frame -> frame.disable())); // Allow H2 console frames
        
        return http.build();
    }
}

