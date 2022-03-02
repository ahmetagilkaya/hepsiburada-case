package tr.com.hepsiburada.rest_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("tr.com.hepsiburada"))
                .paths(PathSelectors.regex("/api.*"))
                .build()
                .apiInfo(apiEndPointsInfo());

    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .docExpansion(DocExpansion.LIST)
                .build();
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
                .title("Hepsiburada Assignment")
                .description("Hepsiburada Assignment Api Dokümantasyonu")
                .contact(new Contact("Ahmet Ağılkaya", "0506 143 50 87", "ahmetakaya92@gmail.com"))
                .license("Ahmet Ağılkaya")
                .licenseUrl("https://www.linkedin.com/in/ahmetagilkaya/")
                .version("1.0.0")
                .build();
    }

}
