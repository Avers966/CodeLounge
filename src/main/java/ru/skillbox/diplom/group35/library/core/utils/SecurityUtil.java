package ru.skillbox.diplom.group35.library.core.utils;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;


public class SecurityUtil {

    public AccountDetails getAccountDetails(){
        JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setId(token.getToken().getClaim("id").toString());
        accountDetails.setEmail(token.getToken().getClaim("email"));
        return accountDetails;
    }
}
