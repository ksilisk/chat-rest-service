package com.ksilisk.chatservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class TokenProviderImpl implements TokenProvider {
    private static final String ISSUER = "KsiliskRestService";
    private static final JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();

    private final JwtConfig jwtConfig;
    private final JwtEncoder jwtEncoder;

    @Override
    public String create(String subject, Long userId) {
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuer(ISSUER)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusMillis(jwtConfig.getExpirationTimeSeconds()))
                .subject(subject)
                .claim("userId", userId)
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaimsSet)).getTokenValue();
    }
}
