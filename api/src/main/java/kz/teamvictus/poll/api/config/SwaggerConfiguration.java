package kz.teamvictus.poll.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

   @Bean
   public Docket productApi() {
      return new Docket(DocumentationType.SWAGGER_2)
              .select()
              .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
              .build()
              .apiInfo(metaInfo());
   }

   private ApiInfo metaInfo() {

      ApiInfo apiInfo = new ApiInfo(
              "Ticket API",
              "Ticket Swagger Example API for Youtube",
              "1.0",
              "Terms of Service",
              new Contact("AJA-x", "https://vk.com/im?peers=c6&sel=c16",
      "azamat.kz946@gmail.com"),
              "Apache License Version 2.0",
              "https://www.apache.org/licesen.html"
      );

      return apiInfo;
   }
}
