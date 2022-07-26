package com.ibsenc.myclosetapi.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.ibsenc.myclosetapi.controller"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(info());
  }

  private ApiInfo info() {
    return new ApiInfo(
        "MyCloset API",
        "A Spring Boot server with an API for closet management. Users can create, view, and store outfit and article resources.",
        "1.0.0",
        null,
        new Contact("Camille Ibsen", "https://github.com/ibsenc", "ibsen.camille@gmail.com"),
        null, null, Collections.emptyList());
  }
}