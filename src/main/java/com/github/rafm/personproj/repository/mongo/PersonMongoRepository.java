package com.github.rafm.personproj.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.rafm.personproj.model.Person;

@Repository
public interface PersonMongoRepository extends MongoRepository<Person, String> {}