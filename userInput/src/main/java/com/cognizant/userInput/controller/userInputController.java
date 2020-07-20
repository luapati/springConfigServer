package com.cognizant.userInput.controller;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
//@RequestMapping("http://localhost:8080")
public class userInputController {

	@Autowired
	private RestTemplate restTemplate;

	String baseUrl = "http://localhost:8081/admin/";
	String action;
	String name;
	int id;

	@PostMapping("/user")
	public String getAll(@RequestBody String jsonBody) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(jsonBody);
			name = (String) json.get("name");
			action = (String) json.get("action");
			id = Integer.parseInt((String) json.get("id"));
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		ResponseEntity<String> response = null;
		if (action.equals("create")) {
			try {
				response = restTemplate.exchange(baseUrl + "addUser/"+ name, HttpMethod.GET, getHeaders(), String.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(action.equals("read")) {
			try {
				response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(action.equals("update")) {
			try {
				response = restTemplate.exchange(baseUrl + "updateUser/"+ name +"/"+id, HttpMethod.GET, getHeaders(), String.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(action.equals("delete")) {
			try {
				response = restTemplate.exchange(baseUrl + "deleteUser/"+id, HttpMethod.GET, getHeaders(), String.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(response != null) {
			System.out.println(response.getBody().toString());
			HttpStatus status = response.getStatusCode();
			System.out.println(status);
			return response.getBody().toString();
		}
		
		else {
			return "Contact Admin";
		}
		

	}

	public static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(httpHeaders);
	}
}

//@PostMapping("/user")
//public String addUser() throws RestClientException, IOException {
//
//	String baseUrl = "http://localhost:8080/userDetails";
//
//	RestTemplate restTemplate = new RestTemplate();
//	ResponseEntity<String> response = null;
//	try {
//		response = restTemplate.exchange(baseUrl, HttpMethod.POST, getHeaders(), String.class);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	System.out.println(response.getBody().toString());
//	HttpStatus status = response.getStatusCode();
//	System.out.println(status);
//	return response.getBody().toString();
//
//}
//
//@GetMapping(value="/showsAll")
//public String showsAll() throws RestClientException, IOException {
//	
//	String baseUrl = "http://localhost:8080/showall";
//
//	RestTemplate restTemplate = new RestTemplate();
//	ResponseEntity<String> response = null;
//	try {
//		response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	System.out.println(response.getBody());
//	HttpStatus status = response.getStatusCode();
//	System.out.println(status);
//	return response.getBody().toString();
//}
//
