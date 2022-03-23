package ru.skillbox.diplom.alpha.library.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import ru.skillbox.diplom.alpha.library.core.dto.Errors;
import ru.skillbox.diplom.alpha.library.core.dto.ErrorsDescription;
import ru.skillbox.diplom.alpha.library.core.dto.LogoutDto;
import ru.skillbox.diplom.alpha.library.core.dto.LogoutRs;

import java.io.PrintWriter;
import java.time.Instant;

/**
 * SecurityConfig
 *
 * @author Sergey Marenin
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .logout(
                        logout -> {
                            logout
                                    .logoutUrl("/api/v1/auth/logout")
                                    .logoutSuccessUrl("/")
                                    .logoutSuccessHandler(getLogoutSuccessHandler())
                                    .invalidateHttpSession(true);
                        }
                );
    }

    @Bean
    protected LogoutSuccessHandler getLogoutSuccessHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            final LogoutRs response = new LogoutRs();
            httpServletResponse.setContentType("application/json");
            PrintWriter out = httpServletResponse.getWriter();
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

            if (authentication != null) {
                response.setTimestamp(Instant.now().getEpochSecond());
                response.setData(new LogoutDto("ok"));
                String json = ow.writeValueAsString(response);
                out.write(json);
                return;
            }
            response.setError(Errors.UNAUTHORIZED);
            response.setErrorDescription(ErrorsDescription.BAD_CREDENTIALS);
            httpServletResponse.setStatus(400);
            String json = ow.writeValueAsString(response);
            out.write(json);
        };
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
