package com.daitangroup.initproj.repository.mongo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories
public class MongoConfiguration {

	@Value("${db.host:localhost}")
	private String host;
	
	@Bean
	public MongoClient mongoClient() {
		return new MongoClient(host);
	}
}