package com.daitangroup.initproj.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class Person {

	@Id
	private String id;
	
	@NotNull
	private String name;
	
	private String country;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
