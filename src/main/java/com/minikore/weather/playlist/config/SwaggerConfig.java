package com.minikore.weather.playlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;
import java.util.regex.Pattern;


@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        Predicate<String> notActuator = Pattern.compile("^/actuator.*").asPredicate().negate();

        ApiSelectorBuilder apiSelectorBuilder = new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any().and(notActuator));

        return apiSelectorBuilder.build();
    }
}