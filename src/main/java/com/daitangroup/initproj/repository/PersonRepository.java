package com.daitangroup.initproj.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.daitangroup.initproj.model.Person;

public interface PersonRepository extends MongoRepository<Person, String> {}