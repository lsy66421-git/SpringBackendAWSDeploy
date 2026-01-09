package com.example.kiosk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map /uploads/** URL to local file system C:/MyKioskProject/uploads/
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///C:/MyKioskProject/uploads/");
    }
}
