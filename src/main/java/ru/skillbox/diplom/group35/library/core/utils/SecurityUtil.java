package ru.skillbox.diplom.group35.library.core.utils;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class SecurityUtil {

    public AccountDetails getAccountDetails(){
        JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(UUID.fromString(token.getToken().getClaim("id").toString()));
        accountDetails.setEmail(token.getToken().getClaim("email"));
        return accountDetails;
    }
}
