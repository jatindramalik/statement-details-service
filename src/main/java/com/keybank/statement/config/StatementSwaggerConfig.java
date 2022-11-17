/**
 * @Copyright 2022, Key Bank pvt ltd, All rights are reserved. You should not disclose the information outside 
 * otherwise terms and condition will apply
 */
package com.keybank.statement.config;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author jatin, 21-Oct-2022
 *         Description:
 */
public class StatementSwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.keybank.statement.controller"))
                .paths(PathSelectors.regex("/.*")).build().apiInfo(apiInfoMetaData());
    }

    private ApiInfo apiInfoMetaData() {
        return new ApiInfoBuilder().title("StatementDetailsService").description("API Endpoint Decoration")
                .contact(new Contact("Dev-Tean", "https://www.dev-team.com/", "dev-team@gmail.com"))
                .license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.HTML").version("1.0.0")
                .build();
    }

}
