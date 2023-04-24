package ru.skillbox.diplom.group35.library.core.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import ru.skillbox.diplom.group35.library.core.security.jwt.JwtTokenProvider;

import java.util.List;
import java.util.function.Supplier;

public class TechnicalUserConfig {

    private final JwtTokenProvider jwtTokenProvider;

    private final SecurityConfig securityConfig;

    public TechnicalUserConfig(JwtTokenProvider jwtTokenProvider, SecurityConfig securityConfig) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.securityConfig = securityConfig;
    }

    public <T> T executeByTechnicalUser(Supplier<T> lambda) {
        var currentContext = SecurityContextHolder.getContext();
        try {
            switchContext(createContextFromToken());
            return lambda.get();
        } finally {
            switchContext(currentContext);
        }
    }

    public <T> List<T> executeByTechnicalUserList(Supplier<List<T>> lambda) {
        var currentContext = SecurityContextHolder.getContext();
        try {
            switchContext(createContextFromToken());
            return lambda.get();
        } finally {
            switchContext(currentContext);
        }
    }

    private void switchContext(SecurityContext securityContext) {
        SecurityContextHolder.clearContext();
        SecurityContextHolder.setContext(securityContext);
    }

    private SecurityContext createContextFromToken() {
        var context = SecurityContextHolder.createEmptyContext();
        String systemKey = "SYSTEM";
        context.setAuthentication(
                new JwtAuthenticationToken(securityConfig
                        .jwtDecoder()
                        .decode(jwtTokenProvider.systemToken(systemKey))));
        return context;
    }
}

