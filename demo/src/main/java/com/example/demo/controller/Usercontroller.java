package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserRepo;
import com.example.demo.model.User;

@RestController
public class Usercontroller {
	
	@Autowired
	UserRepo repo;
	
	@GetMapping("/add/user")
	public List<User> addUser(User user) {
		repo.save(user);
		return repo.findAll();
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
