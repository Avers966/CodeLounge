package ru.skillbox.diplom.group35.library.core.annotation;


import org.springframework.context.annotation.Import;
import ru.skillbox.diplom.group35.library.core.security.jwt.JwtTokenProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Import(JwtTokenProvider.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JwtProvider {
}
