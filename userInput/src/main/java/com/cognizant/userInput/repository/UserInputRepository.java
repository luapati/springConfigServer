package com.cognizant.userInput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.userInput.model.User;

@Repository
public interface UserInputRepository extends JpaRepository<User, Integer>{
	
}
