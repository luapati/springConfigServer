package com.cognizant.userInputCrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.userInputCrud.model.User;

@Repository
public interface UserInputCrudRepository extends JpaRepository<User, Integer> {

	@Modifying
	@Query(value="Update User u Set u.name= :name where u.id= :id")
	int updateUser(@Param("id")int id, @Param("name")String name);

}
