package ru.skillbox.diplom.group35.library.core.annotation;

import org.springframework.context.annotation.Import;
import ru.skillbox.diplom.group35.library.core.config.SwaggerConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({SwaggerConfig.class})
public @interface EnableSwagger {
}
