package com.userlogin.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userlogin.entity.User;
import com.userlogin.service.UserServiceImpl;
import com.userlogin.web.dto.UserRegistrationDto;

@RestController
public class UserController {
	
	@Autowired
	 private UserServiceImpl userService;
	
	// get all users API
	@GetMapping("/users")
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	
	// get user by id
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable (value ="id") long userId) {
		return userService.getUserById(userId);
	}
	
	//create a new user
	@PostMapping("/new")
	public User registerUserAccount(@RequestBody UserRegistrationDto registrationDto) {
		return userService.save(registrationDto);
	}
	
	// update user
	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") long userId) {
		return userService.update(user, userId);
	}
	
	// login user
	@PostMapping("/login")
	public User loginUser(@RequestParam String email, @RequestParam String password) {
		return userService.login(email, password);
	}
	
	// delete user by id
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
		return userService.delete(userId);
	}
	
}
