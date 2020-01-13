package com.aeco.breeds.model;

import java.util.List;

public class ResultBreed {
	
	private String breed;
	private List<String> subBreeds;
	private List<Image> images;
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public List<String> getSubBreeds() {
		return subBreeds;
	}
	public void setSubBreeds(List<String> subBreeds) {
		this.subBreeds = subBreeds;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	
}
