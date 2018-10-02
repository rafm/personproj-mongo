package com.github.rafm.personproj.repository;

import org.springframework.stereotype.Repository;

import com.github.rafm.personproj.repository.elasticsearch.PersonElasticsearchRepository;
import com.github.rafm.personproj.repository.mongo.PersonMongoRepository;

@Repository
public interface PersonRepository extends PersonMongoRepository, PersonElasticsearchRepository {}