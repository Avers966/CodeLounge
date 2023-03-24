package ru.skillbox.diplom.group35.library.core.security.jwt;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithm;
import org.springframework.security.oauth2.jwt.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.ZonedDateTime;
import java.util.UUID;

public class JwtTokenProvider {


    public JwtEncoder jwtEncoder() {
        SecretKey key = new SecretKeySpec("SecretSpecialKeyOauth2.0Jwt256Bites".getBytes(), "HmacSHA256");
        JWKSource<SecurityContext> immutableSecret = new ImmutableSecret<>(key);
        return new NimbusJwtEncoder(immutableSecret);
    }

    public String createToken(UUID userId, String email) {
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .claim("id", userId)
                .claim("email", email)
                .expiresAt(ZonedDateTime.now().plusHours(3).toInstant())
                .build();
        JwsAlgorithm jwsAlgorithm = JWSAlgorithm.HS256::getName;

        return jwtEncoder()
                .encode(JwtEncoderParameters.from(JwsHeader.with(jwsAlgorithm).build(), jwtClaimsSet))
                .getTokenValue();

    }
}
