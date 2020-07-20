package com.cognizant.userInputCrud.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.userInputCrud.model.User;
import com.cognizant.userInputCrud.repository.UserInputCrudRepository;

@Service
public class UserInputCrudService {

	@Autowired
	UserInputCrudRepository userInputCrudRepository;
	
	@Transactional
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userInputCrudRepository.findAll();
	}

	@Transactional
	public void addUser(String name) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setName(name);
		userInputCrudRepository.save(user);
	}

	@Transactional
	public int updateUser(int userId, String name) {
		return userInputCrudRepository.updateUser(userId, name);
	}
	
	public boolean checkId(int userId) {
		if(userInputCrudRepository.findById(userId).get().getName() == null || 
				userInputCrudRepository.findById(userId).get().getName() == "" )
			return false;
		else
			return true;
	}

	@Transactional
	public void deleteUser(int userId) {
		userInputCrudRepository.deleteById(userId);
	}
}
