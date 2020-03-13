package com.devpoc.multiple.db.config;

import static springfox.documentation.builders.PathSelectors.any;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AppConfig {
	
	@Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("Hospital Info service").select()
                .apis(RequestHandlerSelectors.basePackage("com.devpoc.multiple.db"))
                .paths(any()).build().apiInfo(new ApiInfo("Hospital Info service",
                        "A set of services to get Hospital Info service from mutiple dbs", "1.0.0", null,
                        new Contact("sachin", "https://", null),null, null));
    }

}
