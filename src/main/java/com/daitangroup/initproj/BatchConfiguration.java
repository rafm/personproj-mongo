package com.daitangroup.initproj;
import java.util.Collections;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.daitangroup.initproj.model.Person;
import com.daitangroup.initproj.repository.elasticsearch.PersonElasticsearchRepository;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired private JobBuilderFactory jobBuilderFactory;
	
	@Autowired private StepBuilderFactory stepBuilderFactory;
	
	@Autowired private MongoTemplate mongoTemplate;
	
	@Autowired private PersonElasticsearchRepository personElasticsearchRepository;
	
	@Bean
	public Job indexElasticsearchJob() {
		return jobBuilderFactory.get("indexElasticsearchJob")
									.start(exportData())
								.build();
	}
	
	private Step exportData() {
		return stepBuilderFactory.get("exportData")
								.<Person, Person> chunk(10)
								.reader(
										new MongoItemReaderBuilder<Person>().name("mongoItemReader")
											.jsonQuery("{}")
											.targetType(Person.class)
											.template(mongoTemplate)
											.sorts(Collections.emptyMap()).currentItemCount(0)
											.build()
								)
								.processor(
										(ItemProcessor<Person, Person>) person -> person
								)
								.writer(
										new RepositoryItemWriterBuilder<Person>()
											.repository(personElasticsearchRepository)
											.methodName("save")
											.build()
								)
								.build();
	}
}
