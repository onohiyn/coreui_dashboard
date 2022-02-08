package com.systemcheck.config;



import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableAutoConfiguration(exclude={MongoReactiveAutoConfiguration.class, MongoReactiveDataAutoConfiguration.class})
@EnableMongoRepositories(basePackages="com.systemcheck.repository")
public class MongoConfig {

}
