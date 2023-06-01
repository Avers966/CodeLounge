package ru.skillbox.diplom.group35.library.core.security.config;

import com.nimbusds.jose.JWSAlgorithm;
import java.nio.charset.StandardCharsets;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http

        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/api/v1/auth/**", "/v3/api-docs/**",
            "/api/v1/**/api-docs/**", "/swagger-ui/**", "/api/v1/**/swagger-ui/**").permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .logout(
            logout -> {
              logout
                  .logoutUrl("/api/v1/auth/logout")
                  .logoutSuccessUrl("/")
                  .logoutSuccessHandler(getLogoutSuccessHandler())
                  .invalidateHttpSession(true);
            }
        )
        .oauth2ResourceServer().bearerTokenResolver(this::tokenExtractor).jwt()
        .decoder(jwtDecoder())
        .jwtAuthenticationConverter(jwtAuthenticationConverter());
    return http.build();
  }

  @Bean
  protected LogoutSuccessHandler getLogoutSuccessHandler() {
    return (httpServletRequest, httpServletResponse, authentication) -> {
      httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    };
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withSecretKey(
        new SecretKeySpec("SecretSpecialKeyOauth2.0Jwt256Bites".getBytes(StandardCharsets.UTF_8),
            JWSAlgorithm.RS512.getName())).build();
  }

  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
    return jwtAuthenticationConverter;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  public String tokenExtractor(HttpServletRequest request) {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    String requestUrl = request.getRequestURI();
    if (header != null) {
      return header.replace("Bearer ", "");
    }
    if (requestUrl.contains("/api/v1/streaming/ws")) {
      for (Cookie cookie : request.getCookies()) {
        if (cookie.getName().contains("jwt")) {
          return cookie.getValue();
        }
      }
    }
    return null;
  }
}
