package com.tambapps.website.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class ApplicationConfiguration implements WebMvcConfigurer {

    private static final long MAX_AGE_SECS = 3600;

    @Value("${file.storage.directory}")
    private String fileStorageDirectory;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }


    @Bean
    public Path fileStorageRoot() {
        Path path = Paths.get(fileStorageDirectory);
        File file = path.toFile();
        if (file.exists()) {
            if (!file.isDirectory()) {
                throw new RuntimeException("The file storage root must be a directory");
            }
        } else {
            if (!file.mkdir()) {
                throw new RuntimeException("Couldn't create file storage directory");
            }
        }
        return path;
    }
}