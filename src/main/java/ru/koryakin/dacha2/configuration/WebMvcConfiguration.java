package ru.koryakin.dacha2.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/gallery-images/**")
                .addResourceLocations("file:files/images/");
        registry
                .addResourceHandler("/pdf/**")
                .addResourceLocations("file:files/pdf/");
        registry
                .addResourceHandler("/blog-images/**")
                .addResourceLocations("file:files/images/");
    }
}