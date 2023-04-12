package ru.skillbox.diplom.group35.library.core.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import ru.skillbox.diplom.group35.library.core.utils.SecurityUtil;

@Configuration
@RequiredArgsConstructor
public class FeignClientConfig {

    private final SecurityUtil securityUtil;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", "Bearer " + securityUtil.getJwtToken());
        };
    }

    @Bean
    public WebMvcRegistrations feignWebRegistrations() {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return new FeignRequestMappingHandlerMapping();
            }
        };
    }

    /**
     *  Чтобы Swagger игнорировал api если с @FeignClient используется @RequestMapping
     */
    private static class FeignRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
        @Override
        protected boolean isHandler(Class<?> beanType) {
            return super.isHandler(beanType) &&
                    !AnnotatedElementUtils.hasAnnotation(beanType, FeignClient.class);
        }
    }
}