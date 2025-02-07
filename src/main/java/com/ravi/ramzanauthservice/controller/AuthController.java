package com.ravi.ramzanauthservice.controller;

import com.ravi.ramzanauthservice.dto.mapper.UserDtoMapper;
import com.ravi.ramzanauthservice.modal.*;
import com.ravi.ramzanauthservice.repositories.UserJpaRepository;
import com.ravi.ramzanauthservice.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserDtoMapper userDtoMapper;

    @Value("${cert.jwks}")
    private Resource jwksResource;

    @GetMapping("/")
    List<User> findAllUsers() {
        return userJpaRepository.findAll();
    }

    @GetMapping("/.well-known/jwks.json")
    public ResponseEntity<Resource> getJwks() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jwksResource);
    }

    @PostMapping("/token")
    ResponseEntity<?> token(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).build();
    }

    /// Give User details and JWT token after every time login is called
    @PostMapping("/login")
    ResponseEntity<?> login(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        User user = userJpaRepository.findByEmail(authentication.getName());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(userDtoMapper.apply(user, token));
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody User newUser) {
        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        User registeredUser = userJpaRepository.save(newUser);
        String token = tokenService.generateToken(registeredUser.getEmail(), RoleType.ROLE_USER.name());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(userDtoMapper.apply(registeredUser, token));
    }
}
