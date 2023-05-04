package ru.skillbox.diplom.group35.library.core.utils;


import org.springframework.security.core.Authentication;
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
        Authentication currentAuthentication = getAuthentication();
        return currentAuthentication instanceof JwtAuthenticationToken ?
                ((JwtAuthenticationToken) currentAuthentication).getToken(): null;
    }

    public String getJwtTokenValue() {
        return getJwtToken() != null ? getJwtToken().getTokenValue() : null;
    }

    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
