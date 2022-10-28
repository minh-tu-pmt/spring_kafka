package com.actech.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class ApplicationProperties {
    
    @Value("${app.mongo.uri}")
    private String mongoUri;

    @Value("${app.mongo.main-db}")
    private String mainDb;

}
