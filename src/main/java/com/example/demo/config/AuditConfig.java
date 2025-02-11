package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class AuditConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String currentUser = "";
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            currentUser = "System";
        }else {
            currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        }
        return Optional.ofNullable(currentUser);
    }
}
