package com.daitangroup.initproj.repository.mongo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

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
	
	@Bean
	public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
		Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
		factory.setResources(new Resource[] {new ClassPathResource("data.json")});
		return factory;
	}
}