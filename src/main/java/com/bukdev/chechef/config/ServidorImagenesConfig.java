package com.bukdev.chechef.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ServidorImagenesConfig implements WebMvcConfigurer{
    
    @Value("${app.imagenes.directorio}")
    private String directorio;
    
    @Value("${app.imagenes.url}")
    private String url;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(url)
                .addResourceLocations(directorio);
    }
    
}
