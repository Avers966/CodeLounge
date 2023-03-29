package ru.skillbox.diplom.group35.library.core.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.skillbox.diplom.group35.library.core.exception.BaseExceptionHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ru.skillbox.diplom.group35.library.core.utils.Utils.BASE_PACKAGE;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({BaseExceptionHandler.class})
public @interface EnableExceptionHandler {
}