package com.daitangroup.initproj.repository;

import org.springframework.data.repository.CrudRepository;

import com.daitangroup.initproj.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {}