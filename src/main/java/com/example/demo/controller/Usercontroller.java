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
import com.example.demo.model.User.UserBuilder;

import lombok.Builder;


@Builder
@RestController
public class Usercontroller {
	
	@Autowired
	UserRepo repo;
	
	@GetMapping("/add/user/{id}/{name}")
	public User addUser(@PathVariable("id") int id, @PathVariable("name") String name) {
		User new_user = User.builder()
				.id(id)
				.name(name)
				.build();
		return repo.save(new_user);
	}
	
	@PostMapping("/get/user")
	public Optional<User> getUser(@RequestBody int id) {
		return repo.findById(id);
	}
	
	@PostMapping("/get/allusers")
	public List<User> getAllUsers() {
		return repo.findAll();
	}
	
	

}
