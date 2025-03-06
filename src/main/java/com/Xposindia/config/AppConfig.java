package com.Xposindia.config;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@Configuration
@ComponentScan(basePackages = "com.cybertron.*")
@PropertySources({
        @PropertySource("classpath:project.properties"),
        @PropertySource("classpath:responseCode.properties")
})
public class AppConfig {

    @Bean
    public ApplicationContextProvider applicationContextProvider() {
        return new ApplicationContextProvider();
    }  
    
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }  
    
    
}
