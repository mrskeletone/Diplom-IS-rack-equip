package com.example.mobileapp.security;

import com.example.mobileapp.DTO.JwtResponse;
import com.example.mobileapp.DTO.LoginRequest;
import com.example.mobileapp.DTO.RefreshTokenRequest;
import com.example.mobileapp.security.entity.User;
import com.example.mobileapp.security.entity.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService; // Для аутентификации


    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtils jwtUtils,
                          UserService userService, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {

            // 1. Проверяем существование пользователя
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

            // 2. Проверяем пароль
            if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            System.out.println(passwordEncoder.matches(request.getPassword(), userDetails.getPassword())+"dsad");
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()));

            UserDetails user = (UserDetails) auth.getPrincipal();
            String accessToken = jwtUtils.generateAccessToken(user);
            System.out.println(accessToken);
            String refreshToken = jwtUtils.generateRefreshToken(user);
            return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        if (jwtUtils.validateToken(request.getRefreshToken())) {
            String username = jwtUtils.getUsernameFromToken(request.getRefreshToken());
            UserDetails user = userDetailsService.loadUserByUsername(username);
            String newAccessToken = jwtUtils.generateAccessToken(user);
            return ResponseEntity.ok(new JwtResponse(newAccessToken, request.getRefreshToken()));
        }
        return ResponseEntity.status(401).build();
    }
}
