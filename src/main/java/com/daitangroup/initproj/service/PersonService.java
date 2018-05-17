package com.daitangroup.initproj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.daitangroup.initproj.model.Person;
import com.daitangroup.initproj.repository.elasticsearch.PersonElasticsearchRepository;
import com.daitangroup.initproj.repository.mongo.PersonMongoRepository;

@Service
public class PersonService {

	@Autowired private PersonMongoRepository personMongoRepository;

	@Autowired private PersonElasticsearchRepository personElasticsearchRepository;
	
	// TODO Search with Elasticsearch API
	public List<Person> search(SearchQuery searchQuery) {
		return personElasticsearchRepository.search(searchQuery).getContent();
	}

	public Optional<Person> findById(String id) {
		return personMongoRepository.findById(id);
	}
	
	public boolean existsById(String id) {
		return personMongoRepository.existsById(id);
	}
	
	/**
	 * If Person's id is null, then he will be registered, else his data will be updated. 
	 * 
	 * @param person to register or update data
	 */
	public void registerOrUpdate(Person person) {
		personMongoRepository.save(person);
		personElasticsearchRepository.save(person);
	}
	
	// TODO consistency
	public void remove(String id) {
		if (personMongoRepository.existsById(id)) {
			personMongoRepository.deleteById(id);
		}
		
		if (personElasticsearchRepository.existsById(id)) {
			personElasticsearchRepository.deleteById(id);
		}
	}
}
