package com.aeco.breeds.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.aeco.breeds.model.response.Message;
import com.aeco.breeds.model.response.ResponseAll;
import com.aeco.breeds.model.response.ResponseImages;
import com.aeco.breeds.model.response.SubBreeds;
import com.google.gson.Gson;

public class GetBreedServices {

	public ResponseAll getAllBreeds(String urlAll) {
		
		ResponseAll result = new ResponseAll();
		List<Message> message = new ArrayList<Message>();
		URL url;
		HttpURLConnection conn = null;

		try {			
			url = new URL(urlAll);
		
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("content-type", "application/json; charset=utf-8");
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));			
			
			StringBuilder response = new StringBuilder();
			String responseSingle = null; 
            while ((responseSingle = br.readLine()) != null)  
            { 
                response.append(responseSingle); 
            } 
            String resultado = response.toString(); 
            
            String[] res = resultado.split(",\"status\"");
            result.setStatus(res[1].replace("\"}", "").replace("\"", "").replace(":", ""));
            res = res[0].split("\"message\":");
            
            String[] res2 = res[1].split("],\"");
            
            for(int i=0; i < res2.length; i++) {
            	Message breed = new Message();
            	List<SubBreeds>  sBreed = new ArrayList<SubBreeds>();
            	
            	String[] line = res2[i].split("\":\\[");
            	breed.setName(line[0].replace("\"", "").replace("{", "").replace("}", ""));
            	
            	if(line.length > 1) {
            		String[] subLine = line[1].split(",");
            		
            		for(int j=0; j<subLine.length; j++) {
            			SubBreeds sub = new SubBreeds();
            			sub.setName(subLine[j].replace("\"", ""));
            			
            			sBreed.add(sub);
            		}
            	}
            	breed.setBreed(sBreed);
            	message.add(breed);
            }
            
            result.setMessage(message);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			conn.disconnect();
		}		

		return result;
	}

	public ResponseImages getImagesBreed(String urlImages) {
		ResponseImages result = new ResponseImages();
		List<Message> message = new ArrayList<Message>();
		URL url;
		HttpURLConnection conn = null;

		try {			
			url = new URL(urlImages);
		
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("content-type", "application/json; charset=utf-8");
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));			
			
			StringBuilder response = new StringBuilder();
			String responseSingle = null; 
            while ((responseSingle = br.readLine()) != null)  
            { 
                response.append(responseSingle); 
            } 
            String resultado = response.toString(); 
            
            Gson gson = new Gson();
            result = gson.fromJson(resultado, ResponseImages.class);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			conn.disconnect();
		}		

		return result;
	}

}
