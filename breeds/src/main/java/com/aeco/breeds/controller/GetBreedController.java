package com.aeco.breeds.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.aeco.breeds.model.Image;
import com.aeco.breeds.model.ResultBreed;
import com.aeco.breeds.model.UrlService;
import com.aeco.breeds.model.response.Message;
import com.aeco.breeds.model.response.ResponseAll;
import com.aeco.breeds.model.response.ResponseImages;
import com.aeco.breeds.model.response.SubBreeds;
import com.aeco.breeds.services.GetBreedServices;

@RestController
public class GetBreedController {

	GetBreedServices serviceGetBreed;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "/getBreed", method= RequestMethod.GET)
	public ResultBreed getUrls( @RequestParam(value = "breed", required = true) String breed){
		
		serviceGetBreed = new GetBreedServices();
		ResultBreed result = new ResultBreed();
		ResponseAll all = new ResponseAll();
		ResponseImages images = new ResponseImages();
		
		try {
			
			/** Obtener las URL del servicio **/
			String url = "http://localhost:8080/urls";
			UrlService response = restTemplate.getForObject(url, UrlService.class);
			
			String urlAll = response.getAll();
			String urlImages = response.getImage();
			
			if(breed.length()>0) {
				urlImages = urlImages.replace("@", breed);
				
				all = serviceGetBreed.getAllBreeds(urlAll);
				images = serviceGetBreed.getImagesBreed(urlImages);
				
				/** Genera el JSON de salida **/
				if(all.getStatus().equalsIgnoreCase("success") && images.getStatus().equalsIgnoreCase("success")) {
					for (Message br : all.getMessage()) {
						if(breed.equalsIgnoreCase(br.getName())) {
							List<String> subBr = new ArrayList<String>();
							for (SubBreeds sBr : br.getBreed()) {
								subBr.add(sBr.getName());
							}
							result.setSubBreeds(subBr);
						}
					}
					
					List<Image> imgs = new ArrayList<Image>();
					for(String im: images.getMessage()) {
						Image img = new Image();
						img.setUrl(im);
						imgs.add(img);
					}
					result.setImages(imgs);
					
					result.setBreed(breed);
				}
			}

		}catch(Exception e) {
			return null;
		}finally {
			return result;
		}
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
