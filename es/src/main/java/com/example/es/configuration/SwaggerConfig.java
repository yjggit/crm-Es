package com.example.es.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author jianhui.Yang
 * @version $Id SwaggerConfig.java, v 0.1 2018-11-07 16:41 jianhui.Yang Exp $$
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket esApi () {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.es.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo () {
        return new ApiInfoBuilder()
                .title("工具包项目")
                .description("ES查询DEMO/邮件通知……等功能")
                .contact("jianhui.Yang")
                .version("1.0")
                .build();
    }


}
