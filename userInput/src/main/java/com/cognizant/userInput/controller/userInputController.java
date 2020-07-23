package com.cognizant.userInput.controller;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cognizant.userInput.model.CredentialConfig;

@RestController
//@RequestMapping("http://localhost:8080")
public class userInputController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${baseurl}")
	public String baseurl;
	@Value("${user}")
	public String cruduser;
	@Value("${pwd}")
	public String crudpwd;
	
//	String baseUrl = "http://userinputcrud/admin/";
	String action;
	String name;
	int id;

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello" +cruduser+crudpwd+baseurl;
	}
	
	@PostMapping("/user")
	public String getAll(@RequestBody String jsonBody) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonBody);
		try {
//			name = (String) json.get("name");
			action = (String) json.get("action");
//			id = Integer.parseInt((String) json.get("id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<String> response = null;
		if (action.equals("create")) {
			try {
				name = (String) json.get("name");
				response = restTemplate.exchange(baseurl + "addUser/"+ name, HttpMethod.GET, getHeaders(), String.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(action.equals("read")) {
			try {
				response = restTemplate.exchange(baseurl, HttpMethod.GET, getHeaders(), String.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(action.equals("update")) {
			id = Integer.parseInt((String) json.get("id"));
			name = (String) json.get("name");
			try {
				response = restTemplate.exchange(baseurl + "updateUser/"+ name +"/"+id, HttpMethod.GET, getHeaders(), String.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(action.equals("delete")) {
			id = Integer.parseInt((String) json.get("id"));
			try {
				response = restTemplate.exchange(baseurl + "deleteUser/"+id, HttpMethod.GET, getHeaders(), String.class);
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

	public HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders httpHeaders = new HttpHeaders();
//		String username = cruduser;
		httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.setBasicAuth(cruduser, crudpwd);
//		httpHeaders.setBasicAuth("admin", "pwd");
		return new HttpEntity<>(httpHeaders);
	}

}

