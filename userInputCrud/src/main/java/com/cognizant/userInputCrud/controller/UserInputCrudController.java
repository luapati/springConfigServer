package com.cognizant.userInputCrud.controller;

import java.util.NoSuchElementException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
	
	@Value("${user}")
	private String user;
	@Value("${pwd}")
	private String password;
	
	@GetMapping("/")
	public String getAll() {
		return "Reading all Data\n" + userInputCrudService.getAll().toString() + "\n"+ user+ "\n"+ password;
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
