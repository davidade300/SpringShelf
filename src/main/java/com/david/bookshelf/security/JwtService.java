package com.david.bookshelf.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    private JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(Authentication authentication) {
        String name = authentication.getName();
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(3600);
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("bookshelf")
                .subject(name)
                .issuedAt(now)
                .expiresAt(expiration)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


}
