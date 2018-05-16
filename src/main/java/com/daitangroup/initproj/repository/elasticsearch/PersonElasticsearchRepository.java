package com.daitangroup.initproj.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.daitangroup.initproj.model.Person;

@Repository
public interface PersonElasticsearchRepository extends ElasticsearchRepository<Person, String> {}