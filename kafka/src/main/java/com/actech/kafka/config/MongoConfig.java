package com.actech.kafka.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class MongoConfig extends AbstractMongoClientConfiguration {

    private final ApplicationProperties properties; 

    @Override
    protected String getDatabaseName() {
        return properties.getMainDb();
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(properties.getMongoUri());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString).build();
        return MongoClients.create(mongoClientSettings);
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
       return Collections.singleton("com.actech.kafka");
    }
    
}
