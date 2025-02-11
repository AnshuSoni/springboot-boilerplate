package com.example.demo.controllers;

import com.example.demo.dto.AuthFailResponse;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @GetMapping("user/profile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("admin/profile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("user/login")
    public ResponseEntity<AuthResponse> generateToken(@RequestBody @Valid  AuthRequest request){



        // Filter out strings against xss, sql , command injections etc.
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(request.getUsername().trim(), request.getPassword().trim()));

        AuthResponse response = new AuthResponse();
        if(authentication == null) {
            response.setStatus("Unable to Login, Invalid Username or password");
            response.setToken(null);
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtService.generateToken(request.getUsername());
        response.setToken(jwtToken);
        response.setStatus("OK");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
