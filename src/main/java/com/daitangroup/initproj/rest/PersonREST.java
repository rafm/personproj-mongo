package com.daitangroup.initproj.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonREST {

	@GetMapping
	public String findAll() {
		System.out.println("oi");
		return "TODO";
	}
}
