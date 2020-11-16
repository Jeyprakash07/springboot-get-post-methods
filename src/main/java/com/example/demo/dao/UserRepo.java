package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;

import antlr.collections.List;


public interface UserRepo extends JpaRepository<User, Integer>{

	@Query("select u from User u where u.name LIKE CONCAT(:#{#name},'%')")
	java.util.List<User> findMatchingNames(@Param("name") String name);
}
