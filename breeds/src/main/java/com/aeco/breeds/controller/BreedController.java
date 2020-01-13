package com.aeco.breeds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeco.breeds.model.UrlService;
import com.aeco.breeds.services.BreedServices;

@RestController
public class BreedController {

	BreedServices servicesBreed;
	
	@Autowired
	private Environment env;
	
	@RequestMapping(value = "/hello")
	public String helloWorld() {
		return "Hello World Sismos ";
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "/urls")
	public UrlService getUrls(){
		UrlService listUrl = new UrlService();
		servicesBreed = new BreedServices();
		try {
			listUrl = servicesBreed.getUrls(env);
			
		}catch(Exception e) {
			listUrl = null;
		}finally {
			return listUrl;
		}
	}
	
}
