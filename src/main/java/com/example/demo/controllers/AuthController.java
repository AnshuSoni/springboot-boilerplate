package com.example.demo.controllers;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.Response;
import com.example.demo.dto.SignupRequest;
import com.example.demo.models.Users;
import com.example.demo.services.JwtService;
import com.example.demo.services.UsersManagementService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    private UsersManagementService usersManagementService;

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleDeserializationError(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("Malformed JSON or type mismatch: " + ex.getMessage());
    }

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

    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Users>> signup(@RequestBody SignupRequest signupRequest) {

        log.info(signupRequest.toString());

        Users createdUser =  usersManagementService.createUsersFromSignup(signupRequest);
        Response<Users> response = new Response<>();
        response.setBody(createdUser);
        response.setMessage(createdUser.getUsername() + " is created");
        response.setStatusCode(HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
