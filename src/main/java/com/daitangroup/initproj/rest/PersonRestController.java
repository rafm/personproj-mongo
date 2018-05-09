package com.daitangroup.initproj.rest;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daitangroup.initproj.model.Person;
import com.daitangroup.initproj.repository.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonRestController {

	@Autowired private PersonRepository personRepository;
	
	// TODO Query Parameter
	@GetMapping
	public Iterable<Person> findAll() {
		return personRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> findById(@PathVariable Integer id) {
		Optional<Person> result = personRepository.findById(id);
		return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<String> save(@RequestBody @Valid Person person) {
		if (person.getId() != null) {
			return ResponseEntity.badRequest().build();
		}
		
		personRepository.save(person);
		return ResponseEntity.created(URI.create("/person/"+person.getId())).build();
	}
	
	// TODO ETag HTTP 204
	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody @Valid Person person) {
		Optional<Person> result = personRepository.findById(id);
		if (!result.isPresent()) {
			return ResponseEntity.notFound().build(); 
		}
		
		person.setId(id);
		
		personRepository.save(person);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		if (personRepository.existsById(id)) {
			personRepository.deleteById(id);
		}
		
		return ResponseEntity.ok().build();
	}
}
