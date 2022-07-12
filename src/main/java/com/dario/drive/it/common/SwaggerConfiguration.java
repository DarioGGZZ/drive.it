package com.dario.drive.it.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

@Configuration
@EnableSwagger2
@ConditionalOnProperty(name="app.api.swagger.enable", havingValue = "true", matchIfMissing = false)
public class SwaggerConfiguration {

    @Value("${app.api.basePackage}")
    private String basePackage;
    @Value("${app.api.title}")
    private String title;
    @Value("${app.api.description}")
    private String description;
    @Value("${app.api.version}")
    private String version;
    @Value("${app.api.terms}")
    private String terms;
    @Value("${app.api.name}")
    private String name;
    @Value("${app.api.url}")
    private String url;
    @Value("${app.api.email}")
    private String email;
    @Value("${app.api.license}")
    private String license;
    @Value("${app.api.licenseUrl}")
    private String licenseUrl;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo(){
        return new ApiInfo(
                title,
                description,
                version,
                terms,
                new Contact(name, url, email),
                license,
                licenseUrl,
                Collections.emptyList()
        );
    }
}