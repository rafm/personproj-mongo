package com.daitangroup.initproj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.daitangroup.initproj.model.Person;
import com.daitangroup.initproj.repository.elasticsearch.PersonElasticsearchRepository;
import com.daitangroup.initproj.repository.mongo.PersonMongoRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired private PersonMongoRepository personMongoRepository;

	@Autowired private PersonElasticsearchRepository personElasticsearchRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		personMongoRepository.deleteAll();
		personElasticsearchRepository.deleteAll();
		
		personElasticsearchRepository.save(
			personMongoRepository.save(new Person("Joaquim", "BR"))
		);
		
		personElasticsearchRepository.save(
			personMongoRepository.save(new Person("Bran", "US"))
		);
		personElasticsearchRepository.save(
			personMongoRepository.save(new Person("Yushin", "JP"))
		);
		personElasticsearchRepository.save(
			personMongoRepository.save(new Person("Pablo", "ES"))
		);
	}
}