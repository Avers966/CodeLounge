package ru.skillbox.diplom.group35.library.core.annotation;

/**
 * EnableBaseRepository
 *
 * @author Denis_Kholmogorov
 */

import net.bytebuddy.implementation.attribute.AnnotationRetention;
import org.jboss.jandex.AnnotationTarget;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.skillbox.diplom.group35.library.core.repository.BaseRepositoryImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ru.skillbox.diplom.group35.library.core.util.Utils.BASE_PACKAGE;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableJpaRepositories(
        repositoryBaseClass = BaseRepositoryImpl.class,
        basePackages = BASE_PACKAGE
)
@EntityScan(basePackages = BASE_PACKAGE)
public @interface EnableBaseRepository {
}
