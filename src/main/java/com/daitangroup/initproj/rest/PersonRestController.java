package com.daitangroup.initproj.rest;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daitangroup.initproj.model.Person;
import com.daitangroup.initproj.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonRestController {

	@Autowired private PersonService personService;
	
	// TODO Fix sorting
	@GetMapping
	public Iterable<Person> findAll(@RequestParam(defaultValue="0") @Min(0) int page,
									@RequestParam(defaultValue="20") @Min(1) @Max(100) int size) {
		return personService.search(new NativeSearchQueryBuilder()
											.withPageable(PageRequest.of(page, size))
											.build());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> findById(@PathVariable String id) {
		Optional<Person> result = personService.findById(id);
		return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<String> save(@RequestBody @Valid Person person) {
		if (person.getId() != null) {
			return ResponseEntity.badRequest().build();
		}

		personService.registerOrUpdate(person);
		
		return ResponseEntity.created(URI.create("/person/"+person.getId())).build();
	}
	
	// TODO Concurrency
	// TODO ETag HTTP 204
	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable String id, @RequestBody @Valid Person person) {
		boolean isUpdatingPerson = personService.existsById(id);
		
		person.setId(id);
		personService.registerOrUpdate(person);

		return isUpdatingPerson ? ResponseEntity.noContent().build() : ResponseEntity.created(URI.create("/person/"+person.getId())).build();
	}
	
	// TODO Concurrency
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		personService.remove(id);
		
		return ResponseEntity.ok().build();
	}
}
