package com.aeco.breeds.model.response;

import java.util.List;

public class Message {
	
	private String name;
	private List<SubBreeds>  breed;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SubBreeds> getBreed() {
		return breed;
	}

	public void setBreed(List<SubBreeds> breed) {
		this.breed = breed;
	}


}
