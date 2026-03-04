package com.david.bookshelf.services;

import com.david.bookshelf.dtos.auth.LoginRequest;
import com.david.bookshelf.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public String authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.userName(), request.password())
        );
        return jwtService.generateToken(authentication);
    }
}
