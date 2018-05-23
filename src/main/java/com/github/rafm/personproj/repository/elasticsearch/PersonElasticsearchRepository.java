package com.github.rafm.personproj.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.github.rafm.personproj.model.Person;

@Repository
public interface PersonElasticsearchRepository extends ElasticsearchRepository<Person, String> {}