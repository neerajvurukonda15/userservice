package com.example.userservice.configurations; // ✅ Ensure this is at the top

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // ✅ Marks this as a configuration class
public class SecurityConfig {  // ✅ Class keyword must be correct

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(16);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // ✅ Correct way for Spring Security 6.1+
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/login","/users/signup").permitAll() // ✅ Allow signup without authentication
                        .anyRequest().authenticated() // 🔐 Protect other endpoints
                );

        return http.build();
    }
}
