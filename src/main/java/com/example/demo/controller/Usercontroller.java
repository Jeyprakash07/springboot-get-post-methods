package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserRepo;
import com.example.demo.model.User;
import com.example.demo.model.UserRequestBody;

import lombok.Builder;


@Builder
@RestController
public class Usercontroller {
	
	@Autowired
	UserRepo repo;
	
	@GetMapping("/add/user/{id}/{name}/{phnumber}/{emailId}")
	public User addUser( 
			@PathVariable("id") int id,
			@PathVariable("name") String name, 
			@PathVariable("phnumber") String phnumber, 
			@PathVariable("emailId") String emailId) {
		String user_name = name.substring(0, 1).toUpperCase() + name.substring(1);
		User new_user = User.builder()
				.id(id)
				.name(user_name)
				.phnumber(phnumber)
				.emailId(emailId)
				.build();
		return repo.save(new_user);
	}
	
	@PostMapping("/get/user")
	public List<User> getUser(@RequestBody UserRequestBody user) {
		String name = user.getName().substring(0, 1).toUpperCase() + user.getName().substring(1);
		return repo.findMatchingNames(name);
	}
	
	@PostMapping("/get/allusers")
	public List<User> getAllUsers() {
		return repo.findAll();
	}
	
	@GetMapping("/delete/user/{id}")
	public List<User> deleteUser(@PathVariable int id) {
		User user = repo.getOne(id);
		repo.delete(user);
		return repo.findAll();
	}

}
