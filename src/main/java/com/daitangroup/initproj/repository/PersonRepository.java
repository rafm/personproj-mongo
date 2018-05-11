package com.daitangroup.initproj.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.daitangroup.initproj.model.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {}