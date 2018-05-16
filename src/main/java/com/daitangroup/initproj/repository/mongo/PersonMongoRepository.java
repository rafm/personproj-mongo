package com.daitangroup.initproj.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.daitangroup.initproj.model.Person;

@Repository
public interface PersonMongoRepository extends MongoRepository<Person, String> {}