package ru.skillbox.diplom.group35.library.core.utils;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class SecurityUtil {

    public AccountDetails getAccountDetails() {
        Jwt token = getJwtToken();
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(UUID.fromString(token.getClaim("id").toString()));
        accountDetails.setEmail(token.getClaim("email"));
        return accountDetails;
    }

    private Jwt getJwtToken() {
        return  ((JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getToken();
    }

    public String getJwtTokenValue() {
        return  getJwtToken().getTokenValue();
    }
}
