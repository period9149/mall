package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration

public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override

    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String path = "/Users/period9149/Desktop/Projects/mall/uploads/";

        registry.addResourceHandler("/**").addResourceLocations("file:" + path);

        super.addResourceHandlers(registry);

    }

}