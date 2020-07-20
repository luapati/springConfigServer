package com.cognizant.userInputCrud.controller;

import java.util.NoSuchElementException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.userInputCrud.service.UserInputCrudService;

//import com.cognizant.userInput.model.User;

@RestController
@RequestMapping(path="/admin")
public class UserInputCrudController {
	
	@Autowired
	UserInputCrudService userInputCrudService;

//	@PostMapping("/user")
//	public String addUser() throws RestClientException, IOException {
//
//		String baseUrl = "http://localhost:8080/userDetails";
//
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> response = null;
//		try {
//			response = restTemplate.exchange(baseUrl, HttpMethod.POST, getHeaders(), String.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(response.getBody().toString());
//		HttpStatus status = response.getStatusCode();
//		System.out.println(status);
//		return response.getBody().toString();
//
//	}
//
//	@GetMapping(value="/showsAll")
//	public String showsAll() throws RestClientException, IOException {
//		
//		String baseUrl = "http://localhost:8080/showall";
//
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> response = null;
//		try {
//			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(response.getBody());
//		HttpStatus status = response.getStatusCode();
//		System.out.println(status);
//		return response.getBody().toString();
//	}
//
//	public static HttpEntity<?> getHeaders() throws IOException {
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//		return new HttpEntity<>(httpHeaders);
//	}
	
	@GetMapping("/")
	public String getAll() {
		return "Reading all Data\n" + userInputCrudService.getAll().toString();
	}
	
	@GetMapping("/addUser/{name}")
	public String addUser(@PathVariable("name") String name) {
			userInputCrudService.addUser(name);
			return "User added Successfully!";
	}

	@GetMapping("/updateUser/{name}/{id}")
	public String updateUser(@PathVariable("name") String name, @PathVariable("id") int id)  {
		try {
			if(userInputCrudService.checkId(id)) {
				userInputCrudService.updateUser(id, name);
				return "User Updated Successfully!";
			}
			else {
				throw new NoSuchElementException();
			}
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return "User doesn't exist";
		}
	}
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		try {
			if(userInputCrudService.checkId(id)) {
				userInputCrudService.deleteUser(id);
				return "User Deleted Successfully";
			}
			else {
				throw new NoSuchElementException();
			}
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return "User Doesn't Exist";
		}
	}
	
}
