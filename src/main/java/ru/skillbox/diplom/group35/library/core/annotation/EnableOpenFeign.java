package ru.skillbox.diplom.group35.library.core.annotation;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import ru.skillbox.diplom.group35.library.core.config.FeignClientConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ru.skillbox.diplom.group35.library.core.utils.Utils.BASE_PACKAGE;

/**
 * EnableOpenFeign
 *
 * @author Georgii Vinogradov
 */

@Target(ElementType.TYPE)
@Import(FeignClientConfig.class)
@Retention(RetentionPolicy.RUNTIME)
@EnableFeignClients(basePackages = BASE_PACKAGE)
public @interface EnableOpenFeign {
}
