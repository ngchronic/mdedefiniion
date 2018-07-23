package gov.cdc.nccdphp.esurveillance.rest;

import gov.cdc.nccdphp.esurveillance.mdeDefinition.About;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private About about;

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("gov.cdc.nccdphp.esurveillance"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                applicationName,
                about.getSummary(),
                about.getVersions().get(0),
                "",
                new Contact("Marcelo Caldas", "https://www.cdc.gov", "mcq1@cdc.gov"),
                "Centers for Disease Control and Prevention",
                "https://www.cdc.gov",
                new ArrayList<>());
        return apiInfo;
    }
}

