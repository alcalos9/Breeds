package com.aeco.breeds.services;

import org.springframework.core.env.Environment;

import com.aeco.breeds.model.UrlService;

public class BreedServices {
	

	@SuppressWarnings("finally")
	public UrlService getUrls(Environment env){
		
		UrlService listUrl = new UrlService();
		
		try {
			String urlAll = env.getProperty("breeds.all");
			String urlImage = env.getProperty("breeds.images");	
		
			listUrl.setAll(urlAll);
			listUrl.setImage(urlImage);
			
		}catch(Exception e) {
			listUrl = null;
		}finally {
			return listUrl;
		}
	}
}
